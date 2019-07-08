package com.trust.signal.network;

import com.trust.signal.network.request.SignalRequest;
import com.trust.signal.network.response.SignalResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    @POST("signal")
    Call<SignalResponse> sendSignal (@Body SignalRequest request);

}
