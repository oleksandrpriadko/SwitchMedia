package com.example.priadko.switchmedia;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class MainActivity extends AppCompatActivity {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  INIT
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        setOrientation();
        initToolbar();
        initTabLayout();
    }

    private void setOrientation() {
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        setRequestedOrientation(isTablet
                ? ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initTabLayout() {
        TabLayout tabLayout = ((TabLayout) findViewById(R.id.tabLayout));
        DummyPageAdapter dummyPageAdapter = new DummyPageAdapter(getSupportFragmentManager(), this);
        ViewPager viewPager = ((ViewPager) findViewById(R.id.view_pager));
        viewPager.setAdapter(dummyPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
