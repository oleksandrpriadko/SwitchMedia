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
        switch (position) {
            case VIEW_TYPE_CHANNELS:
                listener.sectionNameGot(R.string.section_channels_name);
                itemsForSection = Arrays.copyOfRange(data, 0, data.length / SECTION_COUNT);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, true));
                break;
            case VIEW_TYPE_CONT_WATCHING:
                listener.sectionNameGot(R.string.section_cont_watching_name);
                itemsForSection = Arrays.copyOfRange(data,
                        data.length / SECTION_COUNT,
                        (data.length / SECTION_COUNT) * VIEW_TYPE_HIGHLIGHTS);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, false));
                break;
            case VIEW_TYPE_HIGHLIGHTS:
                listener.sectionNameGot(R.string.section_highlights_name);
                itemsForSection = Arrays.copyOfRange(data,
                        (data.length / SECTION_COUNT) * VIEW_TYPE_HIGHLIGHTS,
                        data.length / SECTION_COUNT * VIEW_TYPE_KIDS);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, false));
                break;
            case VIEW_TYPE_KIDS:
                listener.sectionNameGot(R.string.section_kids_name);
                itemsForSection = Arrays.copyOfRange(data,
                        (data.length / SECTION_COUNT) * VIEW_TYPE_KIDS,
                        data.length);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, false));
                break;
            default:
                listener.sectionNameGot(R.string.section_unknown);
                itemsForSection = new String[0][0];
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
        }
    }
}
