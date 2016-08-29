package com.advance.android.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * ——————————————————————————————————
 * 作者: shuaizhimin www.shuaizm.com
 * 描述:
 * 日期: 2016-08-26
 * 时间: 12:01
 * ——————————————————————————————————
 */
public class LauncherInfo implements Serializable{
    private String label;
    private String action;
    private Drawable drawable;

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
