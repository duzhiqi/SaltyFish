package com.jolo.countsdk.net.task;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.jolo.countsdk.net.BaseNetData;
import com.jolo.countsdk.net.BaseNetUtil;
import com.jolo.countsdk.net.callback.UploadAppListCallback;
import com.jolo.countsdk.net.impl.UploadUserAppListNetUtil;
import com.jolo.countsdk.util.VersionUtil;

/**
 * Description:
 * Created by duzhiqi on 2016/11/9.
 */

public class HeartBreakPostTask implements Runnable {
    private Context mContext;

    public HeartBreakPostTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void run() {
        UploadUserAppListNetUtil.init(mContext);
        UploadUserAppListNetUtil net = new UploadUserAppListNetUtil(mContext);
        String apps = VersionUtil.getAppNameStr(mContext);
        net.uploadAppList(apps, new UploadAppListCallback(mContext));
    }
}
