package com.advance.android.sdk.http;

import okhttp3.OkHttpClient;

/**
 * ——————————————————————————————————
 * 作者: shuaizhimin www.shuaizm.com
 * 描述: okHttp 入口
 * 日期: 2016-09-20
 * 时间: 16:25
 * ——————————————————————————————————
 */
public class AAOkHttpClient {
    private static AAOkHttpClient mInstance;
    private OkHttpClient mOkHttpClient=new OkHttpClient();

    public AAOkHttpClient() {
    }

    /**
     * 通过静态内部类实现线程安全的单例模式
     *
     * @return
     */
    public static AAOkHttpClient getInstance() {
        return AAOkHttpClientHolder.instance;
    }

    public static class AAOkHttpClientHolder {
        private static AAOkHttpClient instance = new AAOkHttpClient();
    }


    public void doGet() {

    }

    public void doPost() {

    }

    public void doHead() {

    }

    public void doDelete() {

    }

    public void doOptions() {

    }
}
