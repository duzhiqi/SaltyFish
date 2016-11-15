package com.jolo.countsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jolo.countsdk.config.Config;
import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.net.BaseNetUtil;
import com.jolo.countsdk.net.callback.SdkConfigCallback;
import com.jolo.countsdk.net.callback.UploadAppListCallback;
import com.jolo.countsdk.net.impl.GetAdSdkConfigNetUtil;
import com.jolo.countsdk.net.impl.UploadUserAppListNetUtil;
import com.jolo.countsdk.net.task.GetSdkConfigTask;
import com.jolo.countsdk.net.task.HeartBreakPostTask;
import com.jolo.countsdk.util.SharedPreferencesUtil;
import com.jolo.countsdk.util.VersionUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description: 初始化统计SDK
 * Created by duzhiqi on 2016/11/9.
 */

public class CountSDK {

    public static void init(Context ctx){
        Context context = ctx.getApplicationContext();
        Intent service = new Intent(context, CountDataService.class);
        context.startService(service);
    }

    public static void initCountSDKConfig(Context context){
        ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(3);
        scheduExec.schedule(new GetSdkConfigTask(context), 5000, TimeUnit.MILLISECONDS);
        scheduExec.schedule(new HeartBreakPostTask(context), 1000 * 60 * 2, TimeUnit.MILLISECONDS);
        registerReceiver(context);
    }

    public static void releaseCountSDKConfig(Context context){
        unregisterReceiver(context);
        GetSdkConfigTask.setFlagFalse();
        HeartBreakPostTask.setFlagFalse();
    }

    public static void setDebugTrue(){
        Config.isDebug = true;
    }


    static class NetWorkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                BaseNetUtil.init(context);
                GetAdSdkConfigNetUtil net = new GetAdSdkConfigNetUtil(context,
                        SharedPreferencesUtil.getInt(context, SPConstants.KEY_BLACK_PKGSVER, 0));
                net.getAdSdkConfig(new SdkConfigCallback(context));

                UploadUserAppListNetUtil uploadUserAppListNet = new UploadUserAppListNetUtil(context);
                String apps = VersionUtil.getAppNameStr(context);
                uploadUserAppListNet.uploadAppList(apps, new UploadAppListCallback(context));
            }
        }
    }

    private static NetWorkChangeReceiver myReceiver;

    private static void registerReceiver(Context context) {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        myReceiver = new NetWorkChangeReceiver();
        context.registerReceiver(myReceiver, filter);
    }

    private static void unregisterReceiver(Context context) {
        context.unregisterReceiver(myReceiver);
    }
}
