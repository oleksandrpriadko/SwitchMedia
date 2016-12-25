package com.example.priadko.switchmedia.home_screen.interactor.retrofit_model;

import retrofit2.http.GET;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface Api {
    @GET("random/")
    retrofit2.Call<PatternModel> getPattern();
}
