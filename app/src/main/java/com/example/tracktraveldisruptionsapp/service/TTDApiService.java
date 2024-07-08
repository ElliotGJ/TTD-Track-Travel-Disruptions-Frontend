package com.example.tracktraveldisruptionsapp.service;

import com.example.tracktraveldisruptionsapp.model.BackendMap;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.model.Station;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

import java.util.List;

public interface TTDApiService {

    @GET("journey/1")
    Call<List<BackendMap>> getAllJourneys();

    @POST("journey")
    Call<Journey> postJourneys(@Body Journey journey);

    @PUT("journey/{id}")
    Call<Journey> updateJourney(Journey journey);
}
