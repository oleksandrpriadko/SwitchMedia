package com.example.priadko.switchmedia.display_items.adapter_main.interactor;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

interface IHolderMainInteractor {
    int VIEW_TYPE_CHANNELS = 0;
    int VIEW_TYPE_CONT_WATCHING = 1;
    int VIEW_TYPE_HIGHLIGHTS = 2;
    int VIEW_TYPE_KIDS = 3;
    int COUNT = 4;

    void getData(String[][] data, int position, IDataPreparedMain listener);

    void getSectionName(int position, IDataPreparedMain listener);
}
