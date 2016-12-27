package com.example.priadko.switchmedia.display_items.adapter_vertical.presenter;

import com.example.priadko.switchmedia.display_items.adapter_vertical.IHolderVerticalView;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface IHolderVerticalPresenter {
    void bind(IHolderVerticalView view, String[][] data, int position);
}
