package com.jolo.countsdk.net;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.jolo.countsdk.net.request.BaseReq;
import com.jolo.countsdk.net.response.BaseResp;
import com.jolo.countsdk.util.DateUtil;
import com.jolo.countsdk.util.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;


/**
 * Description: OkHttp封装类
 * Created by dzq on 2016/10/11.
 */
public class OkHttpWrapper<Q extends BaseReq, P extends BaseResp> {
    private Q req;
    private String url;
    private static final int MAX_TIMEOUT_TIME =  5 * 1000;


    public OkHttpWrapper(Q req, String url) {
        this.req = req;
        this.url = url;
    }

    /**
     * Post 请求
     * @return ResponseBody
     * @throws IOException
     */
    @Nullable
    String postMethod() throws IOException {
        String json = JsonParser.toJson(req);
        Log.d("dzq", "request:" + json);
        if (TextUtils.isEmpty(json)) return null;

        GZIPInputStream gzipInputStream;
        ByteArrayOutputStream baos;
        InputStream inStrm;
        HttpURLConnection conn;

//        String encode = URLEncoder.encode(url, "utf-8");
        conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setReadTimeout(MAX_TIMEOUT_TIME);
        conn.setConnectTimeout(MAX_TIMEOUT_TIME);
        conn.setRequestMethod("POST");
            /* 允许Input、Output，不使用Cache */
        conn.setDoInput(true); // 表示从服务器获取数据
        conn.setDoOutput(true);// 表示向服务器写数据
        conn.setUseCaches(false); //不使用缓存，每次请求的数据，都需要是新的
        conn.setRequestProperty("Content-Type", "application/x-tar");
        conn.setRequestProperty("Content-Length", String.valueOf(json.getBytes().length));
        conn.setRequestProperty("Accept-Encoding", "gzip");
        conn.setRequestProperty("Connection", "Keep-Alive");
        System.setProperty("http.keepAlive","false");
        conn.setRequestProperty("Charset", "UTF-8");

//        OutputStream outStream = conn.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(conn.getOutputStream());
        bos.write(json.getBytes());
        bos.flush();
        bos.close();

        int status;
        inStrm = conn.getInputStream(); // <===注意，实际发送请求的代码段就在这里
        status = conn.getResponseCode();

        if (status == HttpURLConnection.HTTP_OK) {
            String contentEncoding = conn.getContentEncoding();
            /** 判断是否是GZIP **/
            boolean isGzipEncoding = false;

            // 读取数据
            if ((null != contentEncoding) && contentEncoding.equalsIgnoreCase("gzip")) {
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
                conn.disconnect();
                return baos.toString("utf-8");

            } else {
                baos = new ByteArrayOutputStream();
                int len;
                byte[] readBuffer = new byte[1024];
                while ((len = inStrm.read(readBuffer)) != -1) {
                    baos.write(readBuffer, 0, len);
                }
                conn.disconnect();
                return baos.toString("utf-8");
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
