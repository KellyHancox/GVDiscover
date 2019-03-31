package com.example.gvdiscoverapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Hub is responsible for navigation to the other pages
 *
 * @author Kelly Hancox, Monica Klosin, Matthew Shan
 * */
public class Hub extends AppCompatActivity {
    private Model model;

    /**
     * This simply creates the page
     *
     * @param savedInstanceState see AppCompatActivity
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


}

