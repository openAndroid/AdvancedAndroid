package com.advance.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.advance.android.model.LauncherInfo;
import com.advance.android.sdk.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {

    private static String TAG="MainActivity";
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Log.e(TAG,"sssssss");
        getName("shuai","zhimin");
    }
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecylerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(getInfos());
    }
    @DebugLog
    public String getName(String first, String last) {
        SystemClock.sleep(15); // Don't ever really do this!
        return first + " " + last;
    }

    public class RecyclerAdapter extends BaseRecyclerAdapter<LauncherInfo, RecyclerAdapter.ViewHolder> {


        public RecyclerAdapter(Context context) {
            super(context);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.launcher_items, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final LauncherInfo info = getItem(position);
            holder.mTextView.setText(info.getLabel());
            holder.mImageView.setImageDrawable(info.getDrawable());
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        startActivity(new Intent(info.getAction()));
                    }catch (Exception e){
                        Log.e(TAG,""+e.getMessage());
                        e.printStackTrace();
                    }

                }
            });
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ImageView mImageView;

            public ViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.mTextView);
                mImageView = (ImageView) itemView.findViewById(R.id.mImageView);
            }
        }
    }


    private List<LauncherInfo> getInfos() {
        List<LauncherInfo> infos = new ArrayList<>();
        String[] strAry = getResources().getStringArray(R.array.main_launch);
        int count = strAry.length;
        for (int i = 0; i < count; i++) {
            String s = strAry[i];
            String[] tempAry = s.split(",");
            LauncherInfo info = new LauncherInfo();
            info.setLabel(tempAry[0]);
            Drawable defaultDrawable = getResources().getDrawable(getResources().getIdentifier("icon", "drawable", getPackageName()));
            if (tempAry.length > 1 && !TextUtils.isEmpty(tempAry[1])) {
                int resId = getResources().getIdentifier(tempAry[1], "drawable", getPackageName());
                if (resId == 0) {
                    info.setDrawable(defaultDrawable);
                } else {
                    Drawable drawable = getResources().getDrawable(getResources().getIdentifier(tempAry[1], "drawable", getPackageName()));
                    info.setDrawable(drawable);
                }
            } else {
                info.setDrawable(defaultDrawable);
            }
            if (tempAry.length > 2) {
                info.setAction(tempAry[2]);
            }
            infos.add(info);
        }
        return infos;

    }
    public static void main(String[] args){
        testAnnotatedMethod();
        System.out.println("哈哈哈哈哈");
    }
    @DebugLog
    private static void testAnnotatedMethod() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
