package com.jolo.countsdk.net.request;


import com.jolo.fd.codec.bean.BaseReq;
import com.jolo.fd.codec.bean.tlv.annotation.TLVAttribute;

/**
 * 
  * <p>Title: UploadUserInsApplistReq</p>
  * <p>Description: 收集上传用户手机app信息</p>
  * <p>Company: jolo</p> 
  * @author hw
  * @date 2016-10-17 上午10:17:47
 */
public class UploadUserInsApplistReq extends BaseReq {
	
	@TLVAttribute(tag = 1000)
	private String uuid; //用来唯一标识某一次请求UUID

	@TLVAttribute(tag=10019010)
	private byte[] userApks; //用户的apk信息
	
	@TLVAttribute(tag=20013533)
	private String lat; //纬度
	
	@TLVAttribute(tag=20013534)
	private String lng; //经度
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public byte[] getUserApks() {
		return userApks;
	}

	public void setUserApks(byte[] userApks) {
		this.userApks = userApks;
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
