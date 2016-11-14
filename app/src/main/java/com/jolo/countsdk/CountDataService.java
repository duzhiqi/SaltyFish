package com.jolo.countsdk;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

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
 * Description:
 * Created by duzhiqi on 2016/11/9.
 */

public class CountDataService extends Service {

    private Context context = this;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(3);
//        scheduExec.schedule(new GetSdkConfigTask(context), 5000, TimeUnit.MILLISECONDS);
//        scheduExec.schedule(new HeartBreakPostTask(context), 1000 * 60 * 2, TimeUnit.MILLISECONDS);
        CountSDK.initCountSDKConfig(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CountSDK.releaseCountSDKConfig(this);
    }


}
