package com.example.gvdiscoverapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model {
    /** An instance of the Singleton model */
    private static Model instance = null;
    /** Holds the current user in session */
    private static GVUser user;
    /** Holds all the events in the database.
     * Value format: "location~~date~~startTime~~endTime~~desc */
    public static Map<String, String> events;


    private Model() {
        events = new HashMap<String, String>();
    }

    public static Model getInstance() {
        if(instance == null) {
            instance = new Model();
        }
        return instance;
    }

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
        events.put(eventName, event);
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

    public static ArrayList<String> getEventsList() {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String,String> entry : events.entrySet()) {
            list.add(entry.getKey() + "~~" + entry.getValue());
            System.out.println(entry.getKey() + "~~" + entry.getValue());

        }
        return list;
    }

    public static void SignUp(String eventName) {
        user.addEvent(eventName);
    }

    public static GVUser getUser() {
        return user;
    }
}
