package com.jolo.democountsdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.jolo.countsdk.CountSDK;

/**
 * Description:
 * Created by duzhiqi on 2016/11/15.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CountSDK.setDebugTrue();
        CountSDK.initCountSDKConfig(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CountSDK.releaseCountSDKConfig(this);
    }
}
