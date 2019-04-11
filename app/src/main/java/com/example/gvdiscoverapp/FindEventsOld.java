package com.example.gvdiscoverapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * FindEventsOld class corresponds with the FindEventsOld page.
 * It handles viewing all events from model
 * and user sign up.
 *
 * @author Kelly Hancox, Monica Klosin
 */
public class FindEventsOld extends AppCompatActivity {

    /**
     * onCreate method creates the FindEventsOld.
     *
     * @param savedInstanceState see AppCompatActivity
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_events_old);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //pull from model
    //

}
