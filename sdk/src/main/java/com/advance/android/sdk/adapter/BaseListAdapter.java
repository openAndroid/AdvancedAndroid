package com.advance.android.sdk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * ——————————————————————————————————
 * 作者: shuaizhimin www.shuaizm.com
 * 描述: adapter 抽象类
 * 日期: 2016-08-26
 * 时间: 11:53
 * ——————————————————————————————————
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    protected List<T> list;
    protected LayoutInflater inflater;
    protected Context context;

    public BaseListAdapter(Context context) {
        this(context, null);
    }

    public BaseListAdapter(Context context, List<T> data) {
        list = data;
        this.context=context;
        if(context!=null){
            inflater = LayoutInflater.from(context);
        }
    }

    public List<T> getData() {
        return list;
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(List<T> data) {
        list = data;
        notifyDataSetChanged();
    }

    public void appendData(List<T> data) {
        if (list == null) {
            list = data;
        } else {
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 清除数据
     */
    public void clear() {
        if (list != null) {
            list.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected String getText(int textRes) {
        return inflater.getContext().getString(textRes);
    }

    protected String getText(int res, Object... args) {
        return inflater.getContext().getString(res, args);
    }

    protected int getColor(int colorRes) {
        return inflater.getContext().getResources().getColor(colorRes);
    }
}