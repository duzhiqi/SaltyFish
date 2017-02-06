package com.jolo.countsdk.net.task;

import android.content.Context;

import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.dao.AdvMsgListDao;
import com.jolo.countsdk.net.bean.ClientInfo;
import com.jolo.countsdk.net.bean.InstallPkg;
import com.jolo.countsdk.net.callback.UploadAppListCallback;
import com.jolo.countsdk.net.impl.UploadUserAppListNetUtil;
import com.jolo.countsdk.util.DateUtil;
import com.jolo.countsdk.util.SLog;
import com.jolo.countsdk.util.SharedPreferencesUtil;
import com.jolo.countsdk.util.VersionUtil;

import java.util.List;

/**
 * Description:
 * Created by duzhiqi on 2016/11/9.
 */

public class HeartBreakPostTask implements Runnable {
    private Context mContext;
    private final static String TAG = HeartBreakPostTask.class.getSimpleName();
    private static final long DEFAULT_POST_TIME = 1000 * 3600 * 12;
    private AdvMsgListDao dao;

    public HeartBreakPostTask(Context mContext) {
        this.mContext = mContext.getApplicationContext();
        this.dao = new AdvMsgListDao(mContext);
    }

    @Override
    public void run() {
        SLog.d(TAG, "HeartBreakPostTask:" + DateUtil.getTime());
        while (flag) {
            SLog.d(TAG, "HeartBreakPostTask2:" + DateUtil.getTime());
            ClientInfo.initGaid(mContext);
            UploadUserAppListNetUtil.init(mContext);
            List<InstallPkg> appNameList = VersionUtil.getAppNameList(mContext);
            dao.openDatabase();
            dao.add2InstalledPkg(appNameList);
            String appNameStr = VersionUtil.getAppNameStr(dao.get20Pkgs());
            UploadUserAppListNetUtil net = new UploadUserAppListNetUtil(mContext);
            net.uploadAppList(appNameStr, new UploadAppListCallback(appNameList,dao));
            SLog.d(TAG, "HeartBreakPostTask:" + DateUtil.getTime());
            try {
                Thread.sleep(SharedPreferencesUtil.getLong(mContext, SPConstants.KEY_USERAPKS_TIMEINTERVAL, DEFAULT_POST_TIME));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setFlagTrue() {
        flag = true;
    }

    public static void setFlagFalse() {
        flag = false;
    }

    private static boolean flag = true;
}
