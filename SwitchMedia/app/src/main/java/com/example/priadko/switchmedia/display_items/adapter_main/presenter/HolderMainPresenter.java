package com.example.priadko.switchmedia.display_items.adapter_main.presenter;

import android.support.annotation.StringRes;
import android.util.Pair;

import com.example.priadko.switchmedia.display_items.adapter_main.IHolderMainView;
import com.example.priadko.switchmedia.display_items.adapter_main.interactor.IDataPreparedMain;
import com.example.priadko.switchmedia.display_items.adapter_main.interactor.HolderMainInteractor;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class HolderMainPresenter implements IHolderMainPresenter, IDataPreparedMain {
    private IHolderMainView view;
    private HolderMainInteractor interactor;

    public HolderMainPresenter() {
        interactor = new HolderMainInteractor();
    }

    @Override
    public void bind(IHolderMainView view, String[][] data, int position) {
        this.view = view;
        interactor.getData(data, position, this);
        interactor.getSectionName(position, this);
    }

    @Override
    public void dataRecViewPrepared(Pair<String[][], Boolean> data) { view.updateRecyclerView(data);
    }

    @Override
    public void sectionNameGot(@StringRes int sectionName) {
        view.setSectionName(sectionName);
    }
}
