package com.advance.android.sdk.http;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者: shuaizhimin
 * 描述: okhttp call改造类
 * 日期: 2016-09-20
 * 时间: 17:51
 * 版本:
 */
public class AACall implements Call {

    @Override
    public Request request() {
        return null;
    }

    @Override
    public Response execute() throws IOException {
        return null;
    }

    @Override
    public void enqueue(Callback responseCallback) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }
}
