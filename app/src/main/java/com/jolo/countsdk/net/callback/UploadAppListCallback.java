package com.jolo.countsdk.net.callback;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jolo.countsdk.net.BaseNetData;
import com.jolo.countsdk.net.BaseNetUtil;
import com.jolo.countsdk.net.impl.UploadUserAppListNetUtil;
import com.jolo.countsdk.util.SLog;
import com.jolo.countsdk.util.VersionUtil;

/**
 * Description:
 * Created by duzhiqi on 2016/11/10.
 */
public class UploadAppListCallback implements BaseNetUtil.Callbacks {
    private Context mContext;

    public UploadAppListCallback(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    @Override
    public void onFailed() {
        SLog.e("Debug", "网络请求失败");
    }

    @Override
    public void onError(Exception e) {
        SLog.e("Debug", "网络请求失败");
    }

    @Override
    public void onNetError() {
        SLog.e("Debug", "无网络连接");
    }

    @Override
    public void onSuccess(@NonNull BaseNetData result) {
        SLog.i("Debug", "请求成功返回");
    }

}
