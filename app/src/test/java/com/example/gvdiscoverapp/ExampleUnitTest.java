package com.example.gvdiscoverapp;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void addToSignUp() {
        Model model = Model.getInstance();
        model.addEvent("practiceEvent~~practiceEvent~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventsList = model.getEventsList();
        assertThat(eventsList, hasItem("practiceEvent~~practiceEvent~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event"));
    }

    @Test
    public void addToSignUp2() {
        Model model = Model.getInstance();
        model.addEvent("practiceEvent~~practiceEvent~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventsList = model.getEventsList();
        assertThat(eventsList, hasItem("practiceEvent~~practiceEvent~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event"));
    }
}