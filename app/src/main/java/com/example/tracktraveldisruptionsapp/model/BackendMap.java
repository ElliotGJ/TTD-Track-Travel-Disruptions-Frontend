package com.example.tracktraveldisruptionsapp.model;

import com.google.gson.annotations.SerializedName;

public class BackendMap {

    @SerializedName("journeyDTO")
    private Journey journeyDTO;

    @SerializedName("railDataDTO")
    private RailDataDTO railDataDTO;


    public BackendMap(Journey journeyDTO, RailDataDTO railDataDTO) {
        this.journeyDTO = journeyDTO;
        this.railDataDTO = railDataDTO;
    }

    public BackendMap() {
    }

    public Journey getJourneyDTO() {
        return journeyDTO;
    }

    public RailDataDTO getRailDataDTO() {
        return railDataDTO;
    }

    public void setRailDataDTO(RailDataDTO railDataDTO) {
        this.railDataDTO = railDataDTO;
    }

    @Override
    public String toString() {
        return "BackendMap{" +
                "journeyDTO=" + journeyDTO +
                ", railDataDTO=" + railDataDTO +
                '}';
    }
}
