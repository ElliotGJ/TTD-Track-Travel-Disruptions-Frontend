package com.example.tracktraveldisruptionsapp.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import com.example.tracktraveldisruptionsapp.service.RetrofitInstance;
import com.example.tracktraveldisruptionsapp.service.TTDApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class JourneyRepository {

    private MutableLiveData<List<BackendMap>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public JourneyRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<BackendMap>> getMutableLiveData() {
        TTDApiService service = RetrofitInstance.getService();
        Call<List<BackendMap>> call = service.getAllJourneys();
        call.enqueue(new Callback<List<BackendMap>>() {
            @Override
            public void onResponse(Call<List<BackendMap>> call, Response<List<BackendMap>> response) {
                List<BackendMap> journeys = response.body();
                Log.i("JOURNEYLISTLOG",""+response.code());
                Log.i("JOURNEYLISTLOG", journeys.toString());
                Log.i("JOURNEYLISTLOG","ON SUCCESS");


                mutableLiveData.setValue(journeys);
            }

            @Override
            public void onFailure(Call<List<BackendMap>> call, Throwable throwable) {
                Log.i("JOURNEYLISTLOG","FAILURE");
                Log.i("JOURNEYLISTLOG",throwable.getLocalizedMessage());
            }
        });
        return mutableLiveData;
    }

    public void postJourneys(Journey journey) {
        TTDApiService service = RetrofitInstance.getService();
        Call<Journey> call = service.postJourneys(journey);
        call.enqueue(new Callback<Journey>() {
            @Override
            public void onResponse(Call<Journey> call, Response<Journey> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(application.getApplicationContext(), "Journey added successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application.getApplicationContext(), "Failed to add new journey! " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("postJourney", "Failed to add new journey: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Journey> call, Throwable throwable) {
                Toast.makeText(application.getApplicationContext(), "Failed to add new journey!", Toast.LENGTH_SHORT).show();
                Log.e("postJourney", "onFailure: " + throwable.getLocalizedMessage());
            }
        });
    }

    public void updateJourney(Journey journey) {
        TTDApiService service = RetrofitInstance.getService();
        Call<Journey> call = service.updateJourney(journey);
        call.enqueue(new Callback<Journey>() {
            @Override
            public void onResponse(Call<Journey> call, Response<Journey> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(application.getApplicationContext(),"Journey updated successfully.",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application.getApplicationContext(),"Failed to update journey!",Toast.LENGTH_SHORT).show();
                    Log.e("JourneyRepository", "Failed to update journey: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Journey> call, Throwable throwable) {
                Toast.makeText(application.getApplicationContext(),"Failed to update journey!",Toast.LENGTH_SHORT).show();
                Log.e("JourneyRepository", "Failed to update journey: " + throwable.getMessage());
            }
        });
    }

    public void validateJourney(Journey journey, Callback<Void> callback){
        TTDApiService service = RetrofitInstance.getService();
        Call<Void> call = service.validateJourney(journey);
        call.enqueue(callback);
    }

}
