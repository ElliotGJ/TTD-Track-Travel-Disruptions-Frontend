package com.example.tracktraveldisruptionsapp.ui.disruptions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracktraveldisruptionsapp.databinding.ItemLayoutDisruptionsBinding;
import com.example.tracktraveldisruptionsapp.databinding.ItemLayoutMainBinding;
import com.example.tracktraveldisruptionsapp.model.BackendMap;
import com.example.tracktraveldisruptionsapp.model.RailDataDTO;
import com.example.tracktraveldisruptionsapp.ui.main.JourneyAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DisruptionsAdapter extends RecyclerView.Adapter<DisruptionsAdapter.DisruptionsViewHolder> {

    RailDataDTO railData;
    Context context;

    public DisruptionsAdapter(Context context, RailDataDTO railData) {
        this.context = context;
        this.railData = railData;
    }

    @NonNull
    @NotNull
    @Override
    public DisruptionsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int i) {
        ItemLayoutDisruptionsBinding binding = ItemLayoutDisruptionsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new DisruptionsAdapter.DisruptionsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DisruptionsViewHolder disruptionsViewHolder, int i) {
        disruptionsViewHolder.disruptionsBinding.setRailData(railData);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DisruptionsViewHolder extends RecyclerView.ViewHolder {

        private ItemLayoutDisruptionsBinding disruptionsBinding;

        public DisruptionsViewHolder(ItemLayoutDisruptionsBinding disruptionsBinding) {
            super(disruptionsBinding.getRoot());
            this.disruptionsBinding = disruptionsBinding;
        }

    }
}
