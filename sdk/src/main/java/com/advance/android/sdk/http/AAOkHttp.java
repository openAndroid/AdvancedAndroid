package com.advance.android.sdk.http;

import com.advance.android.sdk.http.builder.GetBuilder;
import com.advance.android.sdk.http.builder.PostBuilder;
import com.advance.android.sdk.http.util.DefaultHostNameVerifier;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * TODO
 * 1.post 请求
 * 2.异常处理
 * 3.cookie处理
 * 4.缓存
 * 作者: shuaizhimin
 * 描述:
 * 日期: 2016-09-23
 * 时间: 19:02
 * 版本:
 */
public class AAOkHttp extends OkHttpClient {
    public static final int TIME_OUT = 60000;//超时时间
    private OkHttpClient.Builder okHttpBuilder = this.newBuilder();

    public AAOkHttp() {
        okHttpBuilder = this.newBuilder();
        okHttpBuilder.hostnameVerifier(new DefaultHostNameVerifier());
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS);
        okHttpBuilder.readTimeout(TIME_OUT, TimeUnit.MILLISECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS);
    }

    public static AAOkHttp getInstance() {
        return AAOkHttpHolder.instance;
    }

    public GetBuilder doGet() {
        return new GetBuilder(this);
    }

    public PostBuilder doPost() {
        return new PostBuilder(this);
    }

    /**
     * 取消请求 需在设置的时候builder tag
     *
     * @param tag
     */
    public void cancelTag(Object tag) {
        for (Call call : this.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : this.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }


    private static class AAOkHttpHolder {
        private static AAOkHttp instance = new AAOkHttp();
    }
}
