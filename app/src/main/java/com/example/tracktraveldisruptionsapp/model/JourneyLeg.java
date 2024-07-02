package com.example.tracktraveldisruptionsapp.model;

public class JourneyLeg {

    private String origin;

    private String originCRS;

    private String destination;

    private String destinationCRS;

    private int legOrder;

    private String transportProvider;

    public JourneyLeg(String origin, String originCRS, String destination, String destinationCRS, int legOrder, String transportProvider) {
        this.origin = origin;
        this.originCRS = originCRS;
        this.destination = destination;
        this.destinationCRS = destinationCRS;
        this.legOrder = legOrder;
        this.transportProvider = transportProvider;
    }

    public JourneyLeg() {
    }
}
