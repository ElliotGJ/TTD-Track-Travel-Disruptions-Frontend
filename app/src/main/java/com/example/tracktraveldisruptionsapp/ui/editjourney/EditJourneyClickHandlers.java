package com.example.tracktraveldisruptionsapp.ui.editjourney;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.tracktraveldisruptionsapp.R;
import com.example.tracktraveldisruptionsapp.databinding.ActivityEditJourneyBinding;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivity;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivityViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class EditJourneyClickHandlers {

    private final Context context;
    private final Journey journey;
    private final Set<DayOfWeek> selectedDays;
    private final MainActivityViewModel viewModel;
    private final ActivityEditJourneyBinding binding;

    public EditJourneyClickHandlers(Context context, Journey journey, Set<DayOfWeek> selectedDays, MainActivityViewModel viewModel, ActivityEditJourneyBinding binding) {
        this.context = context;
        this.journey = journey;
        this.selectedDays = selectedDays;
        this.viewModel = viewModel;
        this.binding = binding;
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void onSaveClicked(View view) {
        journey.setDays(selectedDays);

        String departureCrs = binding.fromInput.getText().toString();
        String destinationCrs = binding.toInput.getText().toString();
        String userSelectedTime = binding.departureTimeInput.getText().toString();

        // Validate user inputs
        if (departureCrs.isEmpty()) {
            Toast.makeText(context, "Please select a departure station", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Departure station is empty");
            return;
        }

        if (destinationCrs.isEmpty()) {
            Toast.makeText(context, "Please select a destination station", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Destination station is empty");
            return;
        }

        if (selectedDays.isEmpty()) {
            Toast.makeText(context, "Please select at least one day for the journey", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Frequency is empty");
            return;
        }

        if (userSelectedTime == null || userSelectedTime.isEmpty()) {
            Toast.makeText(context, "Please select a departure time", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Departure time is not set");
            return;
        }

        viewModel.updateJourney(journey.getJourneyID(), journey);
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

//        validateJourneyAndSubmit(journey);

    }

//    private void validateJourneyAndSubmit(@NonNull Journey journey) {
//        viewModel.validateJourney(journey, new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    // If the journey is valid, add the journey
//                    viewModel.addJourney(journey);
//                } else {
//                    // If no route is found
//                    Toast.makeText(context, "No route found for the selected stations. Please try different stations.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                // If validation fails
//                Toast.makeText(context, "Failed to validate journey. Please try again later", Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "Failed to validate journey", t);
//            }
//        });
//    }



    public void onDeleteClicked(View view) {
        Log.e("Delete", "Delete journey: " + journey.getJourneyID());
        viewModel.deleteJourney(journey.getJourneyID());
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void showTimePickerDialog(Button timeBtn) {
        LocalTime currentTime = LocalTime.parse(journey.getDepartureTime(), DateTimeFormatter.ofPattern("hh:mm"));
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                (TimePicker view, int hourOfDay, int minute) -> {
                    String time = String.format("%02d:%02d", hourOfDay, minute);
                    timeBtn.setText(time);
                    journey.setDepartureTime(time);
                }, currentTime.getHour(), currentTime.getMinute(), true);
        timePickerDialog.show();
    }

    public void onDayButtonClick(View view) {
        if (view instanceof Button) {
            Button button = (Button) view;
            DayOfWeek day = getDayOfWeekFromButton(button);
            if (selectedDays.contains(day)) {
                selectedDays.remove(day);
            } else {
                selectedDays.add(day);
            }
            setDayButtonColor(button, day);
        }
    }

    public DayOfWeek getDayOfWeekFromButton(Button button) {
        int id = button.getId();
        if (id == R.id.button_mon) return DayOfWeek.MONDAY;
        if (id == R.id.button_tue) return DayOfWeek.TUESDAY;
        if (id == R.id.button_wed) return DayOfWeek.WEDNESDAY;
        if (id == R.id.button_thu) return DayOfWeek.THURSDAY;
        if (id == R.id.button_fri) return DayOfWeek.FRIDAY;
        if (id == R.id.button_sat) return DayOfWeek.SATURDAY;
        if (id == R.id.button_sun) return DayOfWeek.SUNDAY;
        throw new IllegalArgumentException("Unknown button ID");
    }

    public void updateDayButtonColors() {
        setDayButtonColor(binding.buttonMon, DayOfWeek.MONDAY);
        setDayButtonColor(binding.buttonTue, DayOfWeek.TUESDAY);
        setDayButtonColor(binding.buttonWed, DayOfWeek.WEDNESDAY);
        setDayButtonColor(binding.buttonThu, DayOfWeek.THURSDAY);
        setDayButtonColor(binding.buttonFri, DayOfWeek.FRIDAY);
        setDayButtonColor(binding.buttonSat, DayOfWeek.SATURDAY);
        setDayButtonColor(binding.buttonSun, DayOfWeek.SUNDAY);
    }

    public void setDayButtonColor(Button button, DayOfWeek day) {
        button.setTextColor(selectedDays.contains(day) ? Color.parseColor("#38A3A5") : Color.WHITE);
    }
}
