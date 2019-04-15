package com.example.gvdiscoverapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;

import java.io.File;

//import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ModelTest /*extends ActivityTestRule*/ {
    private LoginActivity act = new LoginActivity();
    private final String event1 = "CryFest~~Kirkof~~1/1/2019~~6:00pm~~7:00pm~~This is a really cool description";
    private final String event2 = "ABCFest~~Campus Recreation~~3/21/2020~~4:00pm~~5:00pm~~This is a realllllllly cool description";
    private final String mail = "mail@mail.gvsu.edu";

    private LoginActivity mActivity;

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

    @Test
    public void testFile() {
        //Context t = Instrumen   tationRegistory.getInstrumentation().context;
        //User and Model set up
        //act.onCreate(Bundle.EMPTY);
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
        //Context c = act.getBaseContext();
        File dir = act.getFilesDir();
        //File dir = new File(String.valueOf(c.getFilesDir()));

        try {
            Model.getInstance().save(dir);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        Model.reset();

        try {
            Model.getInstance().load(dir, mail);
        }
        catch (Exception e) {
            fail();
        }
        //TODO: Assertions
    }
}
