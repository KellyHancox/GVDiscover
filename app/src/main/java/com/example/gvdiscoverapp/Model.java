package com.example.gvdiscoverapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Model is a singleton class that holds information such as the current user and events
 *
 * @author Matthew Shan
 * */
public class Model {
    /** An instance of the Singleton model */
    private static Model instance = null;
    /** Holds the current user in session */
    private static GVUser user;
    /** Holds all the events in the database.
     * Value format: "name~~location~~date~~startTime~~endTime~~desc */
    private static ArrayList<String> events;


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

    /**
     * Sets this session's user to this email.
     *
     * @param email user email
     * */
    public static void newUser(String email) {
        user = new GVUser(email);
    }


    /***
     *  Adds an event to the model.
     *  Value format: "names~~location~~date~~startTime~~endTime~~desc"
     *
     *  @param event in the format above
     * */
    public static void addEvent(String event) {
        //TODO: String validation - https://www.geeksforgeeks.org/java-date-format-validation-using-regex/
        events.add(event);
    }


    /**
     * This method signs up the current user for a new event by key
     *
     * @param event is the event string
     *
     * @throws NullPointerException when no user is found
     *
     * */
    public static void signUp(String event) throws NullPointerException{
        if (user == null)
            throw new NullPointerException("No user found");
        user.signUpEvent(event);
    }

    /***
     *  Gets the events stored in model as an array list.
     *  Value format: "name~~location~~date~~startTime~~endTime~~desc
     *
     *  @return ArrayList<String> of the event.
     * */
    public static ArrayList<String> getEventsList() {
        return events;
    }


    /**
     * Returns the user that is currently in session
     *
     * @return GVUser that is currently logged in
     * */
    public static GVUser getUser() {
        return user;
    }

    //Save everytime event create or sign up

    /**
     * Saves events and the data of the current user
     *
     * @throws IOException when there is an error with saving
     * */
    public void saveEvents() throws IOException {
        //First save all the events
        FileOutputStream fileEvents = new FileOutputStream("events.ser");
        ObjectOutputStream streamEvents = new ObjectOutputStream(fileEvents);
        streamEvents.writeObject(events);
        streamEvents.close();
        //Then save the user by their email
        FileOutputStream fileUser = new FileOutputStream("./users/" + user.getEmail() + ".ser");
        ObjectOutputStream streamUser = new ObjectOutputStream(fileUser);
        streamUser.writeObject(user);
        streamUser.close();
    }
    /**
     * Saves events and the data of the current user
     *
     * @throws IOException when there is an error with saving
     * @throws ClassNotFoundException when loaded file does not have the correct class
     * */
    public void loadEvents() throws ClassNotFoundException, IOException{
        //First retrieve the events
        FileInputStream fileEvents = new FileInputStream ("events.ser");
        ObjectInputStream streamEvents = new ObjectInputStream(fileEvents);
        events = (ArrayList<String>)streamEvents.readObject();
        streamEvents.close();
        //Then load the user by email
        FileInputStream fileUser = new FileInputStream ("users/" + user.getEmail() + ".ser");
        ObjectInputStream streamUser = new ObjectInputStream(fileUser);
        user = (GVUser) streamUser.readObject();
        streamUser.close();
    }
}
