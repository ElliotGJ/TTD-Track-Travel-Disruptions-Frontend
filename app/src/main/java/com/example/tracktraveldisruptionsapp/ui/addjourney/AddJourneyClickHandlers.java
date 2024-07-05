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
import com.example.tracktraveldisruptionsapp.model.JourneyRepository;
import com.example.tracktraveldisruptionsapp.model.Station;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivity;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivityViewModel;

import java.time.DayOfWeek;
import java.util.*;

public class AddJourneyClickHandlers {

//    private Station departure;
    private NewJourneyActivity activity;
//    private Station destination;
    private Context context;
    private MainActivityViewModel viewModel;
    private Map<String, Boolean> buttonStatesMap;


//    public AddJourneyClickHandlers(Station departure, Station destination, Context context, MainActivityViewModel viewModel) {
//        this.departure = departure;
//        this.destination = destination;
//        this.context = context;
//        this.viewModel = viewModel;
//        this.buttonStatesMap = new HashMap<>();
//        setButtonStates();
//    }


    public AddJourneyClickHandlers(NewJourneyActivity activity, Context context, MainActivityViewModel viewModel) {
        this.activity = activity;
        this.context = context;
        this.viewModel = viewModel;
        this.buttonStatesMap = new HashMap<>();
        setButtonStates();
    }

    public void setButtonStates() {
        buttonStatesMap.put("Mon", false);
        buttonStatesMap.put("Tue", false);
        buttonStatesMap.put("Wed", false);
        buttonStatesMap.put("Thu", false);
        buttonStatesMap.put("Fri", false);
        buttonStatesMap.put("Sat", false);
        buttonStatesMap.put("Sun", false);
    }

    public void submitButton(View view) {
        Set<DayOfWeek> frequency = new HashSet<>();
        if(buttonStatesMap.get("Mon")) frequency.add(DayOfWeek.MONDAY);
        if(buttonStatesMap.get("Tue")) frequency.add(DayOfWeek.TUESDAY);
        if(buttonStatesMap.get("Wed")) frequency.add(DayOfWeek.WEDNESDAY);
        if(buttonStatesMap.get("Thu")) frequency.add(DayOfWeek.THURSDAY);
        if(buttonStatesMap.get("Fri")) frequency.add(DayOfWeek.FRIDAY);
        if(buttonStatesMap.get("Sat")) frequency.add(DayOfWeek.SATURDAY);
        if(buttonStatesMap.get("Sun")) frequency.add(DayOfWeek.SUNDAY);

        Station departure = activity.getDeparture();
        Station destination = activity.getDestination();

        //Check for nullPointer
        if (departure == null) {
            Log.e("AddJourneyClickHandlers", "Departure station is null");
            Toast.makeText(context, "Please select a departure station.", Toast.LENGTH_LONG).show();
            return;
        }

        if (destination == null) {
            Log.e("AddJourneyClickHandlers", "Destination station is null");
            Toast.makeText(context, "Please select a destination station.", Toast.LENGTH_LONG).show();
            return;
        }

        Log.d("AddJourneyClickHandlers", "Departure: " + departure.getStation_name() + ", Destination: " + destination.getStation_name());
        //End of check for nullPointer

        JourneyLeg leg1 = new JourneyLeg(departure.getStation_name(), departure.getCrs(), destination.getStation_name(), destination.getCrs(), 1, "Cross-Country");
        Set<JourneyLeg> legs = new HashSet<>();
        legs.add(leg1);

        // Log journey details
        Log.d("AddJourneyClickHandlers", "Adding journey: " + departure.getStation_name() + " to " + destination.getStation_name());

        Journey newJourney = new Journey(false, "BHX", "MAN", frequency, "14:00", legs);
        Log.d("TestAdding", "Adding journey: " +
                newJourney.getNotificationsEnabled() +
                newJourney.getOrigin() +
                newJourney.getDestination() +
                newJourney.getDays() +
                newJourney.getDepartureTime() +
                newJourney.getJourneyLegs());

        viewModel.addJourney(newJourney);

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    public void dayButtonClick(View view) {
        if (view instanceof Button) {
            Button button = (Button) view;
            String day = button.getTag().toString();
            boolean currentState = buttonStatesMap.containsKey(day) ? buttonStatesMap.get(day) : false;
            buttonStatesMap.put(day, !currentState);
            boolean newState = !currentState;
            buttonStatesMap.put(day, newState);
            if (newState) {
                button.setTextColor(Color.parseColor("#38A3A5"));
            } else {
                button.setTextColor(Color.WHITE);
            }
        }
    }


    public void backButton(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void setTime(View view,Button setTimeBtn){

       // on below line we are getting the
       // instance of our calendar.
       final Calendar c = Calendar.getInstance();

       // on below line we are getting our hour, minute.
       int hour = c.get(Calendar.HOUR_OF_DAY);
       int minute = c.get(Calendar.MINUTE);

       // on below line we are initializing our Time Picker Dialog
       TimePickerDialog timePickerDialog = new TimePickerDialog(context,
               (view1, hourOfDay, minute1) -> {
                   // on below line we are setting selected time
                   // in our text view.
                   setTimeBtn.setText(hourOfDay + ":" + minute1);
               }, hour, minute, false);
       // at last we are calling show to
       // display our time picker dialog.
       timePickerDialog.show();

    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker.
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it.
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time the user picks.
        }
    }
}



