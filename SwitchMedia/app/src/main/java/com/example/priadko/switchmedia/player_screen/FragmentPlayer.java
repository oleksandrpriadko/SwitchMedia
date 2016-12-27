package com.example.priadko.switchmedia.player_screen;

import android.view.View;
import android.widget.TextView;

import com.example.priadko.switchmedia.FragBaseAbstract;
import com.example.priadko.switchmedia.R;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class FragmentPlayer extends FragBaseAbstract {
    /**
     * @return id of rootLayout
     */
    @Override
    protected int getRootViewId() {
        return R.layout.fragment_dummy;
    }

    /**
     * Initialize your views here
     *
     * @param rootView parent view of the fragment
     */
    @Override
    protected void prepareViews(View rootView) {
        ((TextView) rootView.findViewById(R.id.textView_title)).setText(R.string.tab_player);
    }
}
