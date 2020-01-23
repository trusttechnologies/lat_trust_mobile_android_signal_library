package com.trust.signalJava.utils;

import android.net.Uri;

public class Constants {
    public static final Uri SIGNAL_URI = Uri.parse("com.trust.signal://auth.id");
    public static final int TOKEN_ERROR = 401;
    public static final String TRUSTCREDENTIALS = "trust-service.json";
    public static final String CREDENTIALS = "client_credentials";
    public static final String TOKEN = "token";
    public static final String TOKEN_TYPE = "token_type";
}
