package com.example.tracktraveldisruptionsapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracktraveldisruptionsapp.R;
import com.example.tracktraveldisruptionsapp.databinding.ActivityMainBinding;
import com.example.tracktraveldisruptionsapp.model.Journey;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Journey> journeys;
    private JourneyAdapter journeyAdapter;
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        getAllJourneys();

    }

    private void getAllJourneys(){
        viewModel.getRepositoryLiveData().observe(this, journeyList -> {
            journeys = (ArrayList<Journey>) journeyList;
            displayInRecyclerView();
        });


    }

    private void displayInRecyclerView(){
        recyclerView = binding.recyclerView;
        journeyAdapter = new JourneyAdapter(journeys,this);
        recyclerView.setAdapter(journeyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        journeyAdapter.notifyDataSetChanged();

    }
}