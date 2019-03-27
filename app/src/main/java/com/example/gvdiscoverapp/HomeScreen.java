package com.example.gvdiscoverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
/**
 * HomeScreen is responsible for navigation to the other pages
 *
 * @author Monica Klosin, Matthew Shan
 * */
public class HomeScreen extends AppCompatActivity {

    /**
     * This simply creates the page
     *
     * @param savedInstanceState see AppCompatActivity
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * toHub method takes the user to the Hub Page
     *
     * @param view is the object that was clicked.
     * */
    public void toHub(View view) {
        startActivity(new Intent(HomeScreen.this, Hub.class));
    }

    /**
     * toFindEvents method takes the user to the Find Events page
     *
     * @param view is the object that was clicked.
     * */
    public void toFindEvents(View view) {
        startActivity(new Intent(HomeScreen.this, FindEventsOld.class));
    }

    /**
     * toMap method takes the user to the Map Page
     *
     * @param view is the object that was clicked.
     * */
    public void toMap(View view) {
        startActivity(new Intent(HomeScreen.this, MapPage.class));
    }

    /**
     * toCreateEvents method takes the user to the Create Event Page
     *
     * @param view is the object that was clicked.
     * */
    public void toCreateEvents(View view) {
        startActivity(new Intent(HomeScreen.this, CreateEvents.class));
    }


}
