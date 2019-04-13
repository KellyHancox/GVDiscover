package com.example.gvdiscoverapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import java.util.ArrayList;

/**
 * FindTheEvents class handles viewing all events from model
 * and user sign up.
 *
 * @author Kelly Hancox
 * */
public class FindTheEvents extends AppCompatActivity {
    /**
     *  full events list.
     */
    private final ArrayList<String> eventList = Model.getInstance().
            getEventsList();

    /**
     *  instance of this class for logging.
     *  */
    private static final String TAG = "FindTheEvents";

    /**
     * onCreate method creates the page and then calls on
     * the recycler view to begin on the page.
     *
     * @param savedInstanceState see AppCompatActivity
     * */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_the_events);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //logged for debugging
        Log.d(TAG, "onCreate: started.");

        initRecyclerView();
    }

    /**
     * Passes the eventsList to the recycler view and then sets
     * the adapter to a linear layout on this page.
     */
    private void initRecyclerView() {
        //logged for debugging
        Log.d(TAG, "initRecyclerView: initializing.");

        //begins the recyclerView with adapter from respective classes
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, eventList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
