package com.example.priadko.switchmedia.display_items.adapter_inner.presenter;

import com.example.priadko.switchmedia.display_items.adapter_inner.IHolderInnerView;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

interface IHolderInnerPresenter {
    void itemClicked(String[][] data, int position);

    void bind(String[][] data, int position, IHolderInnerView view);
}
