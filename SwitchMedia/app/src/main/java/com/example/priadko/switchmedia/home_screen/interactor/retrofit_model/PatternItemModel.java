package com.example.priadko.switchmedia.home_screen.interactor.retrofit_model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

@Root(name = "pattern")
public class PatternItemModel {
    @Element(name = "imageUrl", required = false)
    private String imageUrl;

    @Element(name = "description", required = false)
    public String description;

    @Element(required = false)
    private Template template;

    @Element(name = "numVotes", required = false)
    private String numVotes;

    @Element(name = "badgeUrl", required = false)
    private String badgeUrl;

    @Element(required = false)
    private Colors colors;

    @Element(name = "url", required = false)
    private String url;

    @Element(name = "id", required = false)
    private String id;

    @Element(name = "numComments", required = false)
    private String numComments;

    @Element(name = "title", required = false)
    private String title;

    @Element(name = "rank", required = false)
    private String rank;

    @Element(name = "numViews", required = false)
    private String numViews;

    @Element(name = "apiUrl", required = false)
    private String apiUrl;

    @Element(name = "dateCreated", required = false)
    private String dateCreated;

    @Element(name = "userName", required = false)
    private String userName;

    @Element(name = "numHearts", required = false)
    private String numHearts;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }
}
