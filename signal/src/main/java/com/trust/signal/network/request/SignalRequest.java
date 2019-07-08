package com.trust.signal.network.request;

import com.google.gson.annotations.SerializedName;
import com.trust.signal.model.CurrentLocation;

public class SignalRequest {

    @SerializedName("company")
    private String company;
    @SerializedName("trust_id")
    private String trustId;
    @SerializedName("app_version")
    private String appVersion;
    @SerializedName("sdk_version")
    private String sdkVersion;
    @SerializedName("location")
    private CurrentLocation location;
    @SerializedName("signal")
    private String signal;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTrustId() {
        return trustId;
    }

    public void setTrustId(String trustId) {
        this.trustId = trustId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public CurrentLocation getLocation() {
        return location;
    }

    public void setLocation(CurrentLocation location) {
        this.location = location;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }
}
