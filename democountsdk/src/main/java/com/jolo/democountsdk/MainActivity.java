package com.jolo.democountsdk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.jolo.countsdk.CountSDK;
import com.jolo.countsdk.util.LocationUtil;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationUtil.initLocation(this);
        CountSDK.setDebugTrue();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountSDK.initCountSDKConfig(MainActivity.this);

            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountSDK.init(MainActivity.this);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

