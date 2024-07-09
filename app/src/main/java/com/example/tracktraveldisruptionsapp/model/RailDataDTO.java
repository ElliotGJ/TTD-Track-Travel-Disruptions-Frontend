package com.example.tracktraveldisruptionsapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import com.example.tracktraveldisruptionsapp.BR;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RailDataDTO extends BaseObservable implements Parcelable {


    private String generatedAt;

    private String departureStationCrs;

    private String departureStationName;

    private String destinationStationCrs;

    private String destinationStationName;

    private String etd;

    private String std;

    private String platform;

    private String eta;

    private String sta;

    private String cancelReason;

    private String delayReason;

    private String serviceID;

    private String affectedBy;

    private Boolean filterLocationCancelled;

    private Boolean cancelled;

    private String operator;

    public RailDataDTO(String generatedAt, String departureStationCrs, String departureStationName, String destinationStationCrs, String destinationStationName,
                       String etd, String std, String platform, String eta, String sta, String cancelReason, String delayReason, String serviceID,
                       String affectedBy, Boolean filterLocationCancelled, Boolean cancelled, String operator) {
        this.generatedAt = generatedAt;
        this.departureStationCrs = departureStationCrs;
        this.departureStationName = departureStationName;
        this.destinationStationCrs = destinationStationCrs;
        this.destinationStationName = destinationStationName;
        this.etd = etd;
        this.std = std;
        this.platform = platform;
        this.eta = eta;
        this.sta = sta;
        this.cancelReason = cancelReason;
        this.delayReason = delayReason;
        this.serviceID = serviceID;
        this.affectedBy = affectedBy;
        this.filterLocationCancelled = filterLocationCancelled;
        this.cancelled = cancelled;
        this.operator = operator;
    }

    public RailDataDTO() {
    }

    protected RailDataDTO(Parcel in) {
        generatedAt = in.readString();
        departureStationCrs = in.readString();
        departureStationName = in.readString();
        destinationStationCrs = in.readString();
        destinationStationName = in.readString();
        etd = in.readString();
        std = in.readString();
        platform = in.readString();
        eta = in.readString();
        sta = in.readString();
        cancelReason = in.readString();
        delayReason = in.readString();
        serviceID = in.readString();
        affectedBy = in.readString();
        byte tmpFilterLocationCancelled = in.readByte();
        filterLocationCancelled = tmpFilterLocationCancelled == 0 ? null : tmpFilterLocationCancelled == 1;
        byte tmpCancelled = in.readByte();
        cancelled = tmpCancelled == 0 ? null : tmpCancelled == 1;
        operator = in.readString();
    }

    public static final Creator<RailDataDTO> CREATOR = new Creator<RailDataDTO>() {
        @Override
        public RailDataDTO createFromParcel(Parcel in) {
            return new RailDataDTO(in);
        }

        @Override
        public RailDataDTO[] newArray(int size) {
            return new RailDataDTO[size];
        }
    };

    @Bindable
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
        notifyPropertyChanged(BR.operator);
    }

    @Bindable
    public String getGeneratedAt() {
        return generatedAt;

    }

    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
        notifyPropertyChanged(BR.generatedAt);
    }

    @Bindable
    public String getDepartureStationCrs() {
        return departureStationCrs;
    }

    public void setDepartureStationCrs(String departureStationCrs) {
        this.departureStationCrs = departureStationCrs;
        notifyPropertyChanged(BR.departureStationCrs);
    }

    @Bindable
    public String getDepartureStationName() {
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
        notifyPropertyChanged(BR.departureStationName);
    }

    @Bindable
    public String getDestinationStationCrs() {
        return destinationStationCrs;
    }

    public void setDestinationStationCrs(String destinationStationCrs) {
        this.destinationStationCrs = destinationStationCrs;
        notifyPropertyChanged(BR.destinationStationCrs);
    }

    @Bindable
    public String getDestinationStationName() {
        return destinationStationName;
    }

    public void setDestinationStationName(String destinationStationName) {
        this.destinationStationName = destinationStationName;
        notifyPropertyChanged(BR.destinationStationName);
    }

    @Bindable
    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
        notifyPropertyChanged(BR.etd);
    }

    @Bindable
    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
        notifyPropertyChanged(BR.std);
    }

    @Bindable
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
        notifyPropertyChanged(BR.platform);
    }

    @Bindable
    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
        notifyPropertyChanged(BR.eta);
    }

    @Bindable
    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
        notifyPropertyChanged(BR.sta);
    }

    @Bindable
    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
        notifyPropertyChanged(BR.cancelReason);
    }

    @Bindable
    public String getDelayReason() {
        return delayReason;
    }

    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
        notifyPropertyChanged(BR.delayReason);
    }

    @Bindable
    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
        notifyPropertyChanged(BR.serviceID);
    }

    @Bindable
    public String getAffectedBy() {
        return affectedBy;
    }

    public void setAffectedBy(String affectedBy) {
        this.affectedBy = affectedBy;
        notifyPropertyChanged(BR.affectedBy);
    }

    @Bindable
    public Boolean getFilterLocationCancelled() {
        return filterLocationCancelled;
    }

    public void setFilterLocationCancelled(Boolean filterLocationCancelled) {
        this.filterLocationCancelled = filterLocationCancelled;
        notifyPropertyChanged(BR.filterLocationCancelled);
    }

    @Bindable
    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
        notifyPropertyChanged(BR.cancelled);
    }

    @Override
    public String toString() {
        return "RailDataDTO{" +
                "generatedAt='" + generatedAt + '\'' +
                ", departureStationCrs='" + departureStationCrs + '\'' +
                ", departureStationName='" + departureStationName + '\'' +
                ", destinationStationCrs='" + destinationStationCrs + '\'' +
                ", destinationStationName='" + destinationStationName + '\'' +
                ", etd='" + etd + '\'' +
                ", std='" + std + '\'' +
                ", platform='" + platform + '\'' +
                ", eta='" + eta + '\'' +
                ", sta='" + sta + '\'' +
                ", cancelReason='" + cancelReason + '\'' +
                ", delayReason='" + delayReason + '\'' +
                ", serviceID='" + serviceID + '\'' +
                ", affectedBy='" + affectedBy + '\'' +
                ", filterLocationCancelled=" + filterLocationCancelled +
                ", cancelled=" + cancelled +
                ", operator" + operator+
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(generatedAt);
        dest.writeString(departureStationCrs);
        dest.writeString(departureStationName);
        dest.writeString(destinationStationCrs);
        dest.writeString(destinationStationName);
        dest.writeString(etd);
        dest.writeString(std);
        dest.writeString(platform);
        dest.writeString(eta);
        dest.writeString(sta);
        dest.writeString(cancelReason);
        dest.writeString(delayReason);
        dest.writeString(serviceID);
        dest.writeString(affectedBy);
        dest.writeByte((byte) (filterLocationCancelled == null ? 0 : filterLocationCancelled ? 1 : 2));
        dest.writeByte((byte) (cancelled == null ? 0 : cancelled ? 1 : 2));
        dest.writeString(operator);
    }
}




