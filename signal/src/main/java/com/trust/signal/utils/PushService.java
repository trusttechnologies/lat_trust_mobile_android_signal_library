package com.trust.signal.utils;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.trust.signal.network.RestClient;
import com.trust.signal.network.request.SignalRequest;
import com.trust.signal.network.response.SignalResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PushService extends FirebaseMessagingService {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "onMessageReceived: ");
        SignalRequest request = new Signal(this).obtainRequest();
        apiCall(request);
    }

    private void apiCall(SignalRequest request){
        Call<SignalResponse> sendSignal = RestClient.getApi().sendSignal(request);
        sendSignal.enqueue(new Callback<SignalResponse>() {
            @Override
            public void onResponse(Call<SignalResponse> call, Response<SignalResponse> response) {
                if (response.isSuccessful()){
                    response.body();
                }else {
                    response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<SignalResponse> call, Throwable t) {
                Log.e(TAG, "onFailure");
            }
        });
    }
}
