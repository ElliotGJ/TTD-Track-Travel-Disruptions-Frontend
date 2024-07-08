package com.example.tracktraveldisruptionsapp.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import com.example.tracktraveldisruptionsapp.BR;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RailDataDTO extends BaseObservable {


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

    public RailDataDTO(String generatedAt, String departureStationCrs, String departureStationName, String destinationStationCrs, String destinationStationName,
                       String etd, String std, String platform, String eta, String sta, String cancelReason, String delayReason, String serviceID,
                       String affectedBy, Boolean filterLocationCancelled, Boolean cancelled) {
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
    }

    public RailDataDTO() {
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
                '}';
    }
}




