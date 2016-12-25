package com.example.priadko.switchmedia.utils.recycler_view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class ItemDecorLinHorizontal extends RecyclerView.ItemDecoration {

    private int left;
    private int top;
    private int right;
    private int bottom;
    private boolean firstItemSpace;
    private boolean lastItemSpace;

    public ItemDecorLinHorizontal(int left,
                                  int top,
                                  int right,
                                  int bottom,
                                  boolean firstItemSpace,
                                  boolean lastItemSpacing) {

        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.firstItemSpace = firstItemSpace;
        this.lastItemSpace = lastItemSpacing;
    }

    @Override
    public void getItemOffsets(Rect outRect,
                               View view,
                               RecyclerView parent,
                               RecyclerView.State state) {
        outRect.top = top;
        outRect.bottom = bottom;

        if (firstItemSpace) {
            outRect.left = left;
        } else if (parent.getChildAdapterPosition(view) != 0) {
            outRect.left = left;
        }

        if (lastItemSpace && parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.right = right;
        }
    }
}
