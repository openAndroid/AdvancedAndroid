package com.advance.android.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.advance.android.R;
import com.advance.android.sdk.http.AAOkHttp;
import com.advance.android.sdk.http.call.StringCallback;
import com.advance.android.sdk.utils.AALog;

import okhttp3.Call;
import okhttp3.Response;

public class HttpTestActivity extends AppCompatActivity {
    Button mGetTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);
        findViewById(R.id.mGetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doGet();
            }
        });
        findViewById(R.id.mPostButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPost();
            }
        });
    }

    void doGet(){
        AAOkHttp.getInstance().doGet().url("http://m.zhuomuniao.cc/ZMNVaccine/getFocusArticle").excute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                AALog.e("get",""+s);
            }
        });
    }
    void doPost(){
        AAOkHttp.getInstance().doPost().url("http://m.zhuomuniao.cc/ZMNVaccine/getVaccines").excute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                AALog.e("post",""+s);
            }
        });
    }
}
