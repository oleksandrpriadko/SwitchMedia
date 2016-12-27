package com.example.priadko.switchmedia.display_items.adapter_vertical.interactor;

import android.support.annotation.StringRes;
import android.util.Pair;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface IDataPreparedVertical {
    void dataRecViewPrepared(Pair<String[][], Boolean> data);

    void sectionNameGot(@StringRes int sectionName);
}
