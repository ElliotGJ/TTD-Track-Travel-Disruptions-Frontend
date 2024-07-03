package com.example.tracktraveldisruptionsapp.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracktraveldisruptionsapp.databinding.ItemLayoutMainBinding;
import com.example.tracktraveldisruptionsapp.model.Journey;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.JourneyViewHolder>{

    List<Journey> journeys;
    Context context;


    public JourneyAdapter(List<Journey> journeys, Context context) {
        this.journeys = journeys;
        this.context = context;


    }

    @NonNull
    @NotNull
    @Override
    public JourneyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int i) {
        ItemLayoutMainBinding binding = ItemLayoutMainBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new JourneyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull JourneyViewHolder journeyView, int position) {
        Journey journey = journeys.get(position);
        journeyView.itemLayoutBinding.setJourney(journey);
        frequencyColourSetter(journey.getDays(),journeyView);


    }

    @Override
    public int getItemCount() {
        return journeys.size();
    }

    private void frequencyColourSetter(Set<DayOfWeek> days, JourneyViewHolder journeyView ){
        if (days.contains(DayOfWeek.MONDAY)) {
            journeyView.itemLayoutBinding.freqM.setTextColor(Color.RED);
        }
        if (days.contains(DayOfWeek.TUESDAY)) {
            journeyView.itemLayoutBinding.freqTu.setTextColor(Color.RED);
        }
        if (days.contains(DayOfWeek.WEDNESDAY)) {
            journeyView.itemLayoutBinding.freqW.setTextColor(Color.RED);
        }
        if (days.contains(DayOfWeek.THURSDAY)) {
            journeyView.itemLayoutBinding.freqTh.setTextColor(Color.RED);
        }
        if (days.contains(DayOfWeek.FRIDAY)) {
            journeyView.itemLayoutBinding.freqF.setTextColor(Color.RED);
        }
        if (days.contains(DayOfWeek.SATURDAY)) {
            journeyView.itemLayoutBinding.freqSat.setTextColor(Color.RED);
        }
        if (days.contains(DayOfWeek.SUNDAY)) {
            journeyView.itemLayoutBinding.freqSun.setTextColor(Color.RED);
        }

    }


    public static class JourneyViewHolder extends RecyclerView.ViewHolder {

        private ItemLayoutMainBinding itemLayoutBinding;

        public JourneyViewHolder(ItemLayoutMainBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }

    }
}
