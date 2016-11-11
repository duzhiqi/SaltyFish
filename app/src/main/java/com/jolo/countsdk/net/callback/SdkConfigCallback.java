package com.jolo.countsdk.net.callback;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.net.BaseNetUtil;
import com.jolo.countsdk.net.bean.AdSdkConfig;
import com.jolo.countsdk.net.impl.GetAdSdkConfigNetUtil;
import com.jolo.countsdk.net.response.GetAdSdkConfigResp;
import com.jolo.countsdk.util.SharedPreferencesUtil;

/**
 * Description:
 * Created by duzhiqi on 2016/11/10.
 */

public class SdkConfigCallback implements BaseNetUtil.Callbacks<AdSdkConfig, GetAdSdkConfigResp> {

    private int index;
    private Context mContext;
    private int REPOST_REQUEST_COUNT = 3;

    public SdkConfigCallback(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    @Override
    public void onFailed() {
        rePostRequest();
    }

    @Override
    public void onError(Exception e) {
        rePostRequest();
    }

    @Override
    public void onNetError() {

    }

    @Override
    public void onSuccess(@NonNull AdSdkConfig result) {
        SharedPreferencesUtil.putSdkConfig(mContext, result);
        index = 0;
    }

    private void rePostRequest() {
        if (index < REPOST_REQUEST_COUNT) {
            GetAdSdkConfigNetUtil net = new GetAdSdkConfigNetUtil(mContext,
                    SharedPreferencesUtil.getInt(mContext, SPConstants.KEY_BLACK_PKGSVER, 0));
            net.getAdSdkConfig(this);
            index++;
        }
    }
}
