package com.example.priadko.switchmedia.home_screen.interactor;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface IFragHomeInteractor {

    /**
     * Load data
     */
    void loadData(LoadDataListener listener);

    void shutDown();
}
