package com.example.tracktraveldisruptionsapp.ui.editjourney;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import com.example.tracktraveldisruptionsapp.R;
import com.example.tracktraveldisruptionsapp.databinding.ActivityEditJourneyBinding;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivity;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivityViewModel;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

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
        viewModel.updateJourney(journey);
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void showTimePickerDialog(Button timeBtn) {
        LocalTime currentTime = LocalTime.parse(journey.getDepartureTime(), DateTimeFormatter.ofPattern("HH:mm"));
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

    private DayOfWeek getDayOfWeekFromButton(Button button) {
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

    private void setDayButtonColor(Button button, DayOfWeek day) {
        button.setTextColor(selectedDays.contains(day) ? Color.parseColor("#38A3A5") : Color.WHITE);
    }
}
