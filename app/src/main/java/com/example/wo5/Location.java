package com.example.wo5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Location extends AppCompatActivity {

    String LOCATION_PROVIDER = LocationManager.NETWORK_PROVIDER;
    LocationManager mlocationManager;
    LocationListener mlocationListener;
    long MIN_TIME = 5000;
    float MIN_DISTANCE = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        getCurrentLocation();


    }

    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        mlocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mlocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {
                Log.d("ClimateControl", "onLocationChanged() has been called");
                String longitude = String.valueOf(location.getLongitude());
                String latitude = String.valueOf(location.getLatitude());
                String altitude = String.valueOf(location.getAltitude());
                Log.d("Location", location.toString());
                Log.d("Position", "Latitude: " + latitude + " Longitude: " + longitude + " Altitude: " + altitude);
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
        };
        mlocationManager.requestLocationUpdates(LOCATION_PROVIDER, MIN_TIME, MIN_DISTANCE, mlocationListener);

    }
}
