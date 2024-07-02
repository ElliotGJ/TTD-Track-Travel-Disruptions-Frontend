package com.example.tracktraveldisruptionsapp.service;

import com.example.tracktraveldisruptionsapp.model.Journey;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface TTDApiService {

    @GET
    Call<List<Journey>> getAllJourneys();
}
