package com.example.priadko.switchmedia.display_items.adapter_main.presenter;

import com.example.priadko.switchmedia.display_items.adapter_main.IHolderMainView;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

interface IHolderMainPresenter {
    void bind(IHolderMainView view, String[][] data, int position);
}
