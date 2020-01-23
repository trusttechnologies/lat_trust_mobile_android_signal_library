package com.trust.signalJava.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import lat.trust.trusttrifles.TrustClient;
import lat.trust.trusttrifles.TrustListener;
import lat.trust.trusttrifles.model.Audit;
import lat.trust.trusttrifles.utilities.Permissions;
import lat.trust.trusttrifles.utilities.Constants;

@SuppressLint("StaticFieldLeak")
public class SignalApp extends Application {
    private static final String TAG = "SignalApp";
    private static Context mContext;
    private static Activity mActivity;
    private static SignalApp instance;

    public static void init(Activity activity, Context context){
        Log.e("SignalApp", "INIT - IN");
        mContext = context;
        mActivity = activity;
        instance = new SignalApp();

    }

    public static SignalApp getInstance() {
        return instance;
    }

    public SignalApp() {
        initHawk();
        initTrustID();
        Permissions.checkPermissionsDefault(mActivity, new TrustListener.Permissions() {
            @Override
            public void onPermissionSuccess() {
                getTrustId();
            }

            @Override
            public void onPermissionRevoke() {
                Log.i(TAG, "onPermissionRevoke: revoke permissions");
            }
        });
    }

    private void initTrustID() {
        TrustClient.init(mContext);
    }

    private void getTrustId() {
        try {
            TrustClient.getInstance().getTrifles(true, new TrustListener.OnResult<Audit>() {
                @Override
                public void onSuccess(int code, Audit data) {
                    Log.i(TAG, "onSuccess: " + data.getTrustid());
                    Hawk.get(Constants.TRUST_ID_AUTOMATIC);
                }

                @Override
                public void onError(int code) {
                    Log.i(TAG, "onError: " + code);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.i(TAG, "onFailure: " + t.getMessage());
                }

                @Override
                public void onPermissionRequired(ArrayList<String> permisos) {
                    for (String permiso : permisos) {
                        Log.i(TAG, "onPermissionRequired: " + permiso);
                    }
                }
            });
        } catch (Exception ex) {
            Log.i(TAG, "getTrustId: " + ex.getMessage());
        }

    }

    private void initHawk() {
        Hawk.init(mContext).build();
    }
}
