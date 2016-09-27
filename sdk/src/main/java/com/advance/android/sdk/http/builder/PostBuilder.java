package com.advance.android.sdk.http.builder;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 作者: shuaizhimin
 * 描述:
 * 日期: 2016-09-24
 * 时间: 16:48
 * 版本:
 */
public class PostBuilder extends BaseRequestBuilder<PostBuilder>{
    public PostBuilder(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    @Override
    public RequestBody generateRequestBody() {
        return new FormBody.Builder().build();
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        return new Request.Builder().post(requestBody).url(url).tag(tag).build();
    }
}
