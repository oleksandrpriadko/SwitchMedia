package com.example.priadko.switchmedia.home_screen.interactor.retrofit_model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

@Root(name = "template")
class Template {
    @Element(name = "author", required = false)
    private Author author;

    @Element(name = "title", required = false)
    private String title;

    @Element(name = "url", required = false)
    private String url;
}
