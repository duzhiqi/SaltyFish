package com.jolo.countsdk.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.jolo.countsdk.dao.AdvListDBInfo.TABLE_INSTALLED_PKG;


/**
 * Description: AdvSdkSqlHelper
 * Created by dzq on 2016/10/18.
 */

class AdvSdkSqlHelper extends SQLiteOpenHelper {

    private AdvSdkSqlHelper(Context context, String name, int version) {
        super(context, name, null, version);

    }

    private static volatile AdvSdkSqlHelper helper;

    public static AdvSdkSqlHelper getInstance(Context context, String name, int version) {
        if (helper == null) {
            synchronized (AdvSdkSqlHelper.class) {
                if (helper == null) {
                    helper = new AdvSdkSqlHelper(context, name, version);
                }
            }
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_installed_pkg = "create table if not exists " + TABLE_INSTALLED_PKG +
                " (_id integer primary key autoincrement, " +
                AdvListDBInfo.COLUMN_PKG_NAME + " text, " +
                AdvListDBInfo.COLUMN_VERSION_NAME + " text, " +
                AdvListDBInfo.COLUMN_VERSION_CODE + " integer, " +
                AdvListDBInfo.COLUMN_IS_MARK + " integer)";

        db.execSQL(sql_installed_pkg);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql_installed_pkg = " drop table if exists " + TABLE_INSTALLED_PKG;
        db.execSQL(sql_installed_pkg);
        onCreate(db);
    }
}
