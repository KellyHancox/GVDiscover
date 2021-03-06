package com.example.gvdiscoverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * CreateEvents class corresponds with the CreateEvents page.
 * It handles data collection from the
 * form and putting it into the Model.
 *
 * @author Matthew Shan, Jesse David
 */

public class CreateEvents extends AppCompatActivity {
    /**
     * Object that represents eventName input.
     */
    private EditText eventName;
    /**
     * Object that represents location wrapper.
     */
    private Spinner location;
    /**
     * Object that represents startDate input.
     */
    private String startDate;
    /**
     * Object that represents the day input.
     */
    private EditText day;
    /**
     * Object that represents the month input.
     */
    private EditText month;
    /**
     * Object that represents the year input.
     */
    private EditText year;
    /**
     * Object that represents startTime input.
     */
    private Spinner startTime;
    /**
     * Object that represents endTime input.
     */
    private Spinner endTime;
    /**
     * Object that represents description input.
     */
    private EditText description;

    /**
     * onCreate method creates the page and handles the submit button.
     *
     * @param savedInstanceState see AppCompatActivity
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_events);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eventName = findViewById(R.id.eventName);
        location = findViewById(R.id.location);
        day = findViewById(R.id.day);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        description = findViewById(R.id.description);
        /*
          Object that represents submit input.
         */
        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            /**
             * The onClick method for the submit button that handles the data
             * and encodes it into
             * the Model.
             * */
            public void onClick(final View v) {
                //every field must be filled in
                if (month.getText().length() != 0
                        && day.getText().length() != 0
                        && year.getText().length() != 0
                        && eventName.getText().length() != 0
                        && description.getText().length() != 0) {
                    startDate = month.getText().toString() + "/"
                            + day.getText().toString() + "/"
                            + year.getText().toString();
                    String event = eventName.getText().toString() + "~~"
                            + location.getSelectedItem().toString() + "~~"
                            + startDate + "~~"
                            + startTime.getSelectedItem().toString() + "~~"
                            + endTime.getSelectedItem().toString() + "~~"
                            + description.getText().toString();
                    Model.getInstance().addEvent(event);
                    //go back to home screen
                    startActivity(new Intent(CreateEvents.this,
                            HomeScreen.class));
                } else {
                    Toast.makeText(CreateEvents.this,
                            "Event not created. "
                            + "All Fields required.",
                            Toast.LENGTH_SHORT).show();
                }
                try {
                    File directory = CreateEvents.this.getFilesDir();
                    Model.getInstance().save(directory);
                } catch (NoUserFoundException e) {
                    Toast.makeText(getApplicationContext(), "No user found...",
                            Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),
                            "IOException has occurred...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    Toast.makeText(getApplicationContext(),
                            "NullPointerException",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Unknown exception has occurred...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                //System.out.println("Event: " + event);
            }
        });
    }
}

