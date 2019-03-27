package com.example.gvdiscoverapp;

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
     * Value format: "location~~date~~startTime~~endTime~~desc */
    private static Map<String, String> events;


    /**
     * Private constructor creates new HashMap
     * */
    private Model() {
        events = new HashMap<String, String>();
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
     *  Value format: "location~~date~~startTime~~endTime~~desc"
     *
     *  @param eventName name of the event
     *  @param event in the format above
     * */
    public static void addEvent(String eventName, String event) {
        //TODO: String validation - https://www.geeksforgeeks.org/java-date-format-validation-using-regex/
        events.put(eventName, event);
    }


    /**
     * This method signs up the current user for a new event by key
     *
     * @param eventName is the name of the event, which is the key in the event HashMap.
     *
     * @throws NullPointerException when no user is found
     *
     * */
    public static void signUp(String eventName) throws NullPointerException{
        if (user == null)
            throw new NullPointerException("No user found");
        user.signUpEvent(eventName);
    }


    /***
     *  Gets the events stored in model.
     *  Value format: "location~~date~~startTime~~endTime~~desc
     *
     *  @return Hash map were the key is the name of the event.
     * */
    public static Map<String, String> getEvents() {
        return events;
    }


    /***
     *  Gets the events stored in model as an array list.
     *  Value format: "name~~location~~date~~startTime~~endTime~~desc
     *
     *  @return ArrayList<String> of the event.
     * */
    public static ArrayList<String> getEventsList() {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String,String> entry : events.entrySet()) {
            list.add(entry.getKey() + "~~" + entry.getValue());
            System.out.println(entry.getKey() + "~~" + entry.getValue());
        }
        return list;
    }


    /**
     * Returns the user that is currently in session
     *
     * @return GVUser that is currently logged in
     * */
    public static GVUser getUser() {
        return user;
    }

    //TODO: Save/Load Events
}
