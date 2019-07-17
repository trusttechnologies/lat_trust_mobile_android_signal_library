package com.trust.signal.network.response;

import com.google.gson.annotations.SerializedName;
import com.trust.signal.network.request.SignalRequest;

import java.util.ArrayList;
import java.util.List;

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
