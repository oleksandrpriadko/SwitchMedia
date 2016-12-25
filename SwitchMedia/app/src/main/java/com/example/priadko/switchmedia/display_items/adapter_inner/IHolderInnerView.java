package com.example.priadko.switchmedia.display_items.adapter_inner;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface IHolderInnerView {
    void loadPoster(String url);

    void setTitle(String title);

    void itemClick(String title, String url);
}
