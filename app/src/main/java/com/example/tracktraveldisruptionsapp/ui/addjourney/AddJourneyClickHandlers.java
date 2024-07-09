package com.example.tracktraveldisruptionsapp.ui.addjourney;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.model.JourneyLeg;
import com.example.tracktraveldisruptionsapp.model.Station;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivity;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivityViewModel;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.time.DayOfWeek;
import java.util.*;

public class AddJourneyClickHandlers {

    private NewJourneyActivity activity;
    private Context context;
    private MainActivityViewModel viewModel;
    private Map<String, Boolean> buttonStatesMap;
    private static final String TAG = "AddJourneyClickHandlers";

    private String userSelectedTime = ""; // Default time

    public AddJourneyClickHandlers(NewJourneyActivity activity, Context context, MainActivityViewModel viewModel) {
        this.activity = activity;
        this.context = context;
        this.viewModel = viewModel;
        this.buttonStatesMap = new HashMap<>();
        initializeButtonStates();
    }

    private void initializeButtonStates() {
        buttonStatesMap.put("Mon", false);
        buttonStatesMap.put("Tue", false);
        buttonStatesMap.put("Wed", false);
        buttonStatesMap.put("Thu", false);
        buttonStatesMap.put("Fri", false);
        buttonStatesMap.put("Sat", false);
        buttonStatesMap.put("Sun", false);
    }

    public void submitButton(View view) {
        Set<DayOfWeek> frequency = getSelectedDays();

        Station departure = activity.getDeparture();
        Station destination = activity.getDestination();

        // Validate user inputs
        if (departure == null) {
            Toast.makeText(context, "Please select a departure station", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Departure station is null");
            return;
        }

        if (destination == null) {
            Toast.makeText(context, "Please select a destination station", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Destination station is null");
            return;
        }

        if (frequency.isEmpty()) {
            Toast.makeText(context, "Please select at least one day for the journey", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Frequency is empty");
            return;
        }

        if (userSelectedTime.isEmpty()) {
            Toast.makeText(context, "Please select a departure time", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Departure time is not set");
            return;
        }

        Log.d(TAG, "Adding journey: " + departure.getStation_name() + " to " + destination.getStation_name());
        Log.d(TAG, "Adding journey: " + departure.getCrs() + " to " + destination.getCrs());

        List<JourneyLeg> journeyLegs = new ArrayList<>();
        journeyLegs.add(new JourneyLeg(departure.getStation_name(), departure.getCrs(), destination.getStation_name(), destination.getCrs()));

        Journey newJourney = new Journey(1L, true, departure.getCrs(), destination.getCrs(), frequency, userSelectedTime, journeyLegs);

        Gson gson = new Gson();
        String json = gson.toJson(newJourney);
        Log.d(TAG, "Journey JSON: " + json);

        viewModel.addJourney(newJourney);
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public Set<DayOfWeek> getSelectedDays() {
        Set<DayOfWeek> frequency = new HashSet<>();
        if (buttonStatesMap.get("Mon")) frequency.add(DayOfWeek.MONDAY);
        if (buttonStatesMap.get("Tue")) frequency.add(DayOfWeek.TUESDAY);
        if (buttonStatesMap.get("Wed")) frequency.add(DayOfWeek.WEDNESDAY);
        if (buttonStatesMap.get("Thu")) frequency.add(DayOfWeek.THURSDAY);
        if (buttonStatesMap.get("Fri")) frequency.add(DayOfWeek.FRIDAY);
        if (buttonStatesMap.get("Sat")) frequency.add(DayOfWeek.SATURDAY);
        if (buttonStatesMap.get("Sun")) frequency.add(DayOfWeek.SUNDAY);
        return frequency;
    }

    public void dayButtonClick(View view) {
        if (view instanceof Button) {
            Button button = (Button) view;
            String day = button.getTag().toString();
            boolean currentState = buttonStatesMap.getOrDefault(day, false);
            buttonStatesMap.put(day, !currentState);
            button.setTextColor(currentState ? Color.WHITE : Color.parseColor("#38A3A5"));
        }
    }

    public void backButton(View view) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public void setTime(View view, Button setTimeBtn) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, (view1, hourOfDay, minute1) -> {
            userSelectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1);
            setTimeBtn.setText(userSelectedTime);
        }, hour, minute, false);
        timePickerDialog.show();
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        private AddJourneyClickHandlers clickHandlers;

        public TimePickerFragment(AddJourneyClickHandlers clickHandlers) {
            this.clickHandlers = clickHandlers;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
            clickHandlers.userSelectedTime = selectedTime;
        }
    }
}
