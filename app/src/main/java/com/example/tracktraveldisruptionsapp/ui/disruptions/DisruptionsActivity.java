package com.example.tracktraveldisruptionsapp.ui.disruptions;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracktraveldisruptionsapp.R;
import com.example.tracktraveldisruptionsapp.databinding.ActivityDisruptionBinding;
import com.example.tracktraveldisruptionsapp.databinding.ActivityMainBinding;
import com.example.tracktraveldisruptionsapp.databinding.ActivityStationSelectionBinding;
import com.example.tracktraveldisruptionsapp.model.BackendMap;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.model.RailDataDTO;
import com.example.tracktraveldisruptionsapp.resources.ItemSpaceDecorator;
import com.example.tracktraveldisruptionsapp.ui.addjourney.NewJourneyActivity;
import com.example.tracktraveldisruptionsapp.ui.editjourney.EditJourneyActivity;
import com.example.tracktraveldisruptionsapp.ui.main.JourneyAdapter;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivity;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DisruptionsActivity extends AppCompatActivity {

    private ActivityDisruptionBinding binding;
    private RecyclerView recyclerView;
    private List<RailDataDTO> journeysRailData;
    private DisruptionsAdapter disruptionsAdapter;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disruption);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_disruption);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        RailDataDTO railData =getIntent().getParcelableExtra("journey_raildata");
        journeysRailData = new ArrayList<>();
        journeysRailData.add(railData);


        Log.i("DisruptionCRS",railData.toString());

        FloatingActionButton button = findViewById(R.id.fab_back);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(DisruptionsActivity.this, MainActivity.class);
            startActivity(intent);
        });
        getDisruptionInfo();
    }

   private void getDisruptionInfo(){
        displayInRecyclerView();

   }

    private void displayInRecyclerView(){
        recyclerView = binding.recyclerView;
        disruptionsAdapter = new DisruptionsAdapter(this,journeysRailData);
        recyclerView.setAdapter(disruptionsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ItemSpaceDecorator decorator = new ItemSpaceDecorator(40);
        recyclerView.addItemDecoration(decorator);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        disruptionsAdapter.notifyDataSetChanged();

    }

}
