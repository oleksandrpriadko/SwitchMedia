package com.example.priadko.switchmedia.display_items.adapter_vertical.presenter;

import android.support.annotation.StringRes;
import android.util.Pair;

import com.example.priadko.switchmedia.display_items.adapter_vertical.IHolderVerticalView;
import com.example.priadko.switchmedia.display_items.adapter_vertical.interactor.HolderVerticalInteractorImpl;
import com.example.priadko.switchmedia.display_items.adapter_vertical.interactor.IDataPreparedVertical;
import com.example.priadko.switchmedia.display_items.adapter_vertical.interactor.IHolderVerticalInteractor;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class HolderVerticalPresenterImpl implements IHolderVerticalPresenter, IDataPreparedVertical {
    private IHolderVerticalView view;
    private IHolderVerticalInteractor interactor;

    public HolderVerticalPresenterImpl() {
        interactor = new HolderVerticalInteractorImpl();
    }

    @Override
    public void bind(IHolderVerticalView view, String[][] data, int position) {
        this.view = view;
        interactor.getData(data, position, this);
        interactor.getSectionName(position, this);
    }

    @Override
    public void dataRecViewPrepared(Pair<String[][], Boolean> data) {
        view.updateRecyclerView(data);
    }

    @Override
    public void sectionNameGot(@StringRes int sectionName) {
        view.setSectionName(sectionName);
    }
}
