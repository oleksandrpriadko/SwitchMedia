package com.example.priadko.switchmedia.display_items.adapter_inner.presenter;

import com.example.priadko.switchmedia.display_items.adapter_inner.IHolderInnerView;
import com.example.priadko.switchmedia.display_items.adapter_inner.interactor.HolderInnerInteractor;
import com.example.priadko.switchmedia.display_items.adapter_inner.interactor.IDataPreparedInner;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class HolderInnerPresenter implements IHolderInnerPresenter, IDataPreparedInner {
    private IHolderInnerView view;
    private HolderInnerInteractor interactor;

    public HolderInnerPresenter() {
        interactor = new HolderInnerInteractor();
    }

    public void itemClicked(String[][] data, int position) {
        interactor.getDataForIteClick(data, position, this);
    }

    public void bind(String[][] data, int position, IHolderInnerView view) {
        this.view = view;
        interactor.getPosterUrl(data, position, this);
        interactor.getTitleText(data, position, this);
    }

    @Override
    public void getItemClickData(String title, String url) {
        view.itemClick(title, url);
    }

    @Override
    public void getPosterUrl(String url) {
        view.loadPoster(url);
    }

    @Override
    public void getTitle(String text) {
        view.setTitle(text);
    }
}
