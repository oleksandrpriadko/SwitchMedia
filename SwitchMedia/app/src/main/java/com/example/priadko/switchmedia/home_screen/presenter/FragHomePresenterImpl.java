package com.example.priadko.switchmedia.home_screen.presenter;

import android.util.Log;

import com.example.priadko.switchmedia.home_screen.IFragHomeView;
import com.example.priadko.switchmedia.home_screen.interactor.FragHomeInteractorImpl;
import com.example.priadko.switchmedia.home_screen.interactor.IFragHomeInteractor;
import com.example.priadko.switchmedia.home_screen.interactor.LoadDataListener;

import java.lang.ref.WeakReference;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class FragHomePresenterImpl implements IFragHomePresenter, LoadDataListener {

    private IFragHomeInteractor interactor;
    private WeakReference<IFragHomeView> view;

    public FragHomePresenterImpl() {
        interactor = new FragHomeInteractorImpl();
    }

    @Override
    public void bindView(IFragHomeView view) {
        this.view = new WeakReference<>(view);
        if (interactor == null) {
            interactor = new FragHomeInteractorImpl();
        }
    }

    @Override
    public void unBindView() {
        view = null;
    }

    @Override
    public boolean isViewBind() {
        return view != null && view.get() != null;
    }

    @Override
    public void loadData() {
        interactor.loadData(this);
    }

    @Override
    public void itemClicked(String title, String url) {
        if (isViewBind()) {
            view.get().showDetailScreen();
            view.get().setDetailScreenTitle(title);
            view.get().setDetailScreenImage(url);
        }
    }

    @Override
    public void clickedOkDetailScreen() {
        if (isViewBind()) {
            view.get().hideDetailScreen();
        }
    }

    /**
     * Loading started
     */
    @Override
    public void loadingStarted() {
        if (isViewBind()) {
            view.get().loadingStarted();
        }
    }

    /**
     * Data loaded
     */
    @Override
    public void loaded(String[][] data) {
        if (isViewBind()) view.get().dataLoaded(data);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.i("Presenter", "finalized");
    }
}
