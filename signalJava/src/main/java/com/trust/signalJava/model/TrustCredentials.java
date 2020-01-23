package com.trust.signalJava.model;

import com.google.gson.annotations.SerializedName;

public class TrustCredentials {
    @SerializedName("client_secret")
    private String clientSecret;
    @SerializedName("client_id")
    private String clientId;
    @SerializedName("grant_type")
    private String grantType;

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
