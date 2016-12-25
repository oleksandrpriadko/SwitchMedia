package com.example.priadko.switchmedia.home_screen.presenter;

import com.example.priadko.switchmedia.home_screen.IFragHomeView;
import com.example.priadko.switchmedia.home_screen.interactor.FragHomeInteractor;
import com.example.priadko.switchmedia.home_screen.interactor.LoadDataListener;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class FragHomePresenter implements IFragHomePresenter, LoadDataListener {

    private FragHomeInteractor interactor;
    private IFragHomeView view;

    public FragHomePresenter() {
        interactor = new FragHomeInteractor();
    }

    public void bind(IFragHomeView view) {
        this.view = view;
        if (interactor == null) {
            interactor = new FragHomeInteractor();
        }
        loadData();
    }

    public void unBind() {
        view = null;
        if (interactor != null) {
            interactor.shutDown();
            interactor = null;
        }
    }

    /**
     * Try load data
     */
    @Override
    public void loadData() {
        interactor.loadData(this);
    }

    /**
     * ItemClicked
     */
    @Override
    public void itemClicked(String title, String url) {
        view.showHideDetailScreen(true);
        view.setDetailScreenTitle(title);
        view.setDetailScreenImage(url);
    }

    @Override
    public void clickedOkDetailScreen() {
        view.showHideDetailScreen(false);
    }

    /**
     * Loading started
     */
    @Override
    public void loadingStarted() {
        view.loadingStarted();
    }

    /**
     * Data loaded
     */
    @Override
    public void loaded(String[][] data) {
        view.dataLoaded(data);
    }

    /**
     * Loading failed
     */
    @Override
    public void loadingDataFailed() {
        view.loadingFailed();
    }
}
