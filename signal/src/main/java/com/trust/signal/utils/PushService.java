package com.trust.signal.utils;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;
import com.trust.signal.model.TrustCredentials;
import com.trust.signal.network.RestClient;
import com.trust.signal.network.RestClientToken;
import com.trust.signal.network.request.SignalRequest;
import com.trust.signal.network.response.SignalResponse;
import com.trust.signal.network.response.TokenResponse;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.trust.signal.utils.Constants.CREDENTIALS;
import static com.trust.signal.utils.Constants.TOKEN;
import static com.trust.signal.utils.Constants.TOKEN_ERROR;
import static com.trust.signal.utils.Constants.TOKEN_TYPE;
import static com.trust.signal.utils.Constants.TRUSTCREDENTIALS;

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

    private void apiCall(final SignalRequest request){
        String token = "Bearer " + Hawk.get(TOKEN);
        Call<SignalResponse> sendSignal = RestClient.getApi().sendSignal(token, request);
        sendSignal.enqueue(new Callback<SignalResponse>() {
            @Override
            public void onResponse(Call<SignalResponse> call, Response<SignalResponse> response) {
                if (response.isSuccessful()){
                    response.body();
                }else {
                    response.errorBody();
                    if (response.code() == TOKEN_ERROR){
                        getToken(request);
                    }
                }
            }

            @Override
            public void onFailure(Call<SignalResponse> call, Throwable t) {
                Log.e(TAG, "onFailure");
            }
        });
    }

    private void getToken(final SignalRequest request) {

        TrustCredentials trust = readFromAssets();
        if (trust != null){
            trust.setGrantType(CREDENTIALS);

            Call<TokenResponse> getToken = RestClientToken.getApi().getToken(trust.getClientId(), trust.getClientSecret(), trust.getGrantType());
            getToken.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                    if (response.isSuccessful()){
                        Hawk.put(TOKEN, response.body().getAccessToken());
                        Hawk.put(TOKEN_TYPE, response.body().getTokenType());

                        apiCall(request);
                    } else {
                        Log.e(TAG, "obtain token error");
                    }

                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {

                }
            });
        }


        //
    }

    private TrustCredentials readFromAssets(){

        String json = null;
        try {
            InputStream is = getAssets().open(TRUSTCREDENTIALS);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Type type = new TypeToken<TrustCredentials>(){}.getType();

        return new Gson().fromJson(json, type);
    }
}
