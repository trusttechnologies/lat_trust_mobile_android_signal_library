package com.trust.signalJava.network.response;

import com.google.gson.annotations.SerializedName;

public class SignalResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("signal")
    private SignalResponseDetail signal;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SignalResponseDetail getSignal() {
        return signal;
    }

    public void setSignal(SignalResponseDetail signal) {
        this.signal = signal;
    }
}
