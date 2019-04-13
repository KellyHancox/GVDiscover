package com.example.gvdiscoverapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

/**
 * Hub can view one user's events they signed up for.
 *
 * @author Kelly Hancox, Monica Klosin, Matthew Shan
 * */
public class Hub extends AppCompatActivity {

    /**
     * User is instance user that logs in.
     */
    private final GVUser user = Model.getInstance().getUser();

    /**
     * All of the events that the user signs up for.
     */
    private final ArrayList<String> signedUpEventsList = user.getEvents();


    /**
     * The files tag is hub.
     */
    private static final String TAG = "Hub";

    /**
     * onCreate method creates the FindEventsOld.
     *
     * @param savedInstanceState see AppCompatActivity
     * */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_the_events);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: started.");

        userInitRecyclerView();
    }

    /**
     * userInitRecyclerView starts the recycler view.
     */
    private void userInitRecyclerView() {
        Log.d(TAG, "userInitRecyclerView: initializing.");
        Log.d(TAG, signedUpEventsList.toString());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(
                this, signedUpEventsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}

