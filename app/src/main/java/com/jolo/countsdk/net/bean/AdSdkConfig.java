package com.jolo.countsdk.net.bean;


import com.jolo.countsdk.net.BaseNetData;
import com.jolo.countsdk.net.response.GetAdSdkConfigResp;

/**
 * Description: AdSdkConfig
 * Created by dzq on 2016/10/17.
 */

public class AdSdkConfig extends BaseNetData {

    public Long serverTime;// 服务器当前时间，可用于客户端校对时间，毫秒

    public Long adSdkConfigTimeInterval; //告诉终端隔多少时间再来请求获取版本更新信息和广告黑名单信息等，单位毫秒,最大值为24小时？如果超过24小时，则按照客户端设定的时间来请求

    public Integer adSdkVer; //广告SDK的版本号

    public String adSdkUpgradeUrl;// 版本更新地址

    public Integer blackPkgsVer; //黑名单版本号

    public String blackPkgs;// APP包名黑名单

    public Long userApksTimeInterval; //告诉终端用户手机的app信息隔多少时间再上传一次，单位毫秒

    public Long adMsgListTimeInterval; //告诉终端隔多少时间再来请求广告列表，单位毫秒,最大值为24小时？如果超过24小时，则按照客户端设定的时间来请求

    public String uuid;//用来唯一标识某一次请求UUID

    public String terminalId;//用来唯一标识某一次请求TerminalId

    public Long userAdEventTimeInterval; //告诉终端上传用户广告行为日志多久上传一次，单位毫秒

    public void convert(GetAdSdkConfigResp resp) {
        uuid = resp.getUuid();
        serverTime = resp.getServerTime();
        adSdkConfigTimeInterval = resp.getAdSdkConfigTimeInterval();
        adSdkVer = resp.getAdSdkVer();
        adSdkUpgradeUrl = resp.getAdSdkUpgradeUrl();
        blackPkgsVer = resp.getBlackPkgsVer();
        blackPkgs = resp.getBlackPkgs();
        userApksTimeInterval = resp.getUserApksTimeInterval();
        adMsgListTimeInterval = resp.getAdMsgListTimeInterval();
        terminalId = resp.getTerminalId();
        userAdEventTimeInterval = resp.getUserAdEventTimeInterval();
    }

    @Override
    public String toString() {
        return "AdSdkConfig{" +
                "serverTime=" + serverTime +
                ", adSdkConfigTimeInterval=" + adSdkConfigTimeInterval +
                ", adSdkVer=" + adSdkVer +
                ", adSdkUpgradeUrl='" + adSdkUpgradeUrl + '\'' +
                ", blackPkgsVer=" + blackPkgsVer +
                ", blackPkgs='" + blackPkgs + '\'' +
                ", userApksTimeInterval=" + userApksTimeInterval +
                ", adMsgListTimeInterval=" + adMsgListTimeInterval +
                ", userAdEventTimeInterval=" + userAdEventTimeInterval +
                ", uuid='" + uuid + '\'' +
                ", terminalId='" + terminalId + '\'' +
                '}';
    }
}
