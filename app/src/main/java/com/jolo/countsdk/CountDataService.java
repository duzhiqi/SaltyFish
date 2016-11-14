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
        registerReceiver();
        ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(3);
        scheduExec.schedule(new GetSdkConfigTask(context), 5000, TimeUnit.MILLISECONDS);
        scheduExec.schedule(new HeartBreakPostTask(context), 1000 * 60 * 2, TimeUnit.MILLISECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    class NetWorkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
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

    private NetWorkChangeReceiver myReceiver;

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        myReceiver = new NetWorkChangeReceiver();
        this.registerReceiver(myReceiver, filter);
    }

    private void unregisterReceiver() {
        this.unregisterReceiver(myReceiver);
    }
}
