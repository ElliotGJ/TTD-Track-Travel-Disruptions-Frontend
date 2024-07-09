package com.example.tracktraveldisruptionsapp.ui.disruptions;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracktraveldisruptionsapp.databinding.ItemLayoutDisruptionsBinding;
import com.example.tracktraveldisruptionsapp.model.RailDataDTO;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DisruptionsAdapter extends RecyclerView.Adapter<DisruptionsAdapter.DisruptionsViewHolder> {

    List<RailDataDTO> journeysRailData;
    Context context;

    public DisruptionsAdapter(Context context, List<RailDataDTO> journeysRailData) {
        this.context = context;
        this.journeysRailData = journeysRailData;
    }

    @NonNull
    @NotNull
    @Override
    public DisruptionsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int i) {
        ItemLayoutDisruptionsBinding binding = ItemLayoutDisruptionsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new DisruptionsAdapter.DisruptionsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DisruptionsViewHolder disruptionsViewHolder, int position) {
        RailDataDTO railData = journeysRailData.get(position);
        disruptionsViewHolder.disruptionsBinding.setRailData(railData);
        Log.i("ONBIND", journeysRailData.toString());
    }

    @Override
    public int getItemCount() {
        return journeysRailData.size();
    }

    public static class DisruptionsViewHolder extends RecyclerView.ViewHolder {

        private ItemLayoutDisruptionsBinding disruptionsBinding;

        public DisruptionsViewHolder(ItemLayoutDisruptionsBinding disruptionsBinding) {
            super(disruptionsBinding.getRoot());
            this.disruptionsBinding = disruptionsBinding;
        }

    }
}
