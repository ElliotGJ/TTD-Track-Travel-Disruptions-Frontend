package com.example.tracktraveldisruptionsapp.ui.disruptions;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tracktraveldisruptionsapp.R;
import com.example.tracktraveldisruptionsapp.databinding.ItemLayoutDisruptionsBinding;
import com.example.tracktraveldisruptionsapp.model.RailDataDTO;
import com.example.tracktraveldisruptionsapp.ui.main.JourneyAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DisruptionsAdapter extends RecyclerView.Adapter<DisruptionsAdapter.DisruptionsViewHolder> {

    List<RailDataDTO> journeysRailData;
    Context context;
    ItemLayoutDisruptionsBinding binding;

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
        binding = disruptionsViewHolder.disruptionsBinding;
        binding.setRailData(railData);
        imageSetter(binding.statusPic,railData.getEtd(),railData.getStd());
        textSetter(binding.description,binding.etd,binding.eta,railData.getStd(),railData.getEta(),railData.getSta(),railData.getEtd(),railData.getCancelReason(),railData.getDelayReason());


    }

    private void textSetter(TextView description, TextView etdTV, TextView etaTV,String std,String eta,String sta, String etd, String cancelReason, String delayReason) {
        if(etd == null){
            description.setText("Delay information is only avaliable within 2 hours of departure time!");
        }else if(etd.equalsIgnoreCase("On time")){
            etdTV.setTextColor(Color.parseColor("#4bae4f"));
            description.setText("Service on time, No disruptions reported along your journey.");
        }else if(cancelReason!=null){
            description.setText(cancelReason);
            etdTV.setTextColor(Color.RED);
        }else if(delayReason!=null){
            etdTV.setTextColor(Color.RED);
            description.setText(delayReason);
        }else if(!etd.equals(std)){
            etdTV.setTextColor(Color.RED);
            description.setText("Train has been delayed!");
        }

        if(eta != null) {
            if(cancelReason!= null){
                etaTV.setTextColor(Color.RED);
                journeysRailData.get(0).setEta("Cancelled");
            }else if (eta.equalsIgnoreCase("On time")) {
                etaTV.setTextColor(Color.parseColor("#4bae4f"));
            } else if (!eta.equals(sta)) {
                etaTV.setTextColor(Color.RED);
            }
        }

    }

    @Override
    public int getItemCount() {
        return journeysRailData.size();
    }

    private void imageSetter(ImageView statusPic,String etd,String std){
        if (etd == null){
            statusPic.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.pending));
        }else if (etd.equalsIgnoreCase("On time")){
            statusPic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.tick));
        }else if (!etd.equalsIgnoreCase(std)){
            statusPic.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.warning));
        }
    }

    public static class DisruptionsViewHolder extends RecyclerView.ViewHolder {

        private ItemLayoutDisruptionsBinding disruptionsBinding;

        public DisruptionsViewHolder(ItemLayoutDisruptionsBinding disruptionsBinding) {
            super(disruptionsBinding.getRoot());
            this.disruptionsBinding = disruptionsBinding;
        }

    }

}
