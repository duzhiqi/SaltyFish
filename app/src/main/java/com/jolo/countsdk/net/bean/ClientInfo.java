package com.jolo.countsdk.net.bean;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;


import com.jolo.countsdk.config.Config;
import com.jolo.countsdk.util.ChannelUtil;
import com.jolo.countsdk.util.MCPTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 * 终端信息
 * 
 */
public class ClientInfo {
	public final static int NONET = 0;
	public final static int MOBILE_3G = 1;
	public final static int MOBILE_2G = 2;
	public final static int MOBILE_4G = 4;
	public final static int WIFI = 3;
	// 中国大陆三大运营商imei
	private static final String CHA_IMSI = "46003";
	private static final String CMCC_IMSI_1 = "46000";
	private static final String CMCC_IMSI_2 = "46002";
	private static final String CHU_IMSI = "46001";

	// 中国大陆三大运营商 provider
	private static final String CMCC = "中国移动";
	private static final String CHU = "中国联通";
	private static final String CHA = "中国电信";

	// 未知内容
	public static final String UNKNOWN = "unknown";

	private static ClientInfo instance;

	// 包名
	public String packageName = null;
	// 安卓系统版本号
	public String androidVer = null;
	// SDK 版本//每次发版本,必须修改~
	public String apkVerName = "1.0001";
	// SDK 版本号
	public int apkVerCode = 100001;
	// cpu型号
	public String cpu = null;
	// 厂商
	public String hsman = null;
	// 机型
	public String hstype = null;
	// imei
	public String imei = null;
	// imsi
	public String imsi = null;
	// 运营提供商
	public String provider = null;
	// 网络状态， 网络状态会不停变化，故设置成static，需实时更新
	public static byte networkType;
	// 渠道code
	public String channelCode = Config.DEFAULT_CHANNEL;
	// ram大小
	public int ramSize = 0;
	// rom大小
	public int romSize = 0;
	// 屏幕大小
	public String screenSize = null;
	public int screenWidth = 0;
	public int screenHeight = 0;
	// 屏幕的dpi
	public short dpi = 0;
	// mac地址
	public String mac = null;
	// sd卡大小
	public String sdCardSize = null;

	private ClientInfo(Context context) {
		
		if (context.getPackageName() != null) {
			packageName = context.getPackageName();
		}

		androidVer = Build.VERSION.RELEASE;
		
		cpu = getCpuInfo();
		hsman = Build.MANUFACTURER;// 手机厂商
		hstype = Build.MODEL;// 手机型号

		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		imei = telephonyManager.getDeviceId();
		imsi = telephonyManager.getSubscriberId();

		if (null == imei) {
			imei = UNKNOWN;
		}
		if (null == imsi) {
			imsi = UNKNOWN;
		}
		provider = getProvider(imsi);

		networkType = (byte) getAPNType(context);

		channelCode = ChannelUtil.readSDKChannel(context);

		ramSize = getTotalMemory(context);

		romSize = (int) ((getTotalInternalMemorySize() / 1024) / 1024);

		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		screenWidth = width;
		screenHeight = height;
		
		if (width > height) {
			screenSize = height + "*" + width;
		} else {
			screenSize = width + "*" + height;
		}
		
		dpi = (short) dm.densityDpi;

		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		mac = wifiInfo.getMacAddress();
		if (mac == null) {
			mac = UNKNOWN;
		}

		sdCardSize = getSDCardMemory();
	}

