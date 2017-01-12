package com.jolo.countsdk;

import android.content.Context;
import android.content.Intent;

import com.jolo.countsdk.config.Config;
import com.jolo.countsdk.net.bean.ClientInfo;
import com.jolo.countsdk.net.task.GetSdkConfigTask;
import com.jolo.countsdk.net.task.HeartBreakPostTask;
import com.jolo.countsdk.util.DateUtil;
import com.jolo.countsdk.util.LocationUtil;
import com.jolo.countsdk.util.SLog;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description: 初始化统计SDK
 * Created by duzhiqi on 2016/11/9.
 */

public class CountSDK {

    @Deprecated
    public static void init(Context context) {
        ClientInfo.initGaid(context);
//        Context context = ctx.getApplicationContext();
//        Intent service = new Intent(context, CountDataService.class);
//        context.startService(service);
        GetSdkConfigTask.setFlagTrue();
        HeartBreakPostTask.setFlagTrue();
        ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(3);
        scheduExec.schedule(new GetSdkConfigTask(context), 5000, TimeUnit.MILLISECONDS);
    }

    public static void initCountSDKConfig(Context context) {
        SLog.e("Debug", "time--->" + DateUtil.getTime());
        LocationUtil.initLocation(context);
        ClientInfo.initGaid(context);
        GetSdkConfigTask.setFlagTrue();
        HeartBreakPostTask.setFlagTrue();
        ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(3);
//        scheduExec.schedule(new GetSdkConfigTask(context), 5000, TimeUnit.MILLISECONDS);
//        scheduExec.schedule(new HeartBreakPostTask(context), 1000 * 60 * 2, TimeUnit.MILLISECONDS);
        scheduExec.schedule(new HeartBreakPostTask(context), 1000, TimeUnit.MILLISECONDS);
    }

    public static void releaseCountSDKConfig(Context context) {
        GetSdkConfigTask.setFlagFalse();
        HeartBreakPostTask.setFlagFalse();
    }

    public static void setDebugTrue() {
        Config.isDebug = true;
    }

}
