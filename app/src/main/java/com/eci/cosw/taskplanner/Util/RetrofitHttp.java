package com.eci.cosw.taskplanner.Util;

import com.eci.cosw.taskplanner.Service.AuthService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHttp {

    private Retrofit retrofit;

    public RetrofitHttp(String url) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
