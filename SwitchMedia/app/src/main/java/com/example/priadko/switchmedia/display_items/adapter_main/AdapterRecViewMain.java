package com.example.priadko.switchmedia.display_items.adapter_main;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.priadko.switchmedia.R;
import com.example.priadko.switchmedia.display_items.adapter_inner.AdapterRecViewInner;
import com.example.priadko.switchmedia.display_items.adapter_main.presenter.HolderMainPresenter;
import com.example.priadko.switchmedia.utils.recycler_view.ItemDecorLinHorizontal;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class AdapterRecViewMain extends RecyclerView.Adapter<AdapterRecViewMain.HolderMain> {
    private final static int SECTIONS_COUNT = 4;

    private String[][] arrAll;
    private LayoutInflater inflater;
    private AdapterRecViewInner.ItemListener itemListener;

    public AdapterRecViewMain(@NonNull String[][] arrAll,
                              @NonNull AdapterRecViewInner.ItemListener itemListener) {
        this.arrAll = arrAll;
        this.itemListener = itemListener;
    }

    @Override
    public HolderMain onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new HolderMain(inflater.inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(HolderMain holder, int position) {
        holder.bind(arrAll);
    }

    @Override
    public int getItemCount() {
        return arrAll != null && arrAll.length > SECTIONS_COUNT - 1 ? SECTIONS_COUNT : 0;
    }

    public void setData(@NonNull String[][] data,
                        @NonNull AdapterRecViewInner.ItemListener itemListener) {
        arrAll = data;
        this.itemListener = itemListener;
        notifyDataSetChanged();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //  VIEW HOLDER
    //////////////////////////////////////////////////////////////////////////////////////////////
    class HolderMain extends RecyclerView.ViewHolder implements IHolderMainView {
        private RecyclerView recyclerView;
        private AdapterRecViewInner adapterRecViewInner;
        private TextView section;
        private HolderMainPresenter presenterHolder;

        HolderMain(View itemView) {
            super(itemView);
            initRecView();
            section = ((TextView) itemView.findViewById(R.id.textView_section));
            presenterHolder = new HolderMainPresenter();
        }

        private void bind(String[][] data) {
            presenterHolder.bind(this, data, getAdapterPosition());
        }

        private void initRecView() {
            recyclerView = ((RecyclerView) itemView.findViewById(R.id.recView_inner));
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            int margin = itemView.getResources().getDimensionPixelOffset(R.dimen.margin_item_inner);
            ItemDecorLinHorizontal decorator
                    = new ItemDecorLinHorizontal(margin, 0, margin, 0, false, true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(decorator);
        }

        @Override
        public void updateRecyclerView(Pair<String[][], Boolean> data) {
            if (adapterRecViewInner == null) {
                adapterRecViewInner = new AdapterRecViewInner(itemListener);
            }
            recyclerView.setAdapter(adapterRecViewInner);
            adapterRecViewInner.setData(data.first, data.second);
        }

        @Override
        public void setSectionName(@StringRes int sectionName) {
            section.setText(sectionName);
        }
    }
}
