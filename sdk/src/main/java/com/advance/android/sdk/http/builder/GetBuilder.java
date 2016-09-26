package com.advance.android.sdk.http.builder;

import android.net.Uri;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 作者: shuaizhimin
 * 描述:
 * 日期: 2016-09-24
 * 时间: 15:07
 * 版本:
 */
public class GetBuilder extends BaseRequestBuilder<GetBuilder>{
    public GetBuilder(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    @Override
    public RequestBody generateRequestBody() {
        return null;
    }

    @Override
    public Request generateRequest(RequestBody requestBody) {
        Request.Builder builder = new Request.Builder();
        if (params != null) {
            url = appendParams(url, params);
        }
        builder.get().url(url).tag(tag).build();
        return builder.build();
    }

    private String appendParams(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }
}
