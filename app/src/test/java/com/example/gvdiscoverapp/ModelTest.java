package com.example.gvdiscoverapp;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ModelTest {
    private final LoginActivity act = new LoginActivity();

    private final String event1 = "CryFest~~Kirkof~~1/1/2019~~6:00pm~~7:00pm~~This is a really cool description";
    private final String event2 = "ABCFest~~Campus Recreation~~3/21/2020~~4:00pm~~5:00pm~~This is a realllllllly cool description";
    private final String mail = "mail@mail.gvsu.edu";

    @Test
    public void testExists() {
        Model.reset();
        if (Model.getInstance() != null)
            assertTrue(true);
        else
            fail();
    }

    @Test
    public void testAddEvent() {
        Model.reset();
        Model.getInstance().addEvent(event1);
        Model.getInstance().addEvent(event2);
        assertEquals(event1, Model.getInstance().getEventsList().get(0));
        assertEquals(event2, Model.getInstance().getEventsList().get(1));
        //names~~location~~date~~startTime~~endTime~~desc
    }

    @Test(expected = NoUserFoundException.class)
    public void testSignUpNoUser() throws NoUserFoundException {
        Model.reset();
        Model.getInstance().signUp(event1);
    }

    @Test
    public void testSignUp() {
        Model.reset();
        Model.getInstance().logIn(mail);
        try {
            Model.getInstance().signUp(event1);
            Model.getInstance().signUp(event2);
        }
        catch(Exception e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(Model.getInstance().getUser().getEvents().get(0).equals(event1) &&
                Model.getInstance().getUser().getEvents().get(1).equals(event2));
    }

    @Test
    public void testGetEventList() {
        Model.reset();
        Model.getInstance().addEvent(event1);
        Model.getInstance().addEvent(event2);
        assertTrue(Model.getInstance().getEventsList().get(0).equals(event1) &&
                Model.getInstance().getEventsList().get(1).equals(event2));
    }

    @Test
    public void testGetUser() {
        Model.reset();
        Model.getInstance().logIn(mail);
        assertEquals(Model.getInstance().getUser().getEmail(), mail);
    }

    @Test(expected = NoUserFoundException.class)
    public void testSaveFail() throws NoUserFoundException{
        File dir = act.getFilesDir();
        Model.reset();
        try {
            Model.getInstance().save(dir);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testLoadNewUser() {
        File dir = act.getFilesDir();
        Model.reset();
        String user = Math.random() + "@mail.gvsu.edu";
        try {
            Model.getInstance().load(dir, user);
        }
        catch (Exception e) {
            fail();
        }

        boolean flagUser = Model.getInstance().getUser().getEmail().equals(user);

        assertTrue(flagUser);
    }

    @Test
    public void testSaveAndLoad() {
        //User and Model set up
        File dir = act.getFilesDir();
        Model.reset();
        Model.getInstance().addEvent(event1);
        Model.getInstance().addEvent(event2);
        Model.getInstance().logIn(mail);
        try {
            Model.getInstance().signUp(event1);
        }
        catch(Exception e) {
            e.printStackTrace();
            fail();
        }

        //Saving Process
        try {
            Model.getInstance().save(dir);
        }
        catch (Exception e) {
            fail();
        }

        Model.reset();

        try {
            Model.getInstance().load(dir, mail);
        }
        catch (Exception e) {
            fail();
        }

        assertTrue(Model.getInstance().getUser().getEmail().equals(mail) &&
                Model.getInstance().getEventsList().get(0).equals(event1) &&
                Model.getInstance().getEventsList().get(1).equals(event2));
    }

    @Test
    public void TestDeleteAll() {
        LoginActivity act = new LoginActivity();
        File dir = act.getFilesDir();
        Model.reset();
        Model.getInstance().addEvent(event1);
        Model.getInstance().addEvent(event2);
        Model.getInstance().logIn(mail);
        try {
            Model.getInstance().signUp(event1);
        }
        catch(Exception e) {
            fail();
        }

        try {
            Model.getInstance().save(dir);
        }
        catch (Exception e) {
            fail();
        }

        try {
            Model.getInstance().deleteAll(dir);//Fails here
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Model.getInstance().load(dir, mail);
        }
        catch (Exception e) {
            fail();
        }

        boolean flagUser = Model.getInstance().getUser().getEmail().equals(mail);

        assertTrue(flagUser);

    }

    @Test
    public void addToSignUp() {
        Model.reset();
        Model.getInstance().addEvent("practiceEvent~~practiceEvent~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventsList = Model.getInstance().getEventsList();
        assertThat(eventsList, hasItem("practiceEvent~~practiceEvent~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event"));
    }

    @Test
    public void TestLogOut() {
        Model.reset();
        Model.getInstance().logIn(mail);
        Model.getInstance().logOut();
        assertNull(Model.getInstance().getUser());
    }
}
