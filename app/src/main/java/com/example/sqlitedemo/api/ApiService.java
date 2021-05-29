package com.example.sqlitedemo.api;

import com.example.sqlitedemo.model.Covid;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by congnguyen on 5/29/21.
 */
public interface ApiService {
//    link api https://corona.lmao.ninja/v2/all
    Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd:mm:ss").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://corona.lmao.ninja")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("/v2/all")
    Call<Covid> getCovid();
}
