package com.example.tracktraveldisruptionsapp.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracktraveldisruptionsapp.R;
import com.example.tracktraveldisruptionsapp.databinding.ItemLayoutMainBinding;
import com.example.tracktraveldisruptionsapp.model.BackendMap;
import com.example.tracktraveldisruptionsapp.model.Journey;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.JourneyViewHolder>{


    List<BackendMap> journeys;
    Context context;
    View.OnClickListener editClickListener;
    private static OnClickListener onClickListener;


    public JourneyAdapter(List<BackendMap> journeys, Context context,View.OnClickListener editClickListener) {
        this.journeys = journeys;
        this.context = context;
        this.editClickListener = editClickListener;

    }

    public JourneyAdapter(List<BackendMap> journeys, Context context) {
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
        BackendMap journey = journeys.get(position);
        journeyView.itemLayoutBinding.setJourney(journey.getJourneyDTO());
        journeyView.itemLayoutBinding.setRaildata(journey.getRailDataDTO());
        frequencyColourSetter(journey.getJourneyDTO().getDays(),journeyView);
        imageSetter(journey.getRailDataDTO().getStd(),journey.getRailDataDTO().getEtd(),journeyView);
        journeyView.itemLayoutBinding.editButton.setTag(journey);
        journeyView.itemLayoutBinding.editButton.setOnClickListener(editClickListener);

       journeyView.itemView.setOnClickListener(view ->{
           if (onClickListener != null){
               onClickListener.onClick(position, journey.getRailDataDTO());
           }
       });

    }

    @Override
    public int getItemCount() {
        if(journeys == null){
            return 0;
        }
        return journeys.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class JourneyViewHolder extends RecyclerView.ViewHolder {

        private ItemLayoutMainBinding itemLayoutBinding;

        public JourneyViewHolder(ItemLayoutMainBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;

            itemView.setOnClickListener(view -> {
                if (onClickListener != null) {
                    onClickListener.onClick(getAdapterPosition(), itemLayoutBinding.getRaildata());
                }
            });
        }

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

    private void imageSetter(String std, String etd,JourneyViewHolder journeyView) {
        ImageView journeyIcon = journeyView.itemLayoutBinding.journeyLineImg;
        TextView textView = journeyView.itemLayoutBinding.serviceInfo;
        if (etd == null) {
            journeyIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pendinginfo));
            textView.setText("Service Info will be avaliable within 2 hours of departure time.");
        }else if (etd.equalsIgnoreCase("On time")) {
            journeyIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.journeyok));
            textView.setText("Service On Time");
        } else if (!etd.equalsIgnoreCase(std)) {
            journeyIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.journeydisrupt_two));
            textView.setText("1 Delay!");
        } else if (etd.equalsIgnoreCase("Cancelled")) {
            journeyIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.journeydisrupt_two));
            textView.setText("Service Cancelled!");
        }
    }
}
