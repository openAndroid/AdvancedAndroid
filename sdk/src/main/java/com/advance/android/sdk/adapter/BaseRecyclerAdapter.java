package com.advance.android.sdk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * ——————————————————————————————————
 * 作者: shuaizhimin www.shuaizm.com
 * 描述: recyclerAdpter 抽象类
 * 日期: 2016-08-26
 * 时间: 11:55
 * ——————————————————————————————————
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    //自定义点击事件
    private OnItemClickListener mItemClickListener;
    protected List<T> list;
    protected LayoutInflater inflater;

    public BaseRecyclerAdapter(Context context) {
        this(context, null);
    }

    public BaseRecyclerAdapter(Context context, List<T> data) {
        list = data;
        inflater = LayoutInflater.from(context);
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
            if(data==null) return;
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if(list!=null) list.clear();
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return list.get(position);
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setItemClickListener(@NonNull OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }


}
