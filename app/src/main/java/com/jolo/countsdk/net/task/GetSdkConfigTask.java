package com.jolo.countsdk.net.task;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.net.BaseNetUtil;
import com.jolo.countsdk.net.bean.AdSdkConfig;
import com.jolo.countsdk.net.callback.SdkConfigCallback;
import com.jolo.countsdk.net.impl.GetAdSdkConfigNetUtil;
import com.jolo.countsdk.net.response.GetAdSdkConfigResp;
import com.jolo.countsdk.util.SharedPreferencesUtil;

/**
 * Description:
 * Created by duzhiqi on 2016/11/9.
 */

public class GetSdkConfigTask implements Runnable {
    private Context mContext;

    public GetSdkConfigTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void run() {
        GetAdSdkConfigNetUtil.init(mContext);
        GetAdSdkConfigNetUtil net = new GetAdSdkConfigNetUtil(mContext,
                SharedPreferencesUtil.getInt(mContext, SPConstants.KEY_BLACK_PKGSVER, 0));
        net.getAdSdkConfig(new SdkConfigCallback(mContext));
    }

}
