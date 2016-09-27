package com.advance.android.sdk.http.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * 娇艳主机名是否正确
 * 作者: shuaizhimin
 * 描述:
 * 日期: 2016-09-23
 * 时间: 16:06
 * 版本:
 */
public class DefaultHostNameVerifier implements HostnameVerifier{

    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
