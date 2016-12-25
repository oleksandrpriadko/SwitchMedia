package com.example.priadko.switchmedia.home_screen.interactor.retrofit_model;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */
@Root
class Hex {
    @Text(required = false)
    String hex;
}