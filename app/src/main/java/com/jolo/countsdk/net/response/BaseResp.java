package com.jolo.countsdk.net.response;

/**
 * 
  * <p>Title: BaseResp</p>
  * <p>Description:</p>
  * <p>Company: jolo</p> 
  * @author hw
  * @date 2016-12-16 上午2:24:32
 */
public class BaseResp {
	
	private Integer responseCode;

	private String responseMsg;

	@Override
	public String toString() {
		return "BaseResp{" +
				"responseCode=" + responseCode +
				", responseMsg='" + responseMsg + '\'' +
				'}';
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	
	
}
