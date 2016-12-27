package com.example.priadko.switchmedia;

/**
 * SwitchMedia
 * Oleksandr Priadko
 *
 * if you want to play with count of requests(items) and
 * count of sections - welcome! You can do it here.
 */
public class Constants {
    //count of items. count of items == count of requests to colorlovers.com
    public final static int ITEM_COUNT = 40;
    //count of section in vertical RecyclerView
    public final static int SECTION_COUNT = 4;
    // size of inner array in 2d array(for title and imageUrl)
    public static final int INNER_ITEM_COUNT = 2;
    //index of title in 2d array
    public final static int INDEX_TITLE = 0;
    //index of imageUrl in 2d array
    public final static int INDEX_URL = 1;
    // key for cached 2d array
    public static final String CACHE_2D_DATA_KEY = "CACHE_2D_DATA_KEY";
}