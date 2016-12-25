package com.example.priadko.switchmedia.display_items.adapter_main.interactor;

import android.support.annotation.StringRes;
import android.util.Pair;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface IDataPreparedMain {
    void dataRecViewPrepared(Pair<String[][], Boolean> data);

    void sectionNameGot(@StringRes int sectionName);
}
