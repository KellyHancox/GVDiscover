package com.example.gvdiscoverapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ModelTest {

    @Test
    public void testExists() {
        Model.reset();
        if (Model.getInstance() != null)
            assertTrue(true);
        else
            assertTrue(false);
    }

    @Test
    public void testAddEvent() {
        Model.reset();
        String event1 = "CryFest~~Kirkof~~1/1/2019~~6:00pm~~7:00pm~~This is a really cool description";
        String event2 = "ABCFest~~Campus Recreation~~3/21/2020~~4:00pm~~5:00pm~~This is a realllllllly cool description";
        Model.getInstance().addEvent(event1);
        Model.getInstance().addEvent(event2);
        //TODO: assertions
        assertEquals(event1, Model.getInstance().getEventsList().get(0));
        assertEquals(event2, Model.getInstance().getEventsList().get(1));
        //names~~location~~date~~startTime~~endTime~~desc
    }

    @Test
    public void testSignUp() {
        Model.reset();

    }

    @Test
    public void testGetEventList() {
        Model.reset();

    }

    @Test
    public void testGetUser() {
        Model.reset();

    }

    @Test
    public void testFile() {
        Model.reset();

    }
}
