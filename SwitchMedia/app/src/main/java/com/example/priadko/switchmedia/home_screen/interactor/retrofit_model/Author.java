package com.example.priadko.switchmedia.home_screen.interactor.retrofit_model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

@Root(name = "author")
class Author {
    @Element(name = "userName", required = false)
    private String userName;

    @Element(name = "url", required = false)
    private String url;
}
