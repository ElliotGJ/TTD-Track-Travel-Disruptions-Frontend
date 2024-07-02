package com.example.tracktraveldisruptionsapp.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracktraveldisruptionsapp.databinding.ItemLayoutMainBinding;
import com.example.tracktraveldisruptionsapp.model.Journey;
import org.jetbrains.annotations.NotNull;
import java.util.List;

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

    }

    @Override
    public int getItemCount() {
        return journeys.size();
    }



    public static class JourneyViewHolder extends RecyclerView.ViewHolder {

        private ItemLayoutMainBinding itemLayoutBinding;

        public JourneyViewHolder(ItemLayoutMainBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }

    }
}
