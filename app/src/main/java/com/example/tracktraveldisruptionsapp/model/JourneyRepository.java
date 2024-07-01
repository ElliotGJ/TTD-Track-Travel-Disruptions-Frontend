package com.example.tracktraveldisruptionsapp.model;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.tracktraveldisruptionsapp.service.RetrofitInstance;
import com.example.tracktraveldisruptionsapp.service.TTDApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class JourneyRepository {
    private MutableLiveData<List<Journey>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public JourneyRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Journey>> getMutableLiveData() {
        TTDApiService service = RetrofitInstance.getService();
        Call<List<Journey>> call = service.getAllJourneys();
        call.enqueue(new Callback<List<Journey>>() {
            @Override
            public void onResponse(Call<List<Journey>> call, Response<List<Journey>> response) {
                List<Journey> journeys = response.body();
                Log.i("JOURNEYLISTLOG",""+response.code());
                Log.i("JOURNEYLISTLOG", journeys.toString());
                Log.i("JOURNEYLISTLOG","ON SUCCESS");


                mutableLiveData.setValue(journeys);
            }

            @Override
            public void onFailure(Call<List<Journey>> call, Throwable throwable) {

            }
        });
        return mutableLiveData;
    }


}
