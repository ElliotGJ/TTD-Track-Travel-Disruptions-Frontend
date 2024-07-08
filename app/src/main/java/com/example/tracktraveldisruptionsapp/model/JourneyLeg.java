package com.example.tracktraveldisruptionsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class JourneyLeg implements Parcelable {

    private String origin;
    private String originCRS;
    private String destination;
    private String destinationCRS;

    public JourneyLeg(String origin, String originCRS, String destination, String destinationCRS) {
        this.origin = origin;
        this.originCRS = originCRS;
        this.destination = destination;
        this.destinationCRS = destinationCRS;
    }

    protected JourneyLeg(Parcel in) {
        origin = in.readString();
        originCRS = in.readString();
        destination = in.readString();
        destinationCRS = in.readString();
    }

    public static final Creator<JourneyLeg> CREATOR = new Creator<JourneyLeg>() {
        @Override
        public JourneyLeg createFromParcel(Parcel in) {
            return new JourneyLeg(in);
        }

        @Override
        public JourneyLeg[] newArray(int size) {
            return new JourneyLeg[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(origin);
        dest.writeString(originCRS);
        dest.writeString(destination);
        dest.writeString(destinationCRS);
    }

    @Override
    public String toString() {
        return "JourneyLeg{" +
                "origin='" + origin + '\'' +
                ", originCRS='" + originCRS + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationCRS='" + destinationCRS + '\'' +
                '}';
    }
}
