package com.jolo.countsdk.util;

import android.util.Log;

import com.jolo.countsdk.config.Config;

/**
 * Description: Debug日志
 * Created by duzhiqi on 2016/11/15.
 */

public class SLog {

    public static void i(String tag, String msg){
        if (Config.isDebug){
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg){
        if (Config.isDebug){
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg){
        if (Config.isDebug){
            Log.d(tag, msg);
        }
    }
}
