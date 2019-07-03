package com.trust.signal.utils;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import static android.content.Context.LOCATION_SERVICE;

public class GPS implements LocationListener {
    private final String TAG = getClass().getSimpleName();
    private final LocationManager locationManager;
    private Location location = null;

    public GPS(Context context) {
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        }
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);
        try {
            location = locationManager.getLastKnownLocation(provider);
            showLocation(location);
            //locationManager.requestLocationUpdates(provider, 400, 1, this);
        }catch (SecurityException se){
            Log.e(TAG, se.getMessage());
        }
    }

    private void showLocation(Location location){
        Log.i(TAG, location.getLatitude() + " " + location.getLongitude());
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