	private String getSDCardMemory() {
		String ret = "0";
		long[] sdCardInfo = new long[2];
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			File sdcardDir = Environment.getExternalStorageDirectory();
			StatFs sf = new StatFs(sdcardDir.getPath());
			long bSize = sf.getBlockSize();
			long bCount = sf.getBlockCount();
			long availBlocks = sf.getAvailableBlocks();

			sdCardInfo[0] = bSize * bCount;// 总大小
			sdCardInfo[1] = bSize * availBlocks;// 可用大小
			ret = String.valueOf((sdCardInfo[0] / 1024) / 1024);
		}
		return ret;
	}

	/**
	 * 取Rom Size
	 * 
	 * @return
	 */
	private long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return totalBlocks * blockSize;
	}

	// 获取IMSI号的供应商
	private String getProvider(String imsi) {

		String provider = UNKNOWN; // 当前sim卡运营商 //3为未知的 或者没有sim卡的比如平板
		if (imsi != null) {
			if (imsi.startsWith(CMCC_IMSI_1) || imsi.startsWith(CMCC_IMSI_2)) {// 中国移动
				provider = CMCC;
			} else if (imsi.startsWith(CHU_IMSI)) {// 中国联通
				provider = CHU;
			} else if (imsi.startsWith(CHA_IMSI)) {// 中国电信
				provider = CHA;
			}
		}
		return provider;
	}

	// 获取手机总内存
	private int getTotalMemory(Context context) {
		String str1 = "/proc/meminfo";
		String str2;
		String[] arrayOfString;
		int initial_memory = 0;
		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(
					localFileReader, 8192);
			if (localBufferedReader != null) {
				str2 = localBufferedReader.readLine();
				if (str2 != null) {
					arrayOfString = str2.split("\\s+");
					initial_memory = Integer.valueOf(arrayOfString[1])
							.intValue();// KB
					localBufferedReader.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return initial_memory / 1024; // MB
	}

	private static int check2GOr3GNet(Context context){

		int mobileNetType = NONET;
		if (null == context) {
			return mobileNetType;
		}
		TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		
		int netWorkType = telMgr.getNetworkType();
		switch (netWorkType) {		
		case TelephonyManager.NETWORK_TYPE_UMTS:
		case TelephonyManager.NETWORK_TYPE_HSDPA:
		case TelephonyManager.NETWORK_TYPE_HSPA:
		case TelephonyManager.NETWORK_TYPE_HSUPA:
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
			mobileNetType = MOBILE_3G;
			break;
		case TelephonyManager.NETWORK_TYPE_UNKNOWN:
		case TelephonyManager.NETWORK_TYPE_IDEN:
		case TelephonyManager.NETWORK_TYPE_1xRTT:
		case TelephonyManager.NETWORK_TYPE_GPRS:
		case TelephonyManager.NETWORK_TYPE_EDGE:
		case TelephonyManager.NETWORK_TYPE_CDMA:
			mobileNetType = MOBILE_2G;
			break;

		case TelephonyManager.NETWORK_TYPE_LTE:
			mobileNetType = MOBILE_4G;
			break;
		default:
			mobileNetType = MOBILE_3G;
			break;
		}

		return mobileNetType;
	
	}
	// 获取当前网络状态
	public static int getAPNType(Context context) {
		int netType = NONET;
		networkType = NONET;
		
		if (null == context) {
			return netType;
		}
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null || (networkInfo.getState() != State.CONNECTED)) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			netType = check2GOr3GNet(context);
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = WIFI;
		} else {
			boolean b = ConnectivityManager.isNetworkTypeValid(nType);
			if (b) {
				netType = MOBILE_3G; // 联通3G就跑这里
			}
		}
		networkType = (byte) netType;
		return netType;
	}

	/**
	 * 取cpu 信息
	 * 
	 * @return
	 */
	private String getCpuInfo() {
		String str1 = "/proc/cpuinfo";
		String str2;
		String[] cpuInfo = { "", "" };
		String[] arrayOfString;
		String ret;
		try {
			FileReader fr = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
			str2 = localBufferedReader.readLine();
			if (null != str2) {
				arrayOfString = str2.split("\\s+");
				for (int i = 2; i < arrayOfString.length; i++) {
					cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
				}
			}

			str2 = localBufferedReader.readLine();
			if (null != str2) {
				arrayOfString = str2.split("\\s+");
				cpuInfo[1] += arrayOfString[2];
			}

			localBufferedReader.close();
		} catch (Exception e) {
		}
		ret = cpuInfo[0];
		return ret;
	}

	public static ClientInfo getInstance(Context ctx) {
		if (null == instance) {
			instance = new ClientInfo(ctx);
		}
		return instance;
	}
	
	/**
	 * 初始化
	 */
	public static ClientInfo initClientInfo(Context ctx){
		return getInstance(ctx);
	}
	
	/**第一次读取后存入SharedPreferences,之后都从SharedPreferences缓存读取*/
	public static String getSDKChannel(Context context, String defValue) {
		String channel;
		channel = getChannelFromSP(context);
		if (!TextUtils.isEmpty(channel) && !channel.equals(defValue)) {
			return channel;
		}
		channel = MCPTool.getChannelId(context, null, defValue);
		writeChannelToSP(context, channel);
		return channel;
	}
	/**写入SharedPreferences*/
	private static void writeChannelToSP(Context context, String channel) {
		SharedPreferences sp = context.getSharedPreferences(Config.FILE_CHANNEL_SP, Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putString(Config.KEY_CHANNEL, channel);
		edit.apply();
	}
	/**从SharedPreferences读取*/
	private static String getChannelFromSP(Context context) {
		SharedPreferences sp = context.getSharedPreferences(Config.FILE_CHANNEL_SP, Context.MODE_PRIVATE);
		return sp.getString(Config.KEY_CHANNEL, "");
	}
}
