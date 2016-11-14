package com.jolo.countsdk.net.task;

import android.content.Context;
import android.util.Log;

import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.net.callback.SdkConfigCallback;
import com.jolo.countsdk.net.impl.GetAdSdkConfigNetUtil;
import com.jolo.countsdk.util.DateUtil;
import com.jolo.countsdk.util.SharedPreferencesUtil;

/**
 * Description:
 * Created by duzhiqi on 2016/11/9.
 */

public class GetSdkConfigTask implements Runnable {
    private Context mContext;
    private String TAG = "GetSdkConfigTask";
    private static final long DEFAULT_REQUEST_TIME = 1000 * 3600 * 24;

    public GetSdkConfigTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void run() {

        while (true){
            GetAdSdkConfigNetUtil.init(mContext);
            GetAdSdkConfigNetUtil net = new GetAdSdkConfigNetUtil(mContext,
                    SharedPreferencesUtil.getInt(mContext, SPConstants.KEY_BLACK_PKGSVER, 0));
            net.getAdSdkConfig(new SdkConfigCallback(mContext));
            Log.d(TAG, "GetSdkConfigTask:" + DateUtil.getTime());
            try {
                Thread.sleep(SharedPreferencesUtil.getLong(mContext, SPConstants.KEY_ADSDKCONFIG_TIMEINTERVAL, 1000*60));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
