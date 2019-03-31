package com.example.gvdiscoverapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Wrapper;
/**
 * CreateEvents class corresponds with the CreateEvents page. It handles data collection from the
 * form and putting it into the Model.
 *
 * @author Matthew Shan, Jesse David
 * */

//TODO: Input testing
public class CreateEvents extends AppCompatActivity {
    /** Object that represents eventName input */
    private EditText eventName;
    /** Object that represents location wrapper */
    private Spinner location;
    /** Object that represents startDate input */
    private EditText startDate;
    /** Object that represents startTime input */
    private Spinner startTime;
    /** Object that represents endTime input */
    private Spinner endTime;
    /** Object that represents description input */
    private EditText description;
    /** Object that represents submit input */
    private Button submit;
    /** Represents the connection to Model */
    private Model model;

    /**
     * onCreate method creates the page and handles the submit button
     *
     * @param savedInstanceState see AppCompatActivity
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_events);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        model = Model.getInstance();

        eventName = (EditText)findViewById(R.id.eventName);
        location = (Spinner)findViewById(R.id.location);
        startDate = (EditText) findViewById(R.id.startDate);
        startTime = (Spinner) findViewById(R.id.startTime);
        endTime = (Spinner)findViewById(R.id.endTime);
        description = (EditText)findViewById(R.id.description);
        submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            /**
             * The onClick method for the submit button that handles the data and encodes it into
             * the Model.
             * */
            public void onClick(View v) {
                String event = eventName.getText().toString() + "~~"
                        + location.getSelectedItem().toString() + "~~"
                        + startDate.getText().toString() + "~~"
                        + startTime.getSelectedItem().toString() + "~~"
                        + endTime.getSelectedItem().toString() + "~~"
                        + description.getText().toString() ;
                Model.addEvent(event);
                System.out.println("Event: " + event);
            }
        });
    }
}
