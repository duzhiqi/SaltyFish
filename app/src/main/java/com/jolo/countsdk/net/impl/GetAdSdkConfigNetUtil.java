package com.jolo.countsdk.net.impl;

import android.content.Context;

import com.jolo.countsdk.config.Config;
import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.net.BaseNetData;
import com.jolo.countsdk.net.BaseNetUtil;
import com.jolo.countsdk.net.bean.AdSdkConfig;
import com.jolo.countsdk.net.request.GetAdSdkConfigReq;
import com.jolo.countsdk.net.response.BaseResp;
import com.jolo.countsdk.net.response.GetAdSdkConfigResp;
import com.jolo.countsdk.util.LocationUtil;
import com.jolo.countsdk.util.SharedPreferencesUtil;
import com.jolo.countsdk.util.VersionUtil;

/**
 * Description:
 * Created by dzq on 2016/10/17.
 */

public class GetAdSdkConfigNetUtil extends BaseNetUtil<BaseNetData, GetAdSdkConfigReq, GetAdSdkConfigResp> {
    private Context mContext;
    private int blackPkgsVer;

    public GetAdSdkConfigNetUtil(Context context, int blackPkgsVer) {
        this.mContext = context.getApplicationContext();
        this.blackPkgsVer = blackPkgsVer;
    }

    @Override
    protected Context getContext() {
        return mContext;
    }

    @Override
    protected GetAdSdkConfigReq getRequest() {
        GetAdSdkConfigReq request = new GetAdSdkConfigReq();
        request.setUuid(SharedPreferencesUtil.getString(mContext, SPConstants.KEY_GAID, ""));
        request.setInstallTime(VersionUtil.getFirstInstallAppTime(mContext));
        request.setAdSdkVer(VersionUtil.getVersionCode(mContext));
        request.setBlackPkgsVer(blackPkgsVer);
        return request;
    }

    @Override
    protected String getUrl() {
        return Config.BASE_URL + "/getadsdkconfig";
    }

    @Override
    protected BaseNetData parseResponse(GetAdSdkConfigResp response) {
        AdSdkConfig adsdkconfig = new AdSdkConfig();
        adsdkconfig.convert(response);
        return adsdkconfig;
    }

    @Override
    protected Class<? extends BaseResp> getRespClass() {
        return GetAdSdkConfigResp.class;
    }

    public void getAdSdkConfig(Callbacks callbacks){

        postRequest(callbacks);
    }
}
