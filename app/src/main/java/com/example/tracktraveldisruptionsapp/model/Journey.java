package com.example.tracktraveldisruptionsapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.tracktraveldisruptionsapp.BR;
import com.google.gson.annotations.SerializedName;

import java.time.DayOfWeek;
import java.util.Set;

public class Journey extends BaseObservable implements Parcelable {

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

    protected Journey(Parcel in) {
        byte tmpNotificationsEnabled = in.readByte();
        notificationsEnabled = tmpNotificationsEnabled == 0 ? null : tmpNotificationsEnabled == 1;
        origin = in.readString();
        destination = in.readString();
        departureTime = in.readString();
    }

    public static final Creator<Journey> CREATOR = new Creator<Journey>() {
        @Override
        public Journey createFromParcel(Parcel in) {
            return new Journey(in);
        }

        @Override
        public Journey[] newArray(int size) {
            return new Journey[size];
        }
    };
@Bindable
    public Boolean getNotificationsEnabled() {
        return notificationsEnabled;
    }
    @Bindable
    public String getOrigin() {
        return origin;
    }
    @Bindable
    public String getDestination() {
        return destination;
    }
    @Bindable
    public Set<DayOfWeek> getDays() {
        return days;
    }
    @Bindable
    public String getDepartureTime() {
        return departureTime;
    }
    @Bindable
    public Set<JourneyLeg> getJourneyLegs() {
        return journeyLegs;
    }


    public void setNotificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
        notifyPropertyChanged(BR.notificationsEnabled);
    }

    public void setOrigin(String origin) {
        this.origin = origin;
        notifyPropertyChanged(BR.origin);
    }

    public void setDestination(String destination) {
        this.destination = destination;
        notifyPropertyChanged(BR.destination);
    }

    public void setDays(Set<DayOfWeek> days) {
        this.days = days;
        notifyPropertyChanged(BR.days);
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
        notifyPropertyChanged(BR.departureTime);
    }

    public void setJourneyLegs(Set<JourneyLeg> journeyLegs) {
        this.journeyLegs = journeyLegs;
        notifyPropertyChanged(BR.journeyLegs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeByte((byte) (notificationsEnabled == null ? 0 : notificationsEnabled ? 1 : 2));
        dest.writeString(origin);
        dest.writeString(destination);
        dest.writeString(departureTime);
    }
}

