package com.jolo.countsdk.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Description:
 * Created by dzq on 2016/10/17.
 */

public class VersionUtil {

    /**获取失败*/
    public static final int FAILED_GET_CODE = -1;

    /**
     * 获取app的VersionName
     * @param context
     * @return
     */
    public static String getVersionName(Context context){
        //PackageInfo
        //PackageManager 包管理器
        // step 1 . 获取包管理器对象
        PackageManager packageManager = context.getPackageManager();
        // step 2 . 通过包管理器对象获取指定包的PackageInfo对象
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getVersionName(Context context, String packageName){
        //PackageInfo
        //PackageManager 包管理器
        // step 1 . 获取包管理器对象
        PackageManager packageManager = context.getPackageManager();
        // step 2 . 通过包管理器对象获取指定包的PackageInfo对象
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取app的VersionCode
     * @param context
     * @return
     */
    public static int getVersionCode(Context context){
        //PackageInfo
        //PackageManager 包管理器
        // step 1 . 获取包管理器对象
        PackageManager packageManager = context.getPackageManager();
        // step 2 . 通过包管理器对象获取指定包的PackageInfo对象
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return FAILED_GET_CODE;
    }

    public static int getVersionCode(Context context, String packageName){
        PackageManager packageManager = context.getPackageManager();
        // step 2 . 通过包管理器对象获取指定包的PackageInfo对象
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);

            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return FAILED_GET_CODE;
    }

    /**
     * 第一次安装App时间
     * @param context
     * @return
     */
    public static long getFirstInstallAppTime(Context context){
        //PackageInfo
        //PackageManager 包管理器
        // step 1 . 获取包管理器对象
        PackageManager packageManager = context.getPackageManager();
        // step 2 . 通过包管理器对象获取指定包的PackageInfo对象
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            long firstInstallTime = packageInfo.firstInstallTime;
            return firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return FAILED_GET_CODE;
    }

    public static String getAppNameStr(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> infos = pm.getInstalledPackages(0);
        String apps = "";
        for (PackageInfo info : infos) {
            ApplicationInfo applicationInfo = info.applicationInfo;
            if (isUserApp(applicationInfo.flags)) {
                apps += info.packageName + " ," + info.versionName + " ," + info.versionCode + ";";
            }
        }
        return apps;
    }

    public static boolean isUserApp(int flags) {
        return (flags & ApplicationInfo.FLAG_SYSTEM) == 0;
    }
}
