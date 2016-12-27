package com.example.priadko.switchmedia.home_screen.presenter;

import com.example.priadko.switchmedia.home_screen.IFragHomeView;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface IFragHomePresenter {

    void loadData();

    void itemClicked(String title, String url);

    void clickedOkDetailScreen();

    void bindView(IFragHomeView view);

    void unBindView();
}
