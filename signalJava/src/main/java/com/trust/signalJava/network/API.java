package com.trust.signalJava.network;

import com.trust.signalJava.network.request.SignalRequest;
import com.trust.signalJava.network.response.SignalResponse;
import com.trust.signalJava.network.response.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    @POST("signal")
    Call<SignalResponse> sendSignal (@Header ("Authorization") String token, @Body SignalRequest request);

    @FormUrlEncoded
    @POST("oauth/token")
    Call<TokenResponse> getToken (
            @Field("client_id") String id,
            @Field("client_secret") String secret,
            @Field("grant_type") String type
    );

}
