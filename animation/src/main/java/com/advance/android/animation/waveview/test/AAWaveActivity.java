package com.advance.android.animation.waveview.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.advance.android.animation.R;
import com.advance.android.animation.waveview.AAWaveView;

public class AAWaveActivity extends AppCompatActivity {
    private AAWaveView mHDWaveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aawave);
        initView();
    }
    private void initView(){
        mHDWaveView=(AAWaveView)findViewById(R.id.mWaveView);
        mHDWaveView.start();
    }
}
