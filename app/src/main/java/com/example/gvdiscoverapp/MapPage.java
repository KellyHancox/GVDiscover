package com.example.gvdiscoverapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Class responsible for the Map Page.
 *
 * @author Kelly Hancox
 * */
public class MapPage extends AppCompatActivity {

    /**
     *  creates reference for this page.
     */
    private static final String TAG = "MapPage";

    /**
     *  map object.
     */
    private GoogleMap mMap;

    /**
     *  fine location, most specific.
     */
    private static final String FINE_LOCATION =
            Manifest.permission.ACCESS_FINE_LOCATION;

    /**
     *  coarse location, less specific.
     */
    private static final String COARSE_LOCATION =
            Manifest.permission.ACCESS_COARSE_LOCATION;


    /**
     *  boolean for location permissions.
     */
    private boolean mLocationPermissionGranted = false;


    /**
     *  permissions number.
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    /**
     * Checks on services and then the calls for
     * location permissions to initialize the map.
     *
     * @param savedInstanceState see AppCompatActivity
     * */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        isServicesOK();
        getLocationPermission();
    }

    /**
     * Checks that we can get the location permission.
     *
     * */
    private void getLocationPermission() {
        String[] permissions = {COARSE_LOCATION, FINE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();

            } else {
                ActivityCompat.requestPermissions(this, permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * Required overridden method that requests
     * the permission for locations.
     *
     * @param requestCode the code given to the device
     * @param permissions type of permissions
     * @param grantResults array of grant results
     * */
    @Override
    public void onRequestPermissionsResult(final int requestCode,
                                           @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: requesting permissions");
        mLocationPermissionGranted = false;

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                //looping through the grant results
                for (int i = 0; i > grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                }
                mLocationPermissionGranted = true;
                //initialize the map
                initMap();

            }
        }

    }

    /**
     * toCreateEvents method takes the user to the Create Event Page.
     *
     * @param view is the object that was clicked.
     * */
    public void toCreateEvents(final View view) {
        startActivity(new Intent(MapPage.this, CreateEvents.class));
    }

    /**
     * isServicesOk checks if Google Play Services works on device.
     *
     * */
    private void isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(MapPage.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is ok and the user can make requests
        } else if (GoogleApiAvailability.getInstance()
                .isUserResolvableError(available)) {
            //an error occurred but we can resolve it
            Log.d(TAG, "isServicesOK: an error occurred, but you can fix it");
        } else {
        //we can't make map requests
        }
    }

    /**
     * initMap begins map at GVSU parameters with only
     * 2 levels of zoom and adds markers at available locations.
     *
     * */
    private void initMap() {
        Log.d(TAG, "initMap: initializing map");

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(final GoogleMap googleMap) {
                mMap = googleMap;

                //sets zoom preferences
                mMap.setMinZoomPreference(16.0f);
                mMap.setMaxZoomPreference(17.0f);

                // Add a marker at Kirkhof and move the camera.
                LatLng kirkhof = new LatLng(42.962739, -85.888673);
                mMap.addMarker(new MarkerOptions().position(kirkhof).
                        title("You can find events at Kirkhof"));

                // Add a marker at Kirkhof and move the camera.
                LatLng recCenter = new LatLng(42.966857, -85.889745);
                mMap.addMarker(new MarkerOptions().position(recCenter).
                        title("You can find events at the Rec Center"));

                // Add a marker at Kirkhof and move the camera.
                LatLng mak = new LatLng(42.966833, -85.886892);
                mMap.addMarker(new MarkerOptions().position(mak).
                        title("You can find events at Mackinac"));

                //creates new bounds
                LatLngBounds latLngBounds = new LatLngBounds(
                        //lower left
                        new LatLng(42.961, -85.897),
                        //upper right
                        new LatLng(42.975, -85.8815));

                mMap.setLatLngBoundsForCameraTarget(latLngBounds);
            }
        });
    }

}



