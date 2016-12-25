package com.example.priadko.switchmedia.home_screen.presenter;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

interface IFragHomePresenter {
    /**
     * Try load data
     */
    void loadData();

    /**
     * ItemClicked
     */
    void itemClicked(String title, String url);

    void clickedOkDetailScreen();
}
