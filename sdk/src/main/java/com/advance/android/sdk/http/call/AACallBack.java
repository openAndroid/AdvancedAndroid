package com.advance.android.sdk.http.call;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者: shuaizhimin
 * 描述:
 * 日期: 2016-09-24
 * 时间: 10:25
 * 版本:
 */
public abstract class AACallBack<T> {

    public abstract T parseNetworkResponse(Response response) throws Exception;

    /**
     * 数据返回操作
     * @param t
     * @param call
     * @param response
     */
    public abstract void onSuccess(T t, Call call, Response response);

    public static final AACallBack CALLBACK_DEFAULT = new AACallBack() {

        @Override
        public Response parseNetworkResponse(Response response) throws Exception {
            return response;
        }

        @Override
        public void onSuccess(Object data, Call call, Response response) {
        }
    };

}
