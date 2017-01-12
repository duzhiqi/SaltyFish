package com.jolo.countsdk.dao;

/**
 * Description:
 * Created by dzq on 2016/10/18.
 */

public interface AdvListDBInfo {
    int first_version_db = 0x001;
    int DB_VERSION_CODE = first_version_db;

    int INIT_VERSION = AdvListDBInfo.DB_VERSION_CODE;
    String DB_NAME = "adv_sdk.db";
    /**
     * 1=第三方顶部；
     */
    String TABLE_INSTALLED_PKG = "installedPkg";

    /***TABLE_INSTALLED_PKG****/
    String COLUMN_PKG_NAME = "pkgName";
    String COLUMN_VERSION_CODE = "versionCode";
    String COLUMN_VERSION_NAME = "versionName";
    String COLUMN_IS_MARK = "isMark";//被标记为true的 就不用再向服务器发送
}
