package com.example.gvdiscoverapp;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Model is a singleton class that holds information such as the current user and events
 *
 * @author Matthew Shan
 * */
public class Model {
    /** An instance of the Singleton model */
    private static Model instance = null;
    /** Holds the current user in session */
    private GVUser user;
    /** Holds all the events in the database.
     * Value format: "name~~location~~date~~startTime~~endTime~~desc */
    private ArrayList<String> events;


    /**
     * Private constructor creates new HashMap
     * */
    private Model() {
        events = new ArrayList<String>();
    }

    /**
     * Singleton method that creates and returns a new instance if there is no instance otherwise
     * just returns the old/current instance.
     *
     * @return The singleton instance of Model
     * */
    public static Model getInstance() {
        if(instance == null) {
            instance = new Model();
        }
        return instance;
    }

    /***
     *  Adds an event to the model.
     *  Value format: "names~~location~~date~~startTime~~endTime~~desc"
     *
     *  @param event in the format above
     * */
    public void addEvent(String event) {
        events.add(event);
    }


    /**
     * This method signs up the current user for a new event by key
     *
     * @param event is the event string
     *
     * @throws NoUserFoundException when no user is found
     *
     * */
    public void signUp(String event) throws NoUserFoundException {
        if (user == null)
            throw new NoUserFoundException("No user found");
        user.signUpEvent(event);
    }

    /***
     *  Gets the events stored in model as an array list.
     *  Value format: "name~~location~~date~~startTime~~endTime~~desc
     *
     *  @return ArrayList<String> of the event.
     * */
    public ArrayList<String> getEventsList() {
        return events;
    }


    /**
     * Returns the user that is currently in session
     *
     * @return GVUser that is currently logged in
     * */
    public GVUser getUser() {
        return user;
    }

    //Save everytime event create or sign up

    /**
     * Saves events and the data of the current user. Saves only upon event creation or signup
     *
     * @throws IOException when there is an error with saving
     * @throws NoUserFoundException when the user does not exist. This should never be the case.
     * */
    public void save(Context context) throws IOException, NoUserFoundException {
        printOut("Attempting to save");
        if(user == null) {
            throw new NoUserFoundException();
        }

        //Get the app file directory
        File directory = context.getFilesDir();
        File eventPath = new File(directory,"events.ser");
        eventPath.createNewFile();
        File userPath = new File(directory, user.getEmail());
        userPath.createNewFile();

        //First save all the events
        FileOutputStream fileEvents = new FileOutputStream(eventPath);
        ObjectOutputStream streamEvents = new ObjectOutputStream(fileEvents);
        streamEvents.writeObject(events);
        streamEvents.close();
        printOut("Events saved...");

        //Then save the user by their email
        FileOutputStream fileUser = new FileOutputStream(userPath);
        ObjectOutputStream streamUser = new ObjectOutputStream(fileUser);
        streamUser.writeObject(user);
        streamUser.close();
        printOut("User saved...");

        printOut("Saving done.");
    }

    /**
     * Loads events and the data of the current user. Loads only upon login
     *
     * @throws IOException when there is an error with saving
     * @throws ClassNotFoundException when loaded file does not have the correct class
     * */
    public void load(Context context, String email) throws ClassNotFoundException, IOException, NumberFormatException{
        printOut("Attempting to load");
        File directory = null;
        File eventPath = null;
        File userPath = null;

        //Get the app file directory
        try {
            directory = context.getFilesDir();
            eventPath = new File(directory, "events.ser");
            eventPath.createNewFile();
            userPath = new File(directory, email);
            userPath.createNewFile();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        if(eventPath != null && eventPath.exists()) {
            FileInputStream fileEvents = new FileInputStream(eventPath);
            ObjectInputStream streamEvents = new ObjectInputStream(fileEvents);
            events = (ArrayList<String>) streamEvents.readObject();
            streamEvents.close();
            printOut("Event file found");
        }
        else {
            printOut("Event file not found");
            //throw new NumberFormatException();
        }

        //Then load the user by email
        if(userPath != null && userPath.exists()) {
            FileInputStream fileUser = new FileInputStream(userPath);
            ObjectInputStream streamUser = new ObjectInputStream(fileUser);
            user = (GVUser) streamUser.readObject();
            streamUser.close();
            printOut("User file found");
        }
        else {
            printOut("No user file found");
            user = new GVUser(email);
            printOut("New user created");
        }

        printOut("Loading done.");
    }
    /***Prints out a message to the run terminal*/
    private void printOut(String s) {
        System.out.println("\n=====================\n" + s +"\n=====================");
    }
}
