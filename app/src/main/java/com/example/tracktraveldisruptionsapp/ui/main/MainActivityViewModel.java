package com.example.tracktraveldisruptionsapp.ui.main;

import androidx.lifecycle.AndroidViewModel;
import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import com.example.tracktraveldisruptionsapp.model.BackendMap;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.model.JourneyRepository;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    JourneyRepository journeyRepository;


    public MainActivityViewModel(@NotNull Application application) {
        super(application);
        this.journeyRepository = new JourneyRepository(application);
    }

    public MutableLiveData<List<BackendMap>> getRepositoryLiveData(){
        return journeyRepository.getMutableLiveData();
    }

    public void addJourney(Journey journey){
        journeyRepository.postJourneys(journey);
    }

    public void updateJourney(Long id, Journey journey) {
        journeyRepository.updateJourney(id , journey);
    }
    public void deleteJourney( long id) {
        journeyRepository.deleteJourney(id);
    }
//    public void validateJourney(Journey journey, Callback<Void> callback){
//        journeyRepository.validateJourney(journey, callback);
//    }
//
//    public void validateAddNewJourney(Journey journey, Callback<Void> callback){
//        journeyRepository.validateJourney(journey, callback);
//    }
}
