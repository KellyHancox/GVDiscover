package com.example.gvdiscoverapp;

import java.util.ArrayList;

public class GVUser {
    /** A string for the user email. */
    String email;
    /** An ArrayList of string that hold event keys. The keys reference the hash map in Model */
    ArrayList<String> events;

    public GVUser(String email) {
        this.email = email;
    }

    public void addEvent(String key) {
        events.add(key);
    }

    public ArrayList<String> getEvents() {
        return events;
    }
}
