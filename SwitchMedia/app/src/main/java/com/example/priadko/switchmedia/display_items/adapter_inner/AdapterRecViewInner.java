package com.example.priadko.switchmedia.display_items.adapter_inner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priadko.switchmedia.R;
import com.example.priadko.switchmedia.display_items.adapter_inner.presenter.HolderInnerPresenter;
import com.squareup.picasso.Picasso;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class AdapterRecViewInner extends RecyclerView.Adapter<AdapterRecViewInner.HolderInner> {

    private String[][] data;
    private LayoutInflater inflater;
    private ItemListener itemListener;
    private boolean forChannel = false;

    public AdapterRecViewInner(@NonNull ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    @Override
    public HolderInner onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        View itemView = inflater.inflate(
                forChannel ? R.layout.inner_item_channels : R.layout.inner_item,
                parent,
                false);
        return new HolderInner(itemView);
    }

    @Override
    public void onBindViewHolder(final HolderInner holder, int position) {
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.length : 0;
    }

    public void setData(String[][] data, boolean forChannel) {
        this.data = data;
        this.forChannel = forChannel;
        notifyDataSetChanged();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //  VIEW HOLDER
    //////////////////////////////////////////////////////////////////////////////////////////////
    class HolderInner extends RecyclerView.ViewHolder implements IHolderInnerView {
        private TextView titleText;
        private ImageView poster;
        private ImageView playImage;
        private HolderInnerPresenter presenterHolder;

        HolderInner(View itemView) {
            super(itemView);
            titleText = ((TextView) itemView.findViewById(R.id.textView_title));
            poster = ((ImageView) itemView.findViewById(R.id.imageView_poster));
            playImage = ((ImageView) itemView.findViewById(R.id.imageView_play));
            playImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenterHolder.itemClicked(data, getAdapterPosition());
                }
            });
            presenterHolder = new HolderInnerPresenter();
        }

        private void bind(String[][] data) {
            presenterHolder.bind(data, getAdapterPosition(), this);
        }

        @Override
        public void loadPoster(String url) {
            getPicasso()
                    .load(url)
                    .fit()
                    .error(R.drawable.ic_error_outline_white_24dp)
                    .into(poster);
        }

        @Override
        public void setTitle(String title) {
            titleText.setText(title);
        }

        @Override
        public void itemClick(String title, String url) {
            if (itemListener != null) itemListener.itemClicked(title, url);
        }

        private Picasso getPicasso() {
            Picasso picasso = Picasso.with(poster.getContext());
            picasso.setIndicatorsEnabled(true);
            picasso.setLoggingEnabled(true);
            return picasso;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //  ITEM LISTENER
    //////////////////////////////////////////////////////////////////////////////////////////////
    public interface ItemListener {
        void itemClicked(String title, String url);
    }
}
