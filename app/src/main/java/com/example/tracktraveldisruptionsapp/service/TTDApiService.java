package com.example.tracktraveldisruptionsapp.service;

import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.model.Station;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

import java.util.List;

public interface TTDApiService {

    @GET("journey")
    Call<List<Journey>> getAllJourneys();

    @POST("journey")
    Call<Journey> postJourneys();

    @PUT("journey/{id}")
    Call<Journey> updateJourney(Journey journey);
}
