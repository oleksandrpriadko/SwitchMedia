package com.example.priadko.switchmedia.display_items.adapter_vertical.interactor;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

interface IHolderVerticalInteractor {
    int VIEW_TYPE_CHANNELS = 0;
    int VIEW_TYPE_CONT_WATCHING = 1;
    int VIEW_TYPE_HIGHLIGHTS = 2;
    int VIEW_TYPE_KIDS = 3;

    void getData(String[][] data, int position, IDataPreparedVertical listener);

    void getSectionName(int position, IDataPreparedVertical listener);
}
