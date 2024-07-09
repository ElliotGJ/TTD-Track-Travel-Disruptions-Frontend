package com.example.tracktraveldisruptionsapp.ui.editjourney;

import android.app.Dialog;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.example.tracktraveldisruptionsapp.R;
import com.example.tracktraveldisruptionsapp.databinding.ActivityEditJourneyBinding;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.model.Station;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class EditJourneyActivity extends AppCompatActivity {

    private ActivityEditJourneyBinding binding;
    private Journey journey;
    private MainActivityViewModel viewModel;
    private final Set<DayOfWeek> selectedDays = new HashSet<>();
    private EditJourneyClickHandlers clickHandlers;
    private boolean isFromInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journey);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_journey);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        journey = getIntent().getParcelableExtra("JOURNEY_OBJECT");

        System.out.println("GET "+ journey);
        System.out.println(journey.getJourneyID());
        Log.e("Edit Journey Activity", "Journey days: " + journey.getDays());


        if (journey != null) {
            binding.setJourney(journey);
            binding.fromInput.setText(journey.getOriginCRS());
            binding.toInput.setText(journey.getDestinationCRS());
            binding.departureTimeInput.setText(journey.getDepartureTime());
            selectedDays.addAll(journey.getDays());

        }

        clickHandlers = new EditJourneyClickHandlers(this, journey, selectedDays, viewModel, binding);

        FloatingActionButton backBtn = findViewById(R.id.fab_back);
        backBtn.setOnClickListener(clickHandlers::onBackClicked);

        ExtendedFloatingActionButton saveBtn = findViewById(R.id.button_next);
        saveBtn.setOnClickListener(clickHandlers::onSaveClicked);

        ExtendedFloatingActionButton deleteButton = findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(v -> clickHandlers.onDeleteClicked(v));

        Button timeBtn = findViewById(R.id.departure_time_input);
        timeBtn.setOnClickListener(view -> clickHandlers.showTimePickerDialog(timeBtn));

        binding.buttonMon.setOnClickListener(clickHandlers::onDayButtonClick);
        binding.buttonTue.setOnClickListener(clickHandlers::onDayButtonClick);
        binding.buttonWed.setOnClickListener(clickHandlers::onDayButtonClick);
        binding.buttonThu.setOnClickListener(clickHandlers::onDayButtonClick);
        binding.buttonFri.setOnClickListener(clickHandlers::onDayButtonClick);
        binding.buttonSat.setOnClickListener(clickHandlers::onDayButtonClick);
        binding.buttonSun.setOnClickListener(clickHandlers::onDayButtonClick);

        binding.fromInput.setOnClickListener(v -> {
            isFromInput = true;
            showDialog();
        });
        binding.toInput.setOnClickListener(v -> {
            isFromInput = false;
            showDialog();
        });

        clickHandlers.updateDayButtonColors();

    }

    private void showDialog() {
        Dialog dialog = new Dialog(EditJourneyActivity.this);
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

        EditText editText = dialog.findViewById(R.id.edit_text);
        ListView listView = dialog.findViewById(R.id.list_view);

        ArrayAdapter<Station> adapter = new ArrayAdapter<>(EditJourneyActivity.this, android.R.layout.simple_list_item_1, station);
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
            Station selectedStation = adapter.getItem(position);
            if (isFromInput) {
                binding.fromInput.setText(selectedStation.getStation_name() + " " + selectedStation.getCrs());
//                binding.fromInput.setText(String.format("  %s (%s)", selectedStation.getStation_name(), selectedStation.getCrs()));

                //journey.setOrigin(selectedStation.getStation_name());
            } else {
                binding.toInput.setText(selectedStation.getStation_name() + " " + selectedStation.getCrs());
                //journey.setDestination(selectedStation.getStation_name());
            }
            dialog.dismiss();
        });
    }
}
