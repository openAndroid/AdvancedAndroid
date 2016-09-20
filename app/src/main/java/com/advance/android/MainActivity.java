package com.advance.android;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.advance.android.ui.home.HomeFragment;
import com.advance.android.ui.message.MessageFragment;
import com.advance.android.ui.setting.SettingFragment;
import com.advance.android.uicomp.tab.FragmentTabHost;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    private final static String[] TAB_TAG = {"home", "message", "setting"};

    @BindView(android.R.id.tabhost)
    FragmentTabHost mFragmentTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);
        initFragments();

    }

    private void initFragments() {
        LayoutInflater inflater = LayoutInflater.from(this);
        mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(TAB_TAG[0])
                .setIndicator(inflater.inflate(R.layout.tab_home, null)), HomeFragment.class, null);
        mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(TAB_TAG[1])
                .setIndicator(inflater.inflate(R.layout.tab_message, null)), MessageFragment.class, null);
        mFragmentTabHost.addTab(mFragmentTabHost.newTabSpec(TAB_TAG[2  ])
                .setIndicator(inflater.inflate(R.layout.tab_setting, null)), SettingFragment.class, null);
    }


}
