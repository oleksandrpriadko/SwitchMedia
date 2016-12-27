package com.example.priadko.switchmedia.home_screen.presenter;

import com.example.priadko.switchmedia.home_screen.IFragHomeView;
import com.example.priadko.switchmedia.home_screen.interactor.FragHomeInteractorImpl;
import com.example.priadko.switchmedia.home_screen.interactor.LoadDataListener;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class FragHomePresenterImpl implements IFragHomePresenter, LoadDataListener {

    private FragHomeInteractorImpl interactor;
    private IFragHomeView view;

    public FragHomePresenterImpl() {
        interactor = new FragHomeInteractorImpl();
    }

    @Override
    public void bind(IFragHomeView view) {
        this.view = view;
        if (interactor == null) {
            interactor = new FragHomeInteractorImpl();
        }
        loadData();
    }

    @Override
    public void unBind() {
        view = null;
        if (interactor != null) {
            interactor.shutDown();
            interactor = null;
        }
    }

    @Override
    public void loadData() {
        interactor.loadData(this);
    }

    @Override
    public void itemClicked(String title, String url) {
        view.showDetailScreen();
        view.setDetailScreenTitle(title);
        view.setDetailScreenImage(url);
    }

    @Override
    public void clickedOkDetailScreen() {
        view.hideDetailScreen();
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
}
