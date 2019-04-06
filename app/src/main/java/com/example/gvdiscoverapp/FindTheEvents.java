package com.example.gvdiscoverapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * FindEventsOld class corresponds with the FindEventsOld page. It handles viewing all events from model
 * and user sign up
 *
 * @author Kelly Hancox, Monica Klosin
 * */
public class FindTheEvents extends AppCompatActivity {

    private Model model =  Model.getInstance();

    private GVUser user = Model.getUser();

    private ArrayList<String> eventList = Model.getEventsList();
    private ArrayList<String> signedUpEventsList = user.getEvents();


    private static final String TAG = "FindTheEvents";

    /**
     * onCreate method creates the FindEventsOld
     *
     * @param savedInstanceState see AppCompatActivity
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_the_events);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: started.");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: initializing.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, eventList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
