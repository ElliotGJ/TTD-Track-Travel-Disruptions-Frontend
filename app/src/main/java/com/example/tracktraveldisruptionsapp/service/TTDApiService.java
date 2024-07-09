package com.example.tracktraveldisruptionsapp.service;

import com.example.tracktraveldisruptionsapp.model.BackendMap;
import com.example.tracktraveldisruptionsapp.model.Journey;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TTDApiService {

    @GET("journey/1")
    Call<List<BackendMap>> getAllJourneys();

    @POST("journey")
    Call<Journey> postJourneys(@Body Journey journey);

    @PUT("journey/{id}")
    Call<Journey> updateJourney(@Path("id") Long Id, @Body Journey journey);
    @PUT("journey/validate")
    Call<Void> validateJourney(@Body Journey journey);

    @DELETE("journey/{id}")
    Call<Void> deleteJourney(@Path("id") Long id);

}
