package com.example.tracktraveldisruptionsapp.model;

import com.google.gson.annotations.SerializedName;

public class JourneyLeg {
    @SerializedName("origin")
    private String origin;
    @SerializedName("originCRS")
    private String originCRS;
    @SerializedName("destination")
    private String destination;
    @SerializedName("destinationCRS")
    private String destinationCRS;
    @SerializedName("legOrder")
    private int legOrder;
    @SerializedName("transportProvider")
    private String transportProvider;

    public JourneyLeg(String origin, String originCRS, String destination, String destinationCRS, int legOrder, String transportProvider) {
        this.origin = origin;
        this.originCRS = originCRS;
        this.destination = destination;
        this.destinationCRS = destinationCRS;
        this.legOrder = legOrder;
        this.transportProvider = transportProvider;
    }

    // Getters and Setters
    public String getOrigin() {
        return origin;
    }

    public String getOriginCRS() {
        return originCRS;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestinationCRS() {
        return destinationCRS;
    }

    public int getLegOrder() {
        return legOrder;
    }

    public String getTransportProvider() {
        return transportProvider;
    }
}
