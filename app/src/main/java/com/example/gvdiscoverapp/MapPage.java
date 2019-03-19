package com.example.gvdiscoverapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MapPage extends AppCompatActivity {

    private static final String TAG = "MapPage";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private GoogleMap mMap;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private boolean mLocationPermissionGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        isServicesOK();
        getLocationPermission();
    }

    private void getLocationPermission(){
        String[] permissions = {COARSE_LOCATION, FINE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();

            }else{
                ActivityCompat.requestPermissions(this, permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this, permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: requesting permissions");
        mLocationPermissionGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){

                    //looping through the grant results
                    for(int i = 0; i > grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    //initialize the map
                    initMap();

//                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                            .findFragmentById(R.id.map);
//                    mapFragment.getMapAsync(this);

                }
            }
        }

    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapPage.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is ok and the user can make requests
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occurred but we can resolve it
            Log.d(TAG, "isServicesOK: an error occurred, but you can fix it");
            //Dailog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MapPage.this, available, ERROR_DIALOG_REQUEST);
            //dialog.show();
            return true;
        }
        else{
        //we can't make map requests
        return false;
        }
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;



                //Constrain the camera target to the Adelaide bounds.

                //sets zoom preferences
                mMap.setMinZoomPreference(16.0f);
                mMap.setMaxZoomPreference(17.0f);

                // Add a marker at Kirkhof and move the camera.
                LatLng grandValley = new LatLng(42.962739, -85.888673);
                mMap.addMarker(new MarkerOptions().position(grandValley).title("Marker at Kirkhof"));

                LatLngBounds ADELAIDE = new LatLngBounds(
                        //lower left
                        new LatLng(42.961,-85.897),
                        //upper right
                        new LatLng(42.965, -85.8815));

                mMap.setLatLngBoundsForCameraTarget(ADELAIDE);
            }
        });
    }

}



