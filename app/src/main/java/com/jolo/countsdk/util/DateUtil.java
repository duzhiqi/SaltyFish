package com.jolo.countsdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * Created by duzhiqi on 2016/11/14.
 */

public class DateUtil {

    public static String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return format.format(date);
    }
}
