package com.example.priadko.switchmedia.display_items.adapter_main.interactor;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

interface IHolderMainInteractor {
    int VIEW_TYPE_CHANNELS = 1;
    int VIEW_TYPE_CONT_WATCHING = 2;
    int VIEW_TYPE_HIGHLIGHTS = 3;
    int VIEW_TYPE_KIDS = 4;
    int COUNT = VIEW_TYPE_KIDS;

    void getData(String[][] data, int position, IDataPreparedMain listener);

    void getSectionName(int position, IDataPreparedMain listener);
}
