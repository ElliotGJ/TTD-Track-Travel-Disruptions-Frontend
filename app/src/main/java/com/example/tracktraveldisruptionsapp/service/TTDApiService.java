package com.example.tracktraveldisruptionsapp.service;

import com.example.tracktraveldisruptionsapp.model.BackendMap;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.model.Station;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TTDApiService {

    @GET("journey/1")
    Call<List<BackendMap>> getAllJourneys();

    @POST("journey")
    Call<Journey> postJourneys(@Body Journey journey);

    @PUT("journey/{id}")
    Call<Journey> updateJourney(Journey journey);

//    @GET("journey/{id}")
//    Call<BackendMap> getJourneyByID(@Path("id") Long id);
}
