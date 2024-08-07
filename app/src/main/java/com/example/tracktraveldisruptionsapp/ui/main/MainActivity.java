package com.example.tracktraveldisruptionsapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracktraveldisruptionsapp.databinding.ActivityMainBinding;
import com.example.tracktraveldisruptionsapp.model.BackendMap;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.resources.ItemSpaceDecorator;
import com.example.tracktraveldisruptionsapp.ui.addjourney.NewJourneyActivity;
import com.example.tracktraveldisruptionsapp.R;
import com.example.tracktraveldisruptionsapp.ui.editjourney.EditJourneyActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<BackendMap> journeys;
    private JourneyAdapter journeyAdapter;
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        FloatingActionButton button = findViewById(R.id.addbutton);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewJourneyActivity.class);
            startActivity(intent);
        });

        FloatingActionButton refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(view -> {
            getAllJourneys();
        });
        //Refactor the displayInRecyclerView method into here
        recyclerView = binding.recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ItemSpaceDecorator decorator = new ItemSpaceDecorator(40);
        recyclerView.addItemDecoration(decorator);

        journeyAdapter = new JourneyAdapter(new ArrayList<>(), this, view -> {
            BackendMap backendMap = (BackendMap) view.getTag();
            Journey journey = backendMap.getJourneyDTO();
            Intent intent = new Intent(MainActivity.this, EditJourneyActivity.class);
            intent.putExtra("journey", journey);
            startActivity(intent);
        });

        //Prevent repeat added space between items
        recyclerView.setAdapter(journeyAdapter);

        getAllJourneys();
    }

    private void getAllJourneys(){
        viewModel.getRepositoryLiveData().observe(this, journeyList -> {
            journeys = (ArrayList<BackendMap>) journeyList;
//            System.out.println("journeys =" + journeys);
            if (journeys == null || journeys.isEmpty()){
                showAddJourneyMessage(true);
            }else {
                showAddJourneyMessage(false);
            }

            journeyAdapter.updateJourneys(journeys);
        });

    }


    private void displayInRecyclerView(){
        recyclerView = binding.recyclerView;
        journeyAdapter = new JourneyAdapter(journeys,this,view->{
            BackendMap backendMap = (BackendMap) view.getTag();
            Journey journey = backendMap.getJourneyDTO();
            Intent intent = new Intent(MainActivity.this, EditJourneyActivity.class);
            System.out.println("JourneysObj "+ journey);
            intent.putExtra("JOURNEY_OBJECT", journey);
//            intent.putExtra("journeyID", journey.getJourneyID());

            startActivity(intent);
        });

        recyclerView.setAdapter(journeyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ItemSpaceDecorator decorator = new ItemSpaceDecorator(40);
        recyclerView.addItemDecoration(decorator);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        journeyAdapter.notifyDataSetChanged();

    }


    private void showAddJourneyMessage(boolean visible){
        if(visible) {
            binding.noJourneyImg.setVisibility(View.VISIBLE);
            binding.noJourneyMsg.setVisibility(View.VISIBLE);
        }else{
            binding.noJourneyImg.setVisibility(View.INVISIBLE);
            binding.noJourneyMsg.setVisibility(View.INVISIBLE);
        }

    }

}
