package com.trust.signal.network;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static final String BASE_URL = "http://18.191.85.31:3000/api/v1/";
    private static final int CONNECT_TIMEOUT = 30 * 4;
    private static final int WRITE_TIMEOUT = 30 * 4;
    private static final int READ_TIMEOUT = 30 * 4;
    private static API api;

    public RestClient() {
    }

    public static API getApi() {
        return api;
    }

    static {
        setup();
    }

    private static void setup() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                //.addInterceptor(new HeaderInterceptor())
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .client(okHttpClient)
                .build();

        api = retrofit.create(API.class);
    }

}
