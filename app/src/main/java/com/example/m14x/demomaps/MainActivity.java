package com.example.m14x.demomaps;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;
    private String provider;
    private int permissionCheck;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionCheck = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_ASK_PERMISSIONS);
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            return;

        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            Toast.makeText(this, "Got your Location", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Don't know where you are", Toast.LENGTH_SHORT).show();
        }
    }

   /* @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        permissionCheck = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(provider, 300, 1, this);
        }else {
            requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_ASK_PERMISSIONS);
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onPause() {
        super.onPause();
        permissionCheck = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(this);
        }
        else {
            requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_ASK_PERMISSIONS);
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onLocationChanged(Location location) {

        Double longitude = location.getLongitude();
        Double latitude = location.getLatitude();

        Log.i("Test",longitude.toString()+"-"+latitude.toString());

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
