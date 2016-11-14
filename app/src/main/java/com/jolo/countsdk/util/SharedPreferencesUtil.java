package com.jolo.countsdk.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.net.bean.AdSdkConfig;


/**
 * Description:
 * Created by dzq on 2016/10/18.
 */

public class SharedPreferencesUtil {
    private static SharedPreferences sp;


    public static void put(Context ctx, String key, Integer value) {
        sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (edit == null || value == null) return;
        edit.putInt(key, value);// Maybe throw nullPointerException
        edit.apply();
    }

    public static void put(Context ctx, String key, String value) {
        sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (edit == null || value == null) return;
        edit.putString(key, value);
        edit.apply();
    }

    public static void put(Context ctx, String key, Long value) {
        sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (edit == null || value == null) return;
        edit.putLong(key, value);
        edit.apply();
    }

    public static int getInt(Context ctx, String key, int defValue) {
        sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static String getString(Context ctx, String key, String defValue) {
        sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static long getLong(Context ctx, String key, long defValue) {
        sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        Log.d("dzq", "getLong: " + sp.getLong(key, defValue));
        return sp.getLong(key, defValue);
    }

    public static void putSdkConfig(Context ctx, AdSdkConfig adSdkConfig) {
        put(ctx, SPConstants.KEY_UUID, adSdkConfig.uuid);
        put(ctx, SPConstants.KEY_ADSDK_VER, adSdkConfig.adSdkVer);
        put(ctx, SPConstants.KEY_BLACK_PKGS, adSdkConfig.blackPkgs);
        put(ctx, SPConstants.KEY_SERVERTIME, adSdkConfig.serverTime);
//        put(ctx, SPConstants.KEY_BLACK_PKGSVER, adSdkConfig.blackPkgsVer);
        put(ctx, SPConstants.KEY_USERAPKS_TIMEINTERVAL, adSdkConfig.userApksTimeInterval);
        put(ctx, SPConstants.KEY_ADMSGLIST_TIMEINTERVAL, adSdkConfig.adMsgListTimeInterval);
        put(ctx, SPConstants.KEY_ADSDKCONFIG_TIMEINTERVAL, adSdkConfig.adSdkConfigTimeInterval);
        put(ctx, SPConstants.KEY_TERMINAL_ID, adSdkConfig.terminalId);
        put(ctx, SPConstants.KEY_ADEVENT_TIME_INTERVAL, adSdkConfig.userAdEventTimeInterval);

        if (!TextUtils.isEmpty(adSdkConfig.blackPkgs)) {
            put(ctx, SPConstants.KEY_BLACK_PKGSVER, adSdkConfig.blackPkgsVer);
        }

        if (!TextUtils.isEmpty(adSdkConfig.adSdkUpgradeUrl)) {
            put(ctx, SPConstants.KEY_ADSDK_UPGRADEURL, adSdkConfig.adSdkUpgradeUrl);
        } else {
            put(ctx, SPConstants.KEY_ADSDK_UPGRADEURL, "");
        }
    }

}
