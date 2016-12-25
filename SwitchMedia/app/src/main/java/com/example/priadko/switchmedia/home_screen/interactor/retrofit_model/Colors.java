package com.example.priadko.switchmedia.home_screen.interactor.retrofit_model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */
@Root
class Colors {

    @ElementList(entry = "hex", inline = true, type = Hex.class, required = false)
    List<Hex> hex;
}
