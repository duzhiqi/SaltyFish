package com.jolo.democountsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Description:
 * Created by duzhiqi on 2016/12/29.
 */

public class MyReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)){
            String dataString = intent.getDataString();
            Log.e("dzq", "packageName:"+dataString);
        } else if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)){
            String dataString = intent.getDataString();
            Log.e("dzq", "packageName:"+dataString);
        }
    }
}
