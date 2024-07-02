package com.example.tracktraveldisruptionsapp.service;

import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.model.Station;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface TTDApiService {

    @GET
    Call<List<Journey>> getAllJourneys();

    @POST
    Call<Journey> postJourneys();
}
