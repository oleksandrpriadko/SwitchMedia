package com.example.priadko.switchmedia.display_items.adapter_inner.interactor;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

interface IHolderInnerInteractor {
    int TITLE = 0;
    int URL = 1;

    void getPosterUrl(String[][] data, int position, IDataPreparedInner listener);

    void getTitleText(String[][] data, int position, IDataPreparedInner listener);

    void getDataForIteClick(String[][] data, int position, IDataPreparedInner listener);
}
