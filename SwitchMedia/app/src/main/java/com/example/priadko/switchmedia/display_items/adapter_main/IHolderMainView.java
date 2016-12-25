package com.example.priadko.switchmedia.display_items.adapter_main;

import android.support.annotation.StringRes;
import android.util.Pair;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface IHolderMainView {
    void updateRecyclerView(Pair<String[][], Boolean> data);

    void setSectionName(@StringRes int sectionName);
}
