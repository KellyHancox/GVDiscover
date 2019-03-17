package com.example.gvdiscoverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_monica);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /***********************************************************************************************
     * The follow methods are responsible for navigation. Some are currently commented out
     * because they do not have pages at the moment.
     * -Matt
     * ********************************************************************************************/
    public void toHub(View view) {
        startActivity(new Intent(HomeScreen.this, Hub.class));
    }
    public void toFindEvents(View view) {
        startActivity(new Intent(HomeScreen.this, FindEvents.class));
    }
    public void toMap(View view) {
        startActivity(new Intent(HomeScreen.this, MapPage.class));
    }
    public void toCreateEvents(View view) {
        startActivity(new Intent(HomeScreen.this, CreateEvents.class));
    }


}
