package com.example.priadko.switchmedia;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.priadko.switchmedia.home_screen.FragHome;

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
        setContentView(R.layout.activity_main);
        setOrientation();
        initToolbar();
        initTabLayout();
        replaceFragmentNoStack(new FragHome());
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
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_home_name), true);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_livetv_name), false);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_anytime_name), false);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_kids_name), false);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_player_name), false);
        tabLayout.addOnTabSelectedListener(selectedListener);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  ACTIONS
    ///////////////////////////////////////////////////////////////////////////////////////////////
    protected void replaceFragmentNoStack(FragBaseAbstract fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_container, fragment)
                .commitAllowingStateLoss(); // we don't need the state
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //  LISTENERS
    ///////////////////////////////////////////////////////////////////////////////////////////////

    private TabLayout.OnTabSelectedListener selectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
