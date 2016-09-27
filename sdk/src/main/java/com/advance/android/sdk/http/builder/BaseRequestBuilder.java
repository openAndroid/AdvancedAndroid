package com.advance.android.sdk.http.builder;

import com.advance.android.sdk.http.BaseRequest;
import com.advance.android.sdk.http.call.AACallBack;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * RequestBuilder 类 用来拼接参数
 * 作者: shuaizhimin
 * 描述:
 * 日期: 2016-09-23
 * 时间: 18:55
 * 版本:
 */
public abstract  class BaseRequestBuilder<T> {
    protected OkHttpClient okHttpClient;
    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;
    protected int id;
    protected RequestBody mRequestBody;

    public BaseRequestBuilder(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public T url(String url) {
        this.url = url;
        return (T) this;
    }

    public T tag(Object tag) {
        this.tag = tag;
        return (T) this;
    }

    public T headers(Map<String, String> headers) {
        this.headers = headers;
        return (T) this;
    }

    public T addHeader(String key, String value) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        //map键 值 都可为空
        headers.put(key, value);
        return (T) this;
    }

    public T params(Map<String, String> params) {
        this.params = params;
        return (T) this;
    }

    public T addParam(String key, String value) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        //map键 值 都可为空
        params.put(key, value);
        return (T) this;
    }
    public T okHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return (T)this;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public abstract RequestBody generateRequestBody();
    public abstract Request generateRequest(RequestBody requestBody);
    public void excute() {
        new BaseRequest(this).excute();
    }
    public void excute(AACallBack callBack) {
        new BaseRequest(this).excute(callBack);
    }

}
