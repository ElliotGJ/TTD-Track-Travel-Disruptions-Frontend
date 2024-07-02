package com.example.tracktraveldisruptionsapp.ui.addjourney;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import androidx.fragment.app.DialogFragment;
import com.example.tracktraveldisruptionsapp.model.Journey;
import com.example.tracktraveldisruptionsapp.model.JourneyLeg;
import com.example.tracktraveldisruptionsapp.model.Station;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivity;
import com.example.tracktraveldisruptionsapp.ui.main.MainActivityViewModel;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class AddJourneyClickHandlers {

    private Station departure;
    private Station destination;
    private Context context;
    private MainActivityViewModel viewModel;

    public AddJourneyClickHandlers(Station departure, Station destination, Context context, MainActivityViewModel viewModel) {
        this.departure = departure;
        this.destination = destination;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void submitButton(View view) {
        Set<DayOfWeek> frequency = new HashSet<>();
        frequency.add(DayOfWeek.MONDAY);
        JourneyLeg leg1 = new JourneyLeg(departure.getStation_name(), departure.getCrs(), destination.getStation_name(), destination.getCrs(), 1, "Cross-Country");
        Set<JourneyLeg> legs = new HashSet<>();
        legs.add(leg1);
        viewModel.addJourney(new Journey(false, departure.getStation_name(), destination.getStation_name(), frequency, "14:00", legs));
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
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



