package com.example.gvdiscoverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

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
        startActivity(new Intent(HomeScreen.this, FindTheEvents.class));
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

    /**
     * deleteFiles deletes all the files in the
     *
     * @param view is the object that was clicked.
     * */
    public void deleteFiles(View view){
        String msg = Model.getInstance().deleteAll(this);
        Toast.makeText(getApplicationContext(),
                msg,
                Toast.LENGTH_LONG).show();
    }

    /**
     * deleteFiles deletes all the files in the
     *
     * @param view is the object that was clicked.
     * */
    public void logOut(View view) {
        try {
            Model.getInstance().save(this);
            Model.getInstance().logOut();

        } catch (NoUserFoundException e) {
            Toast.makeText(getApplicationContext(), "No user found...",
                    Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "IOException has occured...",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "NullPointerException",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Unknown exception has occured...",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}

