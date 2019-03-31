package com.example.gvdiscoverapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  GVUser is the class the represents a user. This holds information such as email and events.
 *
 * @author Matthew Shan
 * */
public class GVUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /** A string for the user email. */
    private String email;
    /** An ArrayList of string that hold event keys. The keys reference the hash map in Model */
    private ArrayList<String> events;

    /**
     * Creates a user with a given email
     *
     * @param email The users given email in login
     */
    public GVUser(String email) {
        this.email = email;
    }

    /**
     * Signs the user up for the given event name
     *
     * @param key 'key' is the name of the event in the HashMap
     * */
    public void signUpEvent(String key) {
        events.add(key);
    }

    /**
     * Returns the user's signed up events
     *
     * @return ArrayList<String> that contains the users signed up events
     * */
    public ArrayList<String> getEvents() {
        return events;
    }

    /**
     * Returns the user's email
     *
     * @returns User email
     */
    public String getEmail() {
        return email;
    }
}
