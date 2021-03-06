package com.example.priadko.switchmedia.display_items.adapter_horizontal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priadko.switchmedia.R;
import com.squareup.picasso.Picasso;

import java.util.Random;

import static com.example.priadko.switchmedia.Constants.INDEX_TITLE;
import static com.example.priadko.switchmedia.Constants.INDEX_URL;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class AdapterRecViewHorizontal extends RecyclerView.Adapter<AdapterRecViewHorizontal.HolderHorizontal> {

    private String[][] data;
    private LayoutInflater inflater;
    private ItemListener itemListener;
    private boolean forChannel = false;
    private int count;

    public AdapterRecViewHorizontal(@NonNull ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    @Override
    public HolderHorizontal onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        View itemView = inflater.inflate(
                forChannel ? R.layout.inner_item_channels : R.layout.inner_item,
                parent,
                false);
        return new HolderHorizontal(itemView);
    }

    @Override
    public void onBindViewHolder(final HolderHorizontal holder, int position) {
        holder.setTitle(data[position][INDEX_TITLE]);
        holder.loadPoster(data[position][INDEX_URL]);
    }

    @Override
    public int getItemCount() {
        // as was asked by Trevor - simple randomness added. I know that count variable <= data.length
        // If you would like to see intelligent randomness - ping me and
        // I will do it. If you want to delete randomness - remove variable "count" and Random in
        // AdapterRecViewHorizontal.setData. And change code below to data != null ? data.length : 0
        return data != null ? count : 0;
    }

    public void setData(String[][] data, boolean forChannel) {
        this.data = data;
        this.forChannel = forChannel;
        // as was asked by Trevor - simple randomness added. I know that code below does not use all
        // items from passed 2d array. If you would like to see intelligent randomness - ping me and
        // I will do it. If you want to delete randomness - remove variable "count" and Random,
        // also update AdapterRecViewHorizontal.getItemCount
        Random r = new Random();
        count = r.nextInt(data.length - 1) + 1;
        notifyDataSetChanged();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //  VIEW HOLDER
    //////////////////////////////////////////////////////////////////////////////////////////////
    class HolderHorizontal extends RecyclerView.ViewHolder {
        private TextView titleText;
        private ImageView poster;
        private ImageView playImage;

        HolderHorizontal(View itemView) {
            super(itemView);
            titleText = ((TextView) itemView.findViewById(R.id.textView_title));
            poster = ((ImageView) itemView.findViewById(R.id.imageView_poster));
            playImage = ((ImageView) itemView.findViewById(R.id.imageView_play));
            playImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = data[getAdapterPosition()][INDEX_TITLE];
                    String url = data[getAdapterPosition()][INDEX_URL];
                    itemListener.itemClicked(title, url);
                }
            });
        }

        void loadPoster(String url) {
            getPicasso()
                    .load(url)
                    .fit()
                    .error(R.drawable.ic_error_outline_white_24dp)
                    .into(poster);
        }

        void setTitle(String title) {
            titleText.setText(title);
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
