package com.example.priadko.switchmedia.display_items.adapter_main.interactor;

import android.util.Pair;

import com.example.priadko.switchmedia.R;

import java.util.Arrays;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public class HolderMainInteractor implements IHolderMainInteractor {

    @Override
    public void getData(String[][] data, int position, IDataPreparedMain listener) {
        String[][] itemsForSection;
        switch (position + 1) {
            case VIEW_TYPE_CHANNELS:
                listener.sectionNameGot(R.string.section_channels_name);
                itemsForSection = Arrays.copyOfRange(data, 0, data.length / COUNT);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, true));
                break;
            case VIEW_TYPE_CONT_WATCHING:
                listener.sectionNameGot(R.string.section_cont_watching_name);
                itemsForSection = Arrays.copyOfRange(data,
                        data.length / COUNT,
                        (data.length / COUNT) * VIEW_TYPE_CONT_WATCHING);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, false));
                break;
            case VIEW_TYPE_HIGHLIGHTS:
                listener.sectionNameGot(R.string.section_highlights_name);
                itemsForSection = Arrays.copyOfRange(data,
                        (data.length / COUNT) * VIEW_TYPE_CONT_WATCHING,
                        data.length / COUNT * VIEW_TYPE_HIGHLIGHTS);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, false));
                break;
            case VIEW_TYPE_KIDS:
                listener.sectionNameGot(R.string.section_kids_name);
                itemsForSection = Arrays.copyOfRange(data,
                        (data.length / COUNT) * VIEW_TYPE_HIGHLIGHTS,
                        data.length);
                listener.dataRecViewPrepared(Pair.create(itemsForSection, false));
                break;
        }
    }

    @Override
    public void getSectionName(int position, IDataPreparedMain listener) {
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
