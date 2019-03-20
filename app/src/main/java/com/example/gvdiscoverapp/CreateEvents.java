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

public class CreateEvents extends AppCompatActivity {
    /** Object that represents eventName input */
    private EditText eventName;
    /** Object that represents location wrapper */
    private Spinner location;
    /** Object that represents startDate input */
    private EditText startDate;
    /** Object that represents startTime input */
    private EditText startTime;
    /** Object that represents endTime input */
    private EditText endTime;
    /** Object that represents description input */
    private EditText description;
    /** Object that represents submit input */
    private Button submit;
    /** Represents the connection to Model */
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_events);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Model model = Model.getInstance();

        eventName = (EditText)findViewById(R.id.eventName);
        //location = (Spinner)findViewById(R.id.location);
        startDate = (EditText)findViewById(R.id.startDate);
        startTime = (EditText)findViewById(R.id.startTime);
        endTime = (EditText)findViewById(R.id.endTime);
        description = (EditText)findViewById(R.id.description);
        submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = eventName.getText().toString();
                String event = "Kirkof" + "~~"
                        + startDate.getText().toString() + "~~"
                        + startTime.getText().toString() + "~~"
                        + endTime.getText().toString() + "~~"
                        + description.getText().toString() ;
                Model.addEvent(name, event);
                System.out.println("\nEvent Name: " + name);
                System.out.println("Event: " + event);
            }
        });
    }
}