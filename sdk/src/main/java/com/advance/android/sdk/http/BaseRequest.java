package com.advance.android.sdk.http;


import com.advance.android.sdk.http.builder.BaseRequestBuilder;
import com.advance.android.sdk.http.call.AACallBack;
import com.advance.android.sdk.utils.AALog;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 请求操作封装
 * 作者: shuaizhimin
 * 描述:
 * 日期: 2016-09-23
 * 时间: 19:03
 * 版本:
 */
public class BaseRequest<T> {
    public static final String TAG="AAOkHttp";
    protected BaseRequestBuilder mBaseRequestBuilder;
    protected OkHttpClient mOkHttpClient;
    private AACallBack mCallback;

    public BaseRequest(BaseRequestBuilder requestBuilder) {
        this.mBaseRequestBuilder=requestBuilder;

    }
    public void excute(){

    }
    public void excute(final AACallBack<T> callBack){
        mCallback = callBack;
        if (mCallback == null) mCallback = AACallBack.CALLBACK_DEFAULT;
        try {
            if(mBaseRequestBuilder==null) return;
            mOkHttpClient=mBaseRequestBuilder.getOkHttpClient();
            if(mOkHttpClient==null){
                mOkHttpClient=new OkHttpClient();
            }

            RequestBody requestBody=mBaseRequestBuilder.generateRequestBody();
            Request request=mBaseRequestBuilder.generateRequest(requestBody);
            if(request!=null){
                AALog.d(TAG,"request:"+request.toString());
            }
            mOkHttpClient.newBuilder().build().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        T data=(T)mCallback.parseNetworkResponse(response);
                        mCallback.onSuccess(data,call,response);
                    } catch (Exception e) {
                        AALog.d(TAG,""+e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            AALog.d(TAG,""+e.getMessage());
            e.printStackTrace();
        }

    }

}
