package com.example.gvdiscoverapp;

import org.junit.Test;

import static org.junit.Assert.*;


public class GVUserTest {

    @Test
    public void testConstructor() {
        String mail = "mail@mail.gvsu.edu";
        GVUser user = new GVUser(mail);
        assertEquals(mail, user.getEmail());
    }

    @Test
    public void testSignUpEvent() {
        String mail = "mail@mail.gvsu.edu";
        GVUser user = new GVUser(mail);
        String event = "event~~name~~";
        user.signUpEvent();
    }

    @Test
    public void testGetEvents() {

    }

    @Test
    public void testGetEmail() {

    }
}
