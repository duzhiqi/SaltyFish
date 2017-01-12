package com.jolo.countsdk.net.request;


/**
 * 
  * <p>Title: UploadUserInsApplistReq</p>
  * <p>Description: 收集上传用户手机app信息</p>
  * <p>Company: jolo</p> 
  * @author hw
  * @date 2016-12-16 上午11:37:47
 */
public class UploadUserInsApplistReq extends BaseReq{
	
	private String uuid; //GAID

//	private byte[] userApks; //用户的apk信息
	private String userApks; //用户的apk信息

	@Override
	public Integer getBusinessCode() {
		return 2001;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

//	public byte[] getUserApks() {
//		return userApks;
//	}
//
//	public void setUserApks(byte[] userApks) {
//		this.userApks = userApks;
//	}


	public String getUserApks() {
		return userApks;
	}

	public void setUserApks(String userApks) {
		this.userApks = userApks;
	}
}
