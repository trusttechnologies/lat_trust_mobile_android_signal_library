package com.trust.signalJava.network.response;

import com.google.gson.annotations.SerializedName;

public class SignalResponseDetail {

    @SerializedName("company")
    private String company;
    @SerializedName("trust_id")
    private String trustId;
    @SerializedName("app_version")
    private String appVersion;
    @SerializedName("sdk_version")
    private String sdkVersion;
    @SerializedName("location")
    private String[] location;
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

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }
}
