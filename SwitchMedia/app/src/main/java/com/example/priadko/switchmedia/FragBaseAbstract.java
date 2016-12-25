package com.example.priadko.switchmedia;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public abstract class FragBaseAbstract extends Fragment {

    /**
     * @return id of rootLayout
     */
    @LayoutRes
    protected abstract int getRootViewId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getRootViewId(), container, false);
    }

    /**
     * Initialize your views here
     *
     * @param rootView parent view of the fragment
     */
    protected abstract void prepareViews(View rootView);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareViews(view);
    }

    /**
     * @return Fragment tag
     */
    public String getFragmentTag() {
        return this.getClass().getSimpleName();
    }

    /**
     * Check if fragment is attached to activity and
     * activity is not finishing
     *
     * @return true - if attached, false - otherwise
     */
    protected boolean checkIfAdded() {
        return !(!this.isAdded() || this.getActivity().isFinishing());
    }
}
