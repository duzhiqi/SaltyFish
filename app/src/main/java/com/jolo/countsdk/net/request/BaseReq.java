package com.jolo.countsdk.net.request;


import com.jolo.countsdk.net.bean.UserAgent;

/**
 * 
  * <p>Title: BaseReq</p>
  * <p>Description:</p>
  * <p>Company: jolo</p> 
  * @author hw
  * @date 2016-12-16 上午2:20:33
 */
public abstract class BaseReq {
	
	private UserAgent userAgent;

	private Integer businessCode;

	public Integer getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(Integer businessCode) {
		this.businessCode = businessCode;
	}

	@Override
	public String toString() {
		return "BaseReq{" +
				"userAgent=" + userAgent +
				", businessCode=" + businessCode +
				'}';
	}

	public UserAgent getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(UserAgent userAgent) {
		this.userAgent = userAgent;
	}

}
