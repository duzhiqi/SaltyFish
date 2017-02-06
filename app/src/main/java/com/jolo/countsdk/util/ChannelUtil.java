package com.jolo.countsdk.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;

import com.jolo.countsdk.CountDataService;
import com.jolo.countsdk.config.Config;

/**
 * Description:
 * Created by duzhiqi on 2016/11/10.
 */

public class ChannelUtil {

    public static final String CHANNEL_KEY = "count_sdk_channel";

    public static String getSDKChannel(Context context) {
        ComponentName cn = new ComponentName(context, CountDataService.class);
        ServiceInfo info;
        try {
            info = context.getPackageManager()
                    .getServiceInfo(cn, PackageManager.GET_META_DATA);
            return info.metaData.getString(CHANNEL_KEY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return Config.DEFAULT_CHANNEL;
    }

    public static String readSDKChannel(Context context){

        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
//            Bundle bundle = appInfo.metaData;
//            String str = bundle.getString(CHANNEL_KEY);
            return appInfo.metaData.getString(CHANNEL_KEY, Config.DEFAULT_CHANNEL);
//            return  str;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return Config.DEFAULT_CHANNEL;

    }
}
