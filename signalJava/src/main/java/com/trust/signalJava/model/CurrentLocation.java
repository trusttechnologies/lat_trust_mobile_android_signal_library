package com.trust.signalJava.model;

import com.google.gson.annotations.SerializedName;

public class CurrentLocation {
    @SerializedName("lat")
    private String latitude;
    @SerializedName("lng")
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
