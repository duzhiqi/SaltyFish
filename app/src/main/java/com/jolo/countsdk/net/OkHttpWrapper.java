package com.jolo.countsdk.net;

import com.jolo.fd.codec.bean.BaseReq;
import com.jolo.fd.codec.bean.BaseResp;
import com.jolo.fd.codec.bean.tlv.encode.TLVEncodeContext;
import com.jolo.fd.codec.bean.tlv.encode.encoders.BeanTLVEncoder;
import com.jolo.fd.util.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;


/**
 * Description: OkHttp封装类
 * Created by dzq on 2016/10/11.
 */
class OkHttpWrapper<Q extends BaseReq, P extends BaseResp> {
    private Q req;
    private String url;
    private static final int MAX_TIMEOUT_TIME = 60 * 1000;


    OkHttpWrapper(Q req, String url) {
        this.req = req;
        this.url = url;
    }

    /**
     * Post 请求
     * @return ResponseBody
     * @throws IOException
     */
    byte[] postMethod() throws IOException {
        BeanTLVEncoder beanEncoder = new BeanTLVEncoder();
        TLVEncodeContext encode = beanEncoder.getEncodeContextFactory().createEncodeContext(req.getClass(), null);

        byte[] data = ByteUtils.union(beanEncoder.encode(req, encode));

        GZIPInputStream gzipInputStream;
        ByteArrayOutputStream baos;
        InputStream inStrm;
        HttpURLConnection conn;


        conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setReadTimeout(MAX_TIMEOUT_TIME);
        conn.setConnectTimeout(MAX_TIMEOUT_TIME);
        conn.setRequestMethod("POST");
            /* 允许Input、Output，不使用Cache */
        conn.setDoInput(true); // 表示从服务器获取数据
        conn.setDoOutput(true);// 表示向服务器写数据
        conn.setUseCaches(false); //不使用缓存，每次请求的数据，都需要是新的
        conn.setRequestProperty("Content-Type", "application/x-tar");
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        conn.setRequestProperty("Accept-Encoding", "gzip");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");

        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();

        int status;
        inStrm = conn.getInputStream(); // <===注意，实际发送请求的代码段就在这里
        status = conn.getResponseCode();

        if (status == HttpURLConnection.HTTP_OK) {
            String contentEncoding = conn.getContentEncoding();
            /** 判断是否是GZIP **/
            boolean isGzipEncoding = false;

            // 读取数据
            if ((null != contentEncoding)
                    && contentEncoding.equalsIgnoreCase("gzip")) {
                isGzipEncoding = true;
            }

            if (isGzipEncoding) {
                // 如果是GZIP压缩
                gzipInputStream = new GZIPInputStream(inStrm);
                baos = new ByteArrayOutputStream();
                byte[] readBuffer = new byte[1024];
                int len;
                while ((len = gzipInputStream.read(readBuffer)) != -1) {
                    baos.write(readBuffer, 0, len);
                }

                return baos.toByteArray();

            } else {
                baos = new ByteArrayOutputStream();
                int len;
                byte[] readBuffer = new byte[1024];
                while ((len = inStrm.read(readBuffer)) != -1) {
                    baos.write(readBuffer, 0, len);
                }

                return baos.toByteArray();
            }
        }

        conn.disconnect();

        try {
            if (null != inStrm) {
                inStrm.close();
            }

        } catch (Exception ignored) {
        }
        return null;
    }
}
