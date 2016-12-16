package com.jolo.countsdk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jolo.countsdk.util.LocationUtil;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationUtil.initLocation(MainActivity.this);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationUtil.initLocation(MainActivity.this);
                Toast.makeText(MainActivity.this, "latitude:" + LocationUtil.latitude + ", longitude:" + LocationUtil.longitude, Toast.LENGTH_SHORT).show();
                Log.i("MainActivity", "latitude:" + LocationUtil.latitude + ", longitude:" + LocationUtil.longitude);
            }
        });

        CountSDK.init(this);
    }

}
