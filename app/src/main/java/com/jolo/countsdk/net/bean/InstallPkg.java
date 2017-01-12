package com.jolo.countsdk.net.bean;

/**
 * Description:
 * Created by duzhiqi on 2016/12/27.
 */

public class InstallPkg {
    private String pkgName;
    private String versionName;
    private int versionCode;
    private int isMark;// 1-->true -1--->false

    public int isMark() {
        return isMark;
    }

    public void setMark(int mark) {
        isMark = mark;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    @Override
    public String toString() {
        return "InstallPkg{" +
                "pkgName='" + pkgName + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                '}';
    }
}
