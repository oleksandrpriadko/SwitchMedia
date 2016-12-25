package com.example.priadko.switchmedia.display_items.adapter_inner.interactor;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface IDataPreparedInner {
    void getItemClickData(String title, String url);

    void getPosterUrl(String url);

    void getTitle(String text);
}
