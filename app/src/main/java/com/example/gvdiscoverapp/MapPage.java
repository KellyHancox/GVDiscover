package com.example.gvdiscoverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

        initMap();
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
                LatLngBounds GVSU = new LatLngBounds(
                        //lower left
                        new LatLng(42.961, -85.897),
                        //upper right
                        new LatLng(42.975, -85.8815));
                mMap.setLatLngBoundsForCameraTarget(GVSU);

            }
        });
    }

}
