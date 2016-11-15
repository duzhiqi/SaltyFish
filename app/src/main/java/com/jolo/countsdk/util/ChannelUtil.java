package com.jolo.countsdk.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.util.Log;

import com.jolo.countsdk.CountDataService;
import com.jolo.countsdk.config.Config;

/**
 * Description:
 * Created by duzhiqi on 2016/11/10.
 */

public class ChannelUtil {

    public static String getSDKChannel(Context context) {
        ComponentName cn = new ComponentName(context, CountDataService.class);
        ServiceInfo info;
        try {
            info = context.getPackageManager()
                    .getServiceInfo(cn, PackageManager.GET_META_DATA);
            return info.metaData.getString("channel");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//        Log.d(TAG, " msg == " + msg );
        return Config.DEFAULT_CHANNEL;
    }

}
