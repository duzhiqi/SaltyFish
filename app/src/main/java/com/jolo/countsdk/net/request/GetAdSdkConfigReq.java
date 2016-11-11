package com.jolo.countsdk.net.request;


import com.jolo.fd.codec.bean.BaseReq;
import com.jolo.fd.codec.bean.tlv.annotation.TLVAttribute;

/**
 * 
  * <p>Title: GetAdSdkConfigReq</p>
  * <p>Description: 广告SDK的基本配置信息请求</p>
  * <p>Company: jolo</p> 
  * @author hw
  * @date 2016-10-12 下午6:23:01
 */
public class GetAdSdkConfigReq extends BaseReq {
	
	@TLVAttribute(tag = 1000)
	private String uuid; //用来唯一标识某一次请求UUID
	
	@TLVAttribute(tag=1028)
	private Long installTime;//安装时间
	
	@TLVAttribute(tag = 20013500)
	private Integer adSdkVer; //广告SDK的版本号
	
	@TLVAttribute(tag = 20013505)
	private Integer blackPkgsVer; //广告Sdk的包名黑名单版本号
	
	@TLVAttribute(tag=20013533)
	private String lat; //纬度
	
	@TLVAttribute(tag=20013534)
	private String lng; //经度
	
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

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
}
