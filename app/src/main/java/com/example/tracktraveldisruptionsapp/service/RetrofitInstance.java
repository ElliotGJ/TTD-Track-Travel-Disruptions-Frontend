package com.example.tracktraveldisruptionsapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit rfInstance = null;
    private static final String URL = "http://10.0.2.2:8080/api/v1/";


    public static TTDApiService getService(){
        if (rfInstance == null){
            rfInstance = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return rfInstance.create(TTDApiService.class);
    }
}
