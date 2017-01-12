package com.jolo.countsdk.net.request;


/**
  * 
  * <p>Title: GetAdSdkConfigReq</p>
  * <p>Description: 广告SDK的基本配置信息请求</p>
  * <p>Company: jolo</p> 
  * @author hw
  * @date 2016-12-16 上午11:23:01
 */
public class GetAdSdkConfigReq extends BaseReq {
	
	private String uuid; //GAID
	
	private Long installTime;//安装时间
	
	private Integer adSdkVer; //广告SDK的版本号
	
	private Integer blackPkgsVer; //广告Sdk的包名黑名单版本号

	@Override
	public Integer getBusinessCode() {
		return 1001;
	}

	public Integer getAdSdkVer() {
		return adSdkVer;
	}

	public void setAdSdkVer(Integer adSdkVer) {
		this.adSdkVer = adSdkVer;
	}

	public Long getInstallTime() {
		return installTime;
	}

	public void setInstallTime(Long installTime) {
		this.installTime = installTime;
	}

	public Integer getBlackPkgsVer() {
		return blackPkgsVer;
	}

	public void setBlackPkgsVer(Integer blackPkgsVer) {
		this.blackPkgsVer = blackPkgsVer;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
