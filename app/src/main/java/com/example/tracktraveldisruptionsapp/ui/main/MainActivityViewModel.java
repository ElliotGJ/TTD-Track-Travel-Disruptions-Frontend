package com.example.tracktraveldisruptionsapp.ui.main;

import androidx.lifecycle.AndroidViewModel;
import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.model.JourneyRepository;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    JourneyRepository journeyRepository;


    public MainActivityViewModel(@NotNull Application application) {
        super(application);
        this.journeyRepository = new JourneyRepository(application);
    }

    public MutableLiveData<List<Journey>> getRepositoryLiveData(){
        return journeyRepository.getMutableLiveData();
    }

    public void addJourney(Journey journey){
        journeyRepository.postJourneys(journey);
        journeyRepository.getMutableLiveData();
    }
}
