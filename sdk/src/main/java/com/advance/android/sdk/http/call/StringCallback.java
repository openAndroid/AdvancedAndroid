package com.advance.android.sdk.http.call;

import okhttp3.Response;

/**
 * 作者: shuaizhimin
 * 描述:
 * 日期: 2016-09-24
 * 时间: 15:10
 * 版本:
 */
public abstract class StringCallback extends AACallBack<String> {
    @Override
    public String parseNetworkResponse(Response response) throws Exception {
        if (response != null && response.body() != null) {
            return response.body().string();
        }
        return null;
    }
}
