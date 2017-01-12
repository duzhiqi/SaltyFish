package com.jolo.countsdk.net.impl;

import android.content.Context;

import com.jolo.countsdk.config.Config;
import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.net.BaseNetData;
import com.jolo.countsdk.net.BaseNetUtil;
import com.jolo.countsdk.net.request.UploadUserInsApplistReq;
import com.jolo.countsdk.net.response.BaseResp;
import com.jolo.countsdk.util.Base64;
import com.jolo.countsdk.util.SharedPreferencesUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * Description:
 * Created by dzq on 2016/10/17.
 */

public class UploadUserAppListNetUtil extends BaseNetUtil<BaseNetData, UploadUserInsApplistReq, BaseResp> {
    private Context mContext;
    private byte[] apkLists;

    public UploadUserAppListNetUtil(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    @Override
    protected Context getContext() {
        return mContext;
    }

    @Override
    protected UploadUserInsApplistReq getRequest() {
        UploadUserInsApplistReq request = new UploadUserInsApplistReq();
        request.setUuid(SharedPreferencesUtil.getString(mContext, SPConstants.KEY_GAID, ""));
        String encode = Base64.encode(apkLists);
        request.setUserApks(encode);
        return request;
    }

    @Override
    protected String getUrl() {
        return Config.BASE_URL + "/uploaduserinsapplist";
    }

    @Override
    protected BaseNetData parseResponse(BaseResp response) {
        BaseNetData data = new BaseNetData();
        if (response.getResponseCode() != null){
            data.responseMsg = response.getResponseMsg();
            data.responseCode = response.getResponseCode();
        }
        return data;
    }

    @Override
    protected Class<? extends BaseResp> getRespClass() {
        return BaseResp.class;
    }


    public void uploadAppList(String apps, Callbacks callbacks) {
        apkLists = compress(apps);
        postRequest(callbacks);
    }


    //gzip压缩
    private static byte[] compress(String str) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
            gzip.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return out.toByteArray();
    }
}
