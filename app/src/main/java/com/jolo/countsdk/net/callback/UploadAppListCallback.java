package com.jolo.countsdk.net.callback;

import android.support.annotation.NonNull;

import com.jolo.countsdk.dao.AdvMsgListDao;
import com.jolo.countsdk.net.BaseNetData;
import com.jolo.countsdk.net.BaseNetUtil;
import com.jolo.countsdk.net.bean.InstallPkg;
import com.jolo.countsdk.util.SLog;

import java.util.List;

/**
 * Description:
 * Created by duzhiqi on 2016/11/10.
 */
public class UploadAppListCallback implements BaseNetUtil.Callbacks {
    private List<InstallPkg> pkgs;
    private AdvMsgListDao dao;

    public UploadAppListCallback(List<InstallPkg> list, AdvMsgListDao dao) {
        pkgs = list;
        this.dao = dao;
    }

    @Override
    public void onFailed() {
        SLog.e("Debug",   "UploadAppListCallback-->onFailed网络请求失败");
    }

    @Override
    public void onError(Exception e) {
        SLog.e("Debug", "UploadAppListCallback-->onError网络请求失败");
    }

    @Override
    public void onNetError() {
        SLog.e("Debug", "无网络连接");
    }

    @Override
    public void onSuccess(@NonNull BaseNetData result) {
        SLog.i("Debug", "请求成功返回");
        dao.updatePkgMark(pkgs);
    }

}
