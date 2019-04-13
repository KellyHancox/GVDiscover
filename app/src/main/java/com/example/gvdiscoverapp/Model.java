package com.example.gvdiscoverapp;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Model is a singleton class that holds information such as the current
 * user and events.
 *
 * @author Matthew Shan
 * */
public final class Model {
    /** An instance of the Singleton model. */
    private static Model instance = null;
    /** Holds the current user in session. */
    private GVUser user;
    /** Holds all the events in the database.
     * Value format: "name~~location~~date~~startTime~~endTime~~desc */
    private ArrayList<String> events;


    /**
     * Private constructor creates new HashMap.
     * */
    private Model() {
        events = new ArrayList<String>();
    }

    /**
     * Singleton method that creates and returns a new instance if there
     * is no instance otherwise just returns the old/current instance.
     *
     * @return The singleton instance of Model
     * */
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    /**
     *  Adds an event to the model.
     *  Value format: "names~~location~~date~~startTime~~endTime~~desc"
     *
     *  @param event in the format above
     * */
    public void addEvent(final String event) {
        events.add(event);
    }


    /**
     * This method signs up the current user for a new event by key.
     *
     * @param event is the event string
     *
     * @throws NoUserFoundException when no user is found
     *
     * */
    public void signUp(final String event) throws NoUserFoundException {
        if (user == null) {
            throw new NoUserFoundException("No user found");
        }
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
     * Returns the user that is currently in session.
     *
     * @return GVUser that is currently logged in
     * */
    public GVUser getUser() {
        return user;
    }

    //Save everytime event create or sign up

    /**
     * Saves events and the data of the current user.
     * Saves only upon event creation or signup
     *
     * @throws IOException when there is an error with saving
     * @throws NoUserFoundException when the user does not exist.
     * This should never be the case.
     *
     * @param context provides the context for the save.
     * */
    public void save(final Context context) throws IOException,
            NoUserFoundException {
        printOut("Attempting to save");
        if (user == null) {
            throw new NoUserFoundException();
        }

        //Get the app file directory
        File directory = context.getFilesDir();
        File eventPath = new File(directory, "events.ser");
        Boolean newEvent = eventPath.createNewFile();
        File userPath = new File(directory, user.getEmail() + ".ser");
        Boolean newUser = userPath.createNewFile();

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

        printOut("Saving done for: " + this.getUser().getEmail());
    }

    /**
     * Loads events and the data of the current user. Loads only upon login
     *
     * @throws IOException when there is an error with saving
     * @throws ClassNotFoundException when loaded file does not
     * have the correct class
     * @throws NumberFormatException when the number is formatted incorrectly
     *
     * @param context provides the context for the load
     * @param email is the email of the user
     * */
    public void load(final Context context, final String email)
            throws ClassNotFoundException, IOException, NumberFormatException {
        printOut("Attempting to load");
        File directory = context.getFilesDir();
        File eventPath = new File(directory, "events.ser");
        File userPath = new File(directory, email + ".ser");

        if (eventPath.exists()) {
            FileInputStream fileEvents = new FileInputStream(eventPath);
            ObjectInputStream streamEvents = new ObjectInputStream(fileEvents);
            events = (ArrayList<String>) streamEvents.readObject();
            streamEvents.close();
            printOut("Event file found");
        } else {
            events = new ArrayList<String>();
            printOut("Event file not found");
        }

        //Then load the user by email
        if (userPath.exists()) {
            FileInputStream fileUser = new FileInputStream(userPath);
            ObjectInputStream streamUser = new ObjectInputStream(fileUser);
            user = (GVUser) streamUser.readObject();
            streamUser.close();
            printOut("User file found");
        } else {
            printOut("No user file found");
            user = new GVUser(email);
            printOut("New user created");
        }

        printOut("Loading done. Current User: " + this.getUser().getEmail());
    }

    /**
     * This method deletes all the saved files.
     *
     * @param context of the environment
     *
     * @return a string?
     */
    public String deleteAll(final Context context) {
        File folder = context.getFilesDir();
        File[] serList = folder.listFiles();

        for (File f: serList) {
            if (f.getName().endsWith(".ser")) {
                boolean delete = f.delete();
            }
        }
        try {
            this.load(context, this.getUser().getEmail());
            return "Files deleted";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Files not deleted...";
    }

    /**
     * This method logs the user out.
     */
    public void logOut() {
        this.user = null;
    }

    /***Prints out a message to the run terminal.
     *
     * @param s prints out s?
     */
    private void printOut(final String s) {
        System.out.println("\n==================" + s + "==================");
    }
}
