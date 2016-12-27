package com.example.priadko.switchmedia.display_items.adapter_vertical;

import android.support.annotation.StringRes;
import android.util.Pair;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface IHolderVerticalView {
    void updateRecyclerView(Pair<String[][], Boolean> data);

    void setSectionName(@StringRes int sectionName);
}
