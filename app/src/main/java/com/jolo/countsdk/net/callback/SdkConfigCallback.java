package com.jolo.countsdk.net.callback;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.net.BaseNetUtil;
import com.jolo.countsdk.net.bean.AdSdkConfig;
import com.jolo.countsdk.net.impl.GetAdSdkConfigNetUtil;
import com.jolo.countsdk.net.response.GetAdSdkConfigResp;
import com.jolo.countsdk.util.SLog;
import com.jolo.countsdk.util.SharedPreferencesUtil;

/**
 * Description:
 * Created by duzhiqi on 2016/11/10.
 */

public class SdkConfigCallback implements BaseNetUtil.Callbacks<AdSdkConfig, GetAdSdkConfigResp> {

    private Context mContext;

    public SdkConfigCallback(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    @Override
    public void onFailed() {
        SLog.e("Debug", "网络请求失败");
    }

    @Override
    public void onError(Exception e) {
        SLog.e("Debug", "网络请求失败");
    }

    @Override
    public void onNetError() {
        SLog.i("Debug", "无网络连接.");
    }

    @Override
    public void onSuccess(@NonNull AdSdkConfig result) {
        SLog.i("Debug", "请求成功返回");
        SharedPreferencesUtil.putSdkConfig(mContext, result);
    }

}
