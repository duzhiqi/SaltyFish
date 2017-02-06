package com.jolo.countsdk.net.task;

import android.content.Context;

import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.net.bean.ClientInfo;
import com.jolo.countsdk.net.callback.SdkConfigCallback;
import com.jolo.countsdk.net.impl.GetAdSdkConfigNetUtil;
import com.jolo.countsdk.util.DateUtil;
import com.jolo.countsdk.util.SLog;
import com.jolo.countsdk.util.SharedPreferencesUtil;

/**
 * Description:
 * Created by duzhiqi on 2016/11/9.
 */

public class GetSdkConfigTask implements Runnable {
    private Context mContext;
    private static final String TAG = "Debug";
    private static final long DEFAULT_REQUEST_TIME = 1000 * 60 * 5;
//    private boolean noRepeatRequest = true;

    public GetSdkConfigTask(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

//    private Handler handler =  new Handler();

    @Override
    public void run() {
        while (flag){
            SLog.d(TAG, "GetSdkConfigTask: do once request, " + DateUtil.getTime());
            ClientInfo.initGaid(mContext);
            GetAdSdkConfigNetUtil.init(mContext);
            GetAdSdkConfigNetUtil net = new GetAdSdkConfigNetUtil(mContext,
                    SharedPreferencesUtil.getInt(mContext, SPConstants.KEY_BLACK_PKGSVER, 0));
//            if (noRepeatRequest) {
//                noRepeatRequest = false;
//                net.getAdSdkConfig(new SdkConfigCallback(mContext));
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        noRepeatRequest = true;
//                    }
//                }, 10*1000);
//            }
            net.getAdSdkConfig(new SdkConfigCallback(mContext));

            try {
                Thread.sleep(SharedPreferencesUtil.getLong(mContext, SPConstants.KEY_ADSDKCONFIG_TIMEINTERVAL, DEFAULT_REQUEST_TIME));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setFlagTrue(){
        flag = true;
    }

    public static void setFlagFalse(){
        flag = false;
    }

    private static boolean flag = true;
}
