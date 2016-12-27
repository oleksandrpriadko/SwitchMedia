package com.example.priadko.switchmedia.display_items.adapter_vertical.interactor;

import android.util.Pair;

import com.example.priadko.switchmedia.R;

import java.util.Arrays;

import static com.example.priadko.switchmedia.Constants.SECTION_COUNT;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class HolderVerticalInteractorImpl implements IHolderVerticalInteractor {

    @Override
    public void getData(String[][] data, int position, IDataPreparedVertical listener) {
        String[][] itemsForSection;
        int firstIndex, lastIndex;
        switch (position) {
            case VIEW_TYPE_CHANNELS:
                firstIndex = 0;
                lastIndex = data.length / SECTION_COUNT;
                itemsForSection = Arrays.copyOfRange(data, firstIndex, lastIndex);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, true));
                break;
            case VIEW_TYPE_CONT_WATCHING:
                firstIndex = data.length / SECTION_COUNT;
                lastIndex = (data.length / SECTION_COUNT) * VIEW_TYPE_HIGHLIGHTS;
                itemsForSection = Arrays.copyOfRange(data, firstIndex, lastIndex);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, false));
                break;
            case VIEW_TYPE_HIGHLIGHTS:
                firstIndex = (data.length / SECTION_COUNT) * VIEW_TYPE_HIGHLIGHTS;
                lastIndex = data.length / SECTION_COUNT * VIEW_TYPE_KIDS;
                itemsForSection = Arrays.copyOfRange(data, firstIndex, lastIndex);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, false));
                break;
            case VIEW_TYPE_KIDS:
                firstIndex = (data.length / SECTION_COUNT) * VIEW_TYPE_KIDS;
                // (VIEW_TYPE_KIDS + 1) instead of data.length because you can set count of
                // section > value of VIEW_TYPE_KIDS.
                // if you want to play with count of requests(items) and
                // count of sections - welcome to Constants class
                lastIndex = (data.length / SECTION_COUNT) * (VIEW_TYPE_KIDS + 1);
                itemsForSection = Arrays.copyOfRange(data, firstIndex, lastIndex);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, false));
                break;
            default:
                //count of sections > count of items ? getItemCount() in adapter returns 0 : ok
                itemsForSection = Arrays.copyOfRange(data,
                        (data.length / SECTION_COUNT) * (position),
                        //do not change - it is for use-case when SECTION_COUNT > (VIEW_TYPE_KIDS + 1)
                        position == (SECTION_COUNT - 1)
                                ? data.length
                                : (data.length / SECTION_COUNT) * (position + 1));
                listener.dataRecViewPrepared(Pair.create(itemsForSection, false));
                break;
        }
    }

    @Override
    public void getSectionName(int position, IDataPreparedVertical listener) {
        switch (position) {
            case VIEW_TYPE_CHANNELS:
                listener.sectionNameGot(R.string.section_channels_name);
                break;
            case VIEW_TYPE_CONT_WATCHING:
                listener.sectionNameGot(R.string.section_cont_watching_name);
                break;
            case VIEW_TYPE_HIGHLIGHTS:
                listener.sectionNameGot(R.string.section_highlights_name);
                break;
            case VIEW_TYPE_KIDS:
                listener.sectionNameGot(R.string.section_kids_name);
                break;
            default:
                listener.sectionNameGot(R.string.section_unknown);
                break;
        }
    }
}
