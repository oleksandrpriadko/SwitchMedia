package com.example.priadko.switchmedia.display_items.adapter_vertical;

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
import com.example.priadko.switchmedia.display_items.adapter_horizontal.AdapterRecViewHorizontal;
import com.example.priadko.switchmedia.display_items.adapter_vertical.presenter.HolderVerticalPresenterImpl;
import com.example.priadko.switchmedia.display_items.adapter_vertical.presenter.IHolderVerticalPresenter;
import com.example.priadko.switchmedia.utils.recycler_view.ItemDecorLinHorizontal;

import static com.example.priadko.switchmedia.Constants.SECTION_COUNT;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class AdapterRecViewVertical extends RecyclerView.Adapter<AdapterRecViewVertical.HolderVertical> {

    private String[][] arrAll;
    private LayoutInflater inflater;
    private AdapterRecViewHorizontal.ItemListener itemListener;

    public AdapterRecViewVertical(@NonNull String[][] arrAll,
                                  @NonNull AdapterRecViewHorizontal.ItemListener itemListener) {
        this.arrAll = arrAll;
        this.itemListener = itemListener;
    }

    @Override
    public HolderVertical onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new HolderVertical(inflater.inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(HolderVertical holder, int position) {
        holder.bind(arrAll);
    }

    @Override
    public int getItemCount() {
        return arrAll != null && arrAll.length > SECTION_COUNT - 1 ? SECTION_COUNT : 0;
    }

    public void setData(@NonNull String[][] data,
                        @NonNull AdapterRecViewHorizontal.ItemListener itemListener) {
        arrAll = data;
        this.itemListener = itemListener;
        notifyDataSetChanged();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //  VIEW HOLDER
    //////////////////////////////////////////////////////////////////////////////////////////////
    class HolderVertical extends RecyclerView.ViewHolder implements IHolderVerticalView {
        private RecyclerView recyclerView;
        private AdapterRecViewHorizontal adapterRecViewHorizontal;
        private TextView section;
        private IHolderVerticalPresenter presenterHolder;

        HolderVertical(View itemView) {
            super(itemView);
            initRecView();
            section = ((TextView) itemView.findViewById(R.id.textView_section));
            presenterHolder = new HolderVerticalPresenterImpl();
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
            if (adapterRecViewHorizontal == null) {
                adapterRecViewHorizontal = new AdapterRecViewHorizontal(itemListener);
            }
            recyclerView.setAdapter(adapterRecViewHorizontal);
            adapterRecViewHorizontal.setData(data.first, data.second);
        }

        @Override
        public void setSectionName(@StringRes int sectionName) {
            section.setText(sectionName);
        }
    }
}
