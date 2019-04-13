package com.example.gvdiscoverapp;

import org.junit.Test;

import static org.junit.Assert.*;


public class  GVUserTest {
    //TODO: 100% lines covered, just add more test cases
    String event1 = "event123~~Kirkof~~1/2/2019~~5:00pm~~6:00pm~~This is an awesome description";
    String mail = "mail@mail.gvsu.edu";
    @Test
    public void testConstructor() {
        GVUser user = new GVUser(mail);
        assertEquals(mail, user.getEmail());
    }

    @Test
    public void testSignUpEvent() {
        GVUser user = new GVUser(mail);

        user.signUpEvent(event1);
        assertEquals(user.getEvents().get(0),event1);
        //names~~location~~date~~startTime~~endTime~~desc
    }

    @Test
    public void testGetEmail() {
        GVUser user= new GVUser(mail);
        assertEquals(user.getEmail(), mail);
    }
}
