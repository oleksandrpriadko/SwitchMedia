package com.example.priadko.switchmedia.home_screen.interactor.retrofit_model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

@Root(name = "patterns")
public class PatternModel {
    @Attribute(name = "totalResults", required = false)
    public int totalResults;

    @Attribute(name = "numResults", required = false)
    public int numResults;

    @Element(name = "pattern")
    private PatternItemModel pattern;

    public PatternItemModel getPattern() {
        return pattern;
    }
}
