package com.example.tracktraveldisruptionsapp.ui.addjourney;

import android.content.res.AssetManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.lifecycle.ViewModelProvider;
import com.example.tracktraveldisruptionsapp.R;
import com.example.tracktraveldisruptionsapp.databinding.ActivityStationSelectionBinding;
import com.example.tracktraveldisruptionsapp.model.Station;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class NewJourneyActivity extends AppCompatActivity {

    private ActivityStationSelectionBinding binding;
    private ArrayList<String> arrayList;
    private Dialog dialog;
    private boolean isInputClicked = false;
    private Station departure;
    private Station destination;


    public Station getDestination() {
        return destination;
    }

    public Station getDeparture() {
        return departure;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_station_selection);

        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        AddJourneyClickHandlers clickHandlers = new AddJourneyClickHandlers(departure,destination,this,viewModel);

        FloatingActionButton backBtn = findViewById(R.id.fab_back);
        backBtn.setOnClickListener(clickHandlers::backButton);

        ExtendedFloatingActionButton submitBtn = findViewById(R.id.button_next);
        submitBtn.setOnClickListener(clickHandlers::submitButton);

        Button timeBtn = findViewById(R.id.departure_time_input);
        timeBtn.setOnClickListener(view -> clickHandlers.setTime(view,timeBtn));

        binding.buttonMon.setOnClickListener(clickHandlers::dayButtonClick);
//        Button monBtn = findViewById(R.id.button_mon);
//        monBtn.setOnClickListener(clickHandlers::dayButtonClick);
        Button tueBtn = findViewById(R.id.button_tue);
        tueBtn.setOnClickListener(clickHandlers::dayButtonClick);
        Button wedBtn = findViewById(R.id.button_wed);
        wedBtn.setOnClickListener(clickHandlers::dayButtonClick);
        Button thuBtn = findViewById(R.id.button_thu);
        thuBtn.setOnClickListener(clickHandlers::dayButtonClick);
        Button friBtn = findViewById(R.id.button_fri);
        friBtn.setOnClickListener(clickHandlers::dayButtonClick);
        Button satBtn = findViewById(R.id.button_sat);
        satBtn.setOnClickListener(clickHandlers::dayButtonClick);
        Button sunBtn = findViewById(R.id.button_sun);
        sunBtn.setOnClickListener(clickHandlers::dayButtonClick);





        binding.fromInput.setOnClickListener(v -> {
            isInputClicked = true;
            showDialog();
        });
        binding.toInput.setOnClickListener(v -> {
            isInputClicked = false;
            showDialog();
        });
    }

    private void showDialog() {
        dialog = new Dialog(NewJourneyActivity.this);

        // Set custom dialog
        dialog.setContentView(R.layout.dialog_searchable_spinner);
        dialog.getWindow().setLayout(650, 800);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        BufferedReader br;
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("uk-train-stations.json");
            br = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Station[] station = new Gson().fromJson(br, Station[].class);

        // Initialize and assign variable
        EditText editText = dialog.findViewById(R.id.edit_text);
        ListView listView = dialog.findViewById(R.id.list_view);

        // Initialize array adapter
        ArrayAdapter<Station> adapter = new ArrayAdapter<>(NewJourneyActivity.this, android.R.layout.simple_list_item_1, station);
        listView.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            // checks if input is clicked then updates the correct text view
            if(isInputClicked)
            // Set selected item on TextView
            {

                binding.fromInput.setText("  "+adapter.getItem(position).getStation_name());
                departure = adapter.getItem(position);
            } else {

                destination = adapter.getItem(position);
                binding.toInput.setText("  "+adapter.getItem(position).getStation_name());

            }

            // Dismiss dialog
            dialog.dismiss();



        });
    }
}
