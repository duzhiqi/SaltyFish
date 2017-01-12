package com.jolo.countsdk.net.response;


/**
  * 
  * <p>Title: GetAdSdkConfigResp</p>
  * <p>Description: 广告SDK基本配置信息请求返回</p>
  * <p>Company: jolo</p> 
  * @author hw
  * @date 2016-12-16 上午11:29:21
 */
public class GetAdSdkConfigResp extends BaseResp{
	
	private String terminalId;// 终端标识，由服务端下发
	
	private Long serverTime;// 服务器当前时间，可用于客户端校对时间，毫秒
	
	private Long adSdkConfigTimeInterval; //告诉终端隔多少时间再来请求获取版本更新信息和广告黑名单信息等，单位毫秒,最大值为24小时？如果超过24小时，则按照客户端设定的时间来请求

	private Integer adSdkVer; //广告SDK的版本号
	
	private String adSdkUpgradeUrl;// 版本更新地址
	
	private Integer blackPkgsVer; //黑名单版本号
	
	private String blackPkgs;// APP包名黑名单

	private Long userApksTimeInterval; //告诉终端用户手机的app信息隔多少时间再上传一次，单位毫秒
	
	private Long adMsgListTimeInterval; //告诉终端隔多少时间再来请求广告列表，单位毫秒,最大值为24小时？如果超过24小时，则按照客户端设定的时间来请求
	
	private Long userAdEventTimeInterval; //告诉终端上传用户广告行为日志多久上传一次，单位毫秒,最大值为24小时？如果超过24小时，则按照客户端设定的时间来请求
	
	public Long getServerTime() {
		return serverTime;
	}

	public void setServerTime(Long serverTime) {
		this.serverTime = serverTime;
	}

	public Long getAdSdkConfigTimeInterval() {
		return adSdkConfigTimeInterval;
	}

	public void setAdSdkConfigTimeInterval(Long adSdkConfigTimeInterval) {
		this.adSdkConfigTimeInterval = adSdkConfigTimeInterval;
	}

	public Integer getAdSdkVer() {
		return adSdkVer;
	}

	public void setAdSdkVer(Integer adSdkVer) {
		this.adSdkVer = adSdkVer;
	}

	public String getAdSdkUpgradeUrl() {
		return adSdkUpgradeUrl;
	}

	public void setAdSdkUpgradeUrl(String adSdkUpgradeUrl) {
		this.adSdkUpgradeUrl = adSdkUpgradeUrl;
	}

	public Integer getBlackPkgsVer() {
		return blackPkgsVer;
	}

	public void setBlackPkgsVer(Integer blackPkgsVer) {
		this.blackPkgsVer = blackPkgsVer;
	}

	public String getBlackPkgs() {
		return blackPkgs;
	}

	public void setBlackPkgs(String blackPkgs) {
		this.blackPkgs = blackPkgs;
	}

	public Long getUserApksTimeInterval() {
		return userApksTimeInterval;
	}

	public void setUserApksTimeInterval(Long userApksTimeInterval) {
		this.userApksTimeInterval = userApksTimeInterval;
	}

	public Long getAdMsgListTimeInterval() {
		return adMsgListTimeInterval;
	}

	public void setAdMsgListTimeInterval(Long adMsgListTimeInterval) {
		this.adMsgListTimeInterval = adMsgListTimeInterval;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public Long getUserAdEventTimeInterval() {
		return userAdEventTimeInterval;
	}

	public void setUserAdEventTimeInterval(Long userAdEventTimeInterval) {
		this.userAdEventTimeInterval = userAdEventTimeInterval;
	}

	
}
