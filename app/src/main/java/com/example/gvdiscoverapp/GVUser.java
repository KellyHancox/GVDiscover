package com.example.gvdiscoverapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  GVUser is the class the represents a user.
 *  This holds information such as email and events.
 *
 * @author Matthew Shan
 * */
public class GVUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /** A string for the user email. */
    private final String email;
    /** An ArrayList of string that hold event keys.
     * The keys reference the hash map in Model
     */
    private final ArrayList<String> events = new ArrayList<>();

    /**
     * Creates a user with a given email.
     *
     * @param userEmail The users given email in login
     */
    public GVUser(final String userEmail) {
        this.email = userEmail;
    }

    /**
     * Signs the user up for the given event name.
     *
     * @param event is the string event
     * */
    public void signUpEvent(final String event) {
        events.add(event);
    }

    /**
     * Returns the user's signed up events.
     *
     * @return ArrayList<String> that contains the users signed up events
     * */
    public ArrayList<String> getEvents() {
        return events;
    }

    /**
     * Returns the user's email.
     *
     * @return User email
     */
    public String getEmail() {
        return email;
    }
}
