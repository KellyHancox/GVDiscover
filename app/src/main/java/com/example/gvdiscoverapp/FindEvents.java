package com.example.gvdiscoverapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
/**
 * FindEvents class corresponds with the FindEvents page. It handles viewing all events from model
 * and user sign up
 *
 * @author Kelly Hancox, Monica Klosin
 * */
public class FindEvents extends AppCompatActivity {

    /**
     * onCreate method creates the FindEvents
     *
     * @param savedInstanceState see AppCompatActivity
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_events);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //pull from model
    //

}