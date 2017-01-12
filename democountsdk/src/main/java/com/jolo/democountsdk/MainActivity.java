package com.jolo.democountsdk;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.jolo.countsdk.CountSDK;
import com.jolo.countsdk.config.Config;
import com.jolo.countsdk.config.SPConstants;
import com.jolo.countsdk.net.OkHttpWrapper;
import com.jolo.countsdk.net.bean.ClientInfo;
import com.jolo.countsdk.net.bean.UserAgent;
import com.jolo.countsdk.net.request.BaseReq;
import com.jolo.countsdk.net.request.GetAdSdkConfigReq;
import com.jolo.countsdk.net.request.UploadUserInsApplistReq;
import com.jolo.countsdk.net.response.BaseResp;
import com.jolo.countsdk.net.response.UploadUserInsApplistResp;
import com.jolo.countsdk.util.Base64;
import com.jolo.countsdk.util.DateUtil;
import com.jolo.countsdk.util.JsonParser;
import com.jolo.countsdk.util.LocationUtil;
import com.jolo.countsdk.util.SharedPreferencesUtil;
import com.jolo.countsdk.util.VersionUtil;

import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationUtil.initLocation(this);

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

