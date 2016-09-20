package com.advance.android.sdk.http;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * 1.同步request
 * 2.异步request
 *
 *
 * 第一步:获取Call对象
 * 第二步:封装request
 *
 * 作者: shuaizhimin
 * 描述:
 * 日期: 2016-09-20
 * 时间: 17:07
 * 版本:
 *
 */
public class BaseRequest{
//    OkHttpClient.Builder mBuilder=new OkHttpClient.Builder().build().newBuilder().build().newCall(request);
//
//
//    OkHttpClient client = new OkHttpClient();
//    String run(String url) throws IOException {
//        Request request = new Request.Builder().url(url).build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });
//
//        if (response.isSuccessful()) {
//            return response.body().string();
//        } else {
//            throw new IOException("Unexpected code " + response);
//        }
//    }


}
