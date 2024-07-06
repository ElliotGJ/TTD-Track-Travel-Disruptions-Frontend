package com.example.tracktraveldisruptionsapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public class Journey extends BaseObservable implements Parcelable {

    private Long userId;

    private Boolean notificationsEnabled;

    private String originCRS;

    private String destinationCRS;

    private Set<DayOfWeek> days;

    private String departureTime;



    public Journey(Long userId, Boolean notificationsEnabled, String originCRS, String destinationCRS, Set<DayOfWeek> days, String departureTime) {
        this.userId = userId;
        this.notificationsEnabled = notificationsEnabled;
        this.originCRS = originCRS;
        this.destinationCRS = destinationCRS;
        this.days = days;
        this.departureTime = departureTime;
    }

    public Journey() {
    }

    protected Journey(Parcel in) {
        byte tmpNotificationsEnabled = in.readByte();
        notificationsEnabled = tmpNotificationsEnabled == 0 ? null : tmpNotificationsEnabled == 1;
        originCRS = in.readString();
        destinationCRS = in.readString();
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

    public void setUserId(Long userId) {
        this.userId = userId;
        notifyPropertyChanged(BR.userId);

    }

    public void setNotificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
        notifyPropertyChanged(BR.notificationsEnabled);
    }

    public void setOriginCRS(String originCRS) {
        this.originCRS = originCRS;
        notifyPropertyChanged(BR.originCRS);
    }

    public void setDestinationCRS(String destinationCRS) {
        this.destinationCRS = destinationCRS;
        notifyPropertyChanged(BR.destinationCRS);
    }

    public void setDays(Set<DayOfWeek> days) {
        this.days = days;
        notifyPropertyChanged(BR.days);
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
        notifyPropertyChanged(BR.departureTime);
    }

    @Bindable
    public Boolean getNotificationsEnabled() {
        return notificationsEnabled;

    }

    @Bindable
    public String getOriginCRS() {
        return originCRS;
    }

    @Bindable
    public String getDestinationCRS() {
        return destinationCRS;
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
    public Long getUserId() {
        return userId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeByte((byte) (notificationsEnabled == null ? 0 : notificationsEnabled ? 1 : 2));
        dest.writeString(originCRS);
        dest.writeString(destinationCRS);
        dest.writeString(departureTime);
    }
}
