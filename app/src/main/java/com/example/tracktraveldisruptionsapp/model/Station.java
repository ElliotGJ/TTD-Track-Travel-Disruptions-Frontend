package com.example.tracktraveldisruptionsapp.model;


import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

public class Station {

    private String station_name;

    private String crs;

    private Double latitude;

    private Double longitude;

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }
    @SerializedName("csr")
    public String getCrs() {
        return crs;
    }


    public void setCrs(String crs) {
        this.crs = crs;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @NotNull
    @Override
    public String toString(){
        return this.station_name;
    }
}
