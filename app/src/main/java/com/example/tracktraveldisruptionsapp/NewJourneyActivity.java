package com.example.tracktraveldisruptionsapp;

import android.content.res.AssetManager;
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

import com.example.tracktraveldisruptionsapp.databinding.ActivityStationSelectionBinding;
import com.example.tracktraveldisruptionsapp.model.Station;
import com.example.tracktraveldisruptionsapp.model.StationList;
import com.example.tracktraveldisruptionsapp.resources.utils;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewJourneyActivity extends AppCompatActivity {

    private ActivityStationSelectionBinding binding;
    private ArrayList<String> arrayList;
    private Dialog dialog;
    private boolean isInputClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_station_selection);

        // Initialize array list
        arrayList = new ArrayList<>(Arrays.asList(
                getString(R.string.dsa_self_paced),
                getString(R.string.complete_interview_prep),
                getString(R.string.amazon_sde_test_series),
                getString(R.string.compiler_design),
                getString(R.string.git_github),
                getString(R.string.python_foundation),
                getString(R.string.operating_systems),
                getString(R.string.theory_of_computation)
        ));

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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(NewJourneyActivity.this, android.R.layout.simple_list_item_1, Arrays.stream(station).map(Station::getStation_name).toArray(String[]::new));
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
                binding.fromInput.setText(adapter.getItem(position));
            } else {

            binding.toInput.setText(adapter.getItem(position));
            }

            // Dismiss dialog
            dialog.dismiss();



        });
    }
}
