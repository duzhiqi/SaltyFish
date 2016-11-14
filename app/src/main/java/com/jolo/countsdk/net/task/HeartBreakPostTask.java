package com.jolo.countsdk.net.task;

import android.content.Context;
import android.util.Log;

import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.net.callback.UploadAppListCallback;
import com.jolo.countsdk.net.impl.UploadUserAppListNetUtil;
import com.jolo.countsdk.util.DateUtil;
import com.jolo.countsdk.util.SharedPreferencesUtil;
import com.jolo.countsdk.util.VersionUtil;

/**
 * Description:
 * Created by duzhiqi on 2016/11/9.
 */

public class HeartBreakPostTask implements Runnable {
    private Context mContext;
    private String TAG="HeartBreakPostTask";
    private static final long DEFAULT_POST_TIME = 1000 * 3600 * 2;

    public HeartBreakPostTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void run() {
        while (true){
            UploadUserAppListNetUtil.init(mContext);
            UploadUserAppListNetUtil net = new UploadUserAppListNetUtil(mContext);
            String apps = VersionUtil.getAppNameStr(mContext);
            net.uploadAppList(apps, new UploadAppListCallback(mContext));
            Log.d(TAG, "HeartBreakPostTask:" + DateUtil.getTime());
            try {
                Thread.sleep(SharedPreferencesUtil.getLong(mContext, SPConstants.KEY_USERAPKS_TIMEINTERVAL, DEFAULT_POST_TIME));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
