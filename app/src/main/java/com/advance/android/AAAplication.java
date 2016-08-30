package com.advance.android;

import android.app.Application;

import com.antfortune.freeline.FreelineCore;

/**
 * ——————————————————————————————————
 * 作者: shuaizhimin www.shuaizm.com
 * 描述:
 * 日期: 2016-08-30
 * 时间: 15:42
 * ——————————————————————————————————
 */
public class AAAplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FreelineCore.init(this);
    }
}
