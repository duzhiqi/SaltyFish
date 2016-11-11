package com.jolo.countsdk;

import android.content.Context;
import android.content.Intent;

/**
 * Description: 初始化统计SDK
 * Created by duzhiqi on 2016/11/9.
 */

public class CountSDK {

    public static void init(Context ctx){
        Context context = ctx.getApplicationContext();
        Intent service = new Intent(context, CountDataService.class);
        context.startService(service);
    }
}
