package com.example.priadko.switchmedia.display_items.adapter_inner.interactor;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class HolderInnerInteractor implements IHolderInnerInteractor {
    @Override
    public void getPosterUrl(String[][] data, int position, IDataPreparedInner listener) {
        listener.getPosterUrl(data[position][URL]);
    }

    @Override
    public void getTitleText(String[][] data, int position, IDataPreparedInner listener) {
        listener.getTitle(data[position][TITLE]);
    }

    @Override
    public void getDataForIteClick(String[][] data, int position, IDataPreparedInner listener) {
        listener.getItemClickData(data[position][TITLE], data[position][URL]);
    }
}
