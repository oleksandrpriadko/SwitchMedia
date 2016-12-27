package com.example.priadko.switchmedia;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.priadko.switchmedia.anytime_screen.FragmentAnytime;
import com.example.priadko.switchmedia.home_screen.FragHome;
import com.example.priadko.switchmedia.kids_screen.FragmentKids;
import com.example.priadko.switchmedia.live_tv_screen.FragmentLiveTV;
import com.example.priadko.switchmedia.player_screen.FragmentPlayer;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

class DummyPageAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 5;
    private final int PAGE_HOME = 0;
    private final int PAGE_LIVE_TV = 1;
    private final int PAGE_ANYTIME = 2;
    private final int PAGE_KIDS = 3;
    private final int PAGE_PLAYER = 4;
    private String[] tabNames;

    DummyPageAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        populateTabsNames(context);
    }

    private void populateTabsNames(Context context) {
        tabNames = context.getResources().getStringArray(R.array.tab_names);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case PAGE_HOME:
                return new FragHome();
            case PAGE_LIVE_TV:
                return new FragmentLiveTV();
            case PAGE_ANYTIME:
                return new FragmentAnytime();
            case PAGE_KIDS:
                return new FragmentKids();
            case PAGE_PLAYER:
                return new FragmentPlayer();
            default:
                return new FragHome();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position < PAGE_COUNT ? tabNames[position] : tabNames[PAGE_HOME];
    }
}
