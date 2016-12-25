package com.example.priadko.switchmedia.home_screen.interactor;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface LoadDataListener {
    /**
     * Loading started
     */
    void loadingStarted();

    /**
     * Data loaded
     */
    void loaded(String[][] data);

    /**
     * Loading failed
     */
    void loadingDataFailed();
}
