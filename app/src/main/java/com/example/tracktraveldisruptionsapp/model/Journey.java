package com.example.tracktraveldisruptionsapp.model;

import com.google.gson.annotations.SerializedName;

import java.time.DayOfWeek;
import java.util.Set;

public class Journey {

    private Boolean notificationsEnabled;

    private String origin;

    private String destination;

    private Set<DayOfWeek> days;

    private String departureTime;

    private Set<JourneyLeg> journeyLegs;

    public Journey(Boolean notificationsEnabled, String origin, String destination, Set<DayOfWeek> days, String departureTime, Set<JourneyLeg> journeyLegs) {
        this.notificationsEnabled = notificationsEnabled;
        this.origin = origin;
        this.destination = destination;
        this.days = days;
        this.departureTime = departureTime;
        this.journeyLegs = journeyLegs;
    }

    public Journey() {
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

