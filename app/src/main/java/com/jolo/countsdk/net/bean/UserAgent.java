package com.jolo.countsdk.net.bean;

import java.io.Serializable;

/**
  * <p>Title: UserAgent</p>
  * <p>Description: 用户手机基本信息即UA</p>
  * <p>Company: jolo</p> 
  * @author hw
  * @date 2016-12-15 下午9:42:38
 */
public class UserAgent implements Serializable {

	private static final long serialVersionUID = 4185384665421644824L;

	//手机基本信息
	private String imsi; //imsi
	private String imei; //imei
	private String androidSystemVer; //android系统版本号
	private String screenSize;	//分辨率
	private Integer ramSize;	
	private Integer romSize;		
	private String cpu; //cpu
	private String hsman; //厂商
	private String hstype; //机型
	private Byte networkType; //网络类型
	private String provider; //运营商
	private String packegeName; //终端包名
	private String apkVer; //终端版本名称
	private Short dpi; //屏幕密度
	private Integer apkverInt; //终端版本号
	private String mac; //mac值
	//每次请求公共参数
	private Long installTime; //安装时间
	private String terminalId; //服务端下发的
	private String channelCode; //渠道号
	private String lat; //纬度
	private String lng; //经度
	
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getAndroidSystemVer() {
		return androidSystemVer;
	}
	public void setAndroidSystemVer(String androidSystemVer) {
		this.androidSystemVer = androidSystemVer;
	}
	public String getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}
	public Integer getRamSize() {
		return ramSize;
	}
	public void setRamSize(Integer ramSize) {
		this.ramSize = ramSize;
	}
	public Integer getRomSize() {
		return romSize;
	}
	public void setRomSize(Integer romSize) {
		this.romSize = romSize;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getHsman() {
		return hsman;
	}
	public void setHsman(String hsman) {
		this.hsman = hsman;
	}
	public String getHstype() {
		return hstype;
	}
	public void setHstype(String hstype) {
		this.hstype = hstype;
	}
	public Byte getNetworkType() {
		return networkType;
	}
	public void setNetworkType(Byte networkType) {
		this.networkType = networkType;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getPackegeName() {
		return packegeName;
	}
	public void setPackegeName(String packegeName) {
		this.packegeName = packegeName;
	}
	public String getApkVer() {
		return apkVer;
	}
	public void setApkVer(String apkVer) {
		this.apkVer = apkVer;
	}
	public Short getDpi() {
		return dpi;
	}
	public void setDpi(Short dpi) {
		this.dpi = dpi;
	}
	public Integer getApkverInt() {
		return apkverInt;
	}
	public void setApkverInt(Integer apkverInt) {
		this.apkverInt = apkverInt;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public Long getInstallTime() {
		return installTime;
	}
	public void setInstallTime(Long installTime) {
		this.installTime = installTime;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
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

	@Override
	public String toString() {
		return "UserAgent{" +
				"imsi='" + imsi + '\'' +
				", imei='" + imei + '\'' +
				", androidSystemVer='" + androidSystemVer + '\'' +
				", screenSize='" + screenSize + '\'' +
				", ramSize=" + ramSize +
				", romSize=" + romSize +
				", cpu='" + cpu + '\'' +
				", hsman='" + hsman + '\'' +
				", hstype='" + hstype + '\'' +
				", networkType=" + networkType +
				", provider='" + provider + '\'' +
				", packegeName='" + packegeName + '\'' +
				", apkVer='" + apkVer + '\'' +
				", dpi=" + dpi +
				", apkverInt=" + apkverInt +
				", mac='" + mac + '\'' +
				", installTime=" + installTime +
				", terminalId='" + terminalId + '\'' +
				", channelCode='" + channelCode + '\'' +
				", lat='" + lat + '\'' +
				", lng='" + lng + '\'' +
				'}';
	}
}
