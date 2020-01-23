package com.trust.signalJava.utils;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.trust.signalJava.BuildConfig;
import com.trust.signalJava.model.CurrentLocation;
import com.trust.signalJava.network.request.SignalRequest;

import java.util.List;

import lat.trust.trusttrifles.utilities.Constants;

import static android.content.Context.LOCATION_SERVICE;

public class Signal implements LocationListener {
    private final String TAG = getClass().getSimpleName();
    private Context context;

    public Signal(Context context) {
        this.context = context;
    }

    public SignalRequest obtainRequest(){
        SignalRequest request = new SignalRequest();
        try{
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            request.setCompany(telephonyManager.getSimOperatorName());
            request.setAppVersion(BuildConfig.VERSION_NAME);
            request.setSdkVersion(String.valueOf(Build.VERSION.SDK_INT));
            request.setLocation(getLocation());
            request.setTrustId((String) Hawk.get(Constants.TRUST_ID_AUTOMATIC));
            request.setSignal(getSignalStregth(telephonyManager));

        }catch (Exception e){
            Log.i(TAG, "obtainRequest "+e.getMessage());
        }
        return request;
    }

    private void showLocation(Location location){
        Log.i(TAG, location.getLatitude() + " " + location.getLongitude());
    }

    private CurrentLocation getLocation(){

        CurrentLocation currentLocation = new CurrentLocation();
        Location location = null;
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        try {
            boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (!enabled) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);

            location = locationManager.getLastKnownLocation(provider);
            showLocation(location);

            currentLocation.setLatitude(String.valueOf(location.getLatitude()));
            currentLocation.setLongitude(String.valueOf(location.getLongitude()));

        }catch (SecurityException se){
            Log.e(TAG, se.getMessage());
        }

        return currentLocation;
    }

    private String getSignalStregth(TelephonyManager telephonyManager){
        String strength = "";
        try {
            List<CellInfo> cellInfos = telephonyManager.getAllCellInfo();   //This will give info of all sims present inside your mobile
            if(cellInfos!=null){
                for (int i = 0 ; i<cellInfos.size(); i++){
                    if (cellInfos.get(i).isRegistered()){
                        if(cellInfos.get(i) instanceof CellInfoWcdma){
                            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) telephonyManager.getAllCellInfo().get(0);
                            CellSignalStrengthWcdma cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();
                            strength = String.valueOf(cellSignalStrengthWcdma.getDbm());
                            Log.i(TAG, "CellSignalStrengthWcdma Signal: " + strength );
                        }else if(cellInfos.get(i) instanceof CellInfoGsm){
                            CellInfoGsm cellInfogsm = (CellInfoGsm) telephonyManager.getAllCellInfo().get(0);
                            CellSignalStrengthGsm cellSignalStrengthGsm = cellInfogsm.getCellSignalStrength();
                            strength = String.valueOf(cellSignalStrengthGsm.getDbm());
                            Log.i(TAG, "CellSignalStrengthGsm Signal: " + strength );
                        }else if(cellInfos.get(i) instanceof CellInfoLte){
                            CellInfoLte cellInfoLte = (CellInfoLte) telephonyManager.getAllCellInfo().get(0);
                            CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();
                            strength = String.valueOf(cellSignalStrengthLte.getDbm());
                            Log.i(TAG, "CellSignalStrengthLte Signal: " + strength );
                        }
                    }
                }
            }
        }catch (SecurityException se){
            Log.e(TAG, se.getMessage());
        }
        catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        return strength;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
