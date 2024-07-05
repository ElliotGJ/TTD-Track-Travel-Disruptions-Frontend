package com.example.tracktraveldisruptionsapp.model;

import com.google.gson.annotations.SerializedName;

import java.time.DayOfWeek;
import java.util.Set;

public class Journey {
    @SerializedName("notificationsEnabled")
    private Boolean notificationsEnabled;
    @SerializedName("originCRS")
    private String origin;
    @SerializedName("destinationCRS")
    private String destination;
    @SerializedName("userId")
    private int userId;
    @SerializedName("days")
    private Set<DayOfWeek> days;
    @SerializedName("departureTime")
    private String departureTime;
    @SerializedName("journeyLegs")
    private Set<JourneyLeg> journeyLegs;

    public Journey(Boolean notificationsEnabled, String origin, String destination, int userId, Set<DayOfWeek> days, String departureTime, Set<JourneyLeg> journeyLegs) {
        this.notificationsEnabled = notificationsEnabled;
        this.origin = origin;
        this.destination = destination;
        this.userId = userId;
        this.days = days;
        this.departureTime = departureTime;
        this.journeyLegs = journeyLegs;
    }

    public Boolean getNotificationsEnabled() {
        return notificationsEnabled;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getUserId() {
        return userId;
    }

    public Set<DayOfWeek> getDays() {
        return days;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public Set<JourneyLeg> getJourneyLegs() {
        return journeyLegs;
    }
}

