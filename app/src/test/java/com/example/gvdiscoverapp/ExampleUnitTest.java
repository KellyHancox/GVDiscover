package com.example.gvdiscoverapp;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

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

    //when run by itself, this works. Then when run again, it does not.
    @Test
    public void checkRecyclerViewAdapterItems() {
        Model model = Model.getInstance();
        model.addEvent("practiceEvent~~practiceEvent~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        FindTheEvents findEvents = new FindTheEvents();

        ArrayList<String> eventList = model.getEventsList();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(findEvents, eventList);

        assertEquals(adapter.getItemCount(), 1);
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
    public void checkRecyclerViewAdapterItemCount() {
        Model model = Model.getInstance();
        model.addEvent("practiceEvent~~practiceEvent~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        FindTheEvents findEvents = new FindTheEvents();

        ArrayList<String> eventList = model.getEventsList();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(findEvents, eventList);

        assertEquals(adapter.getItemCount(), 1);
    }

    @Test
    public void checkArrayName() {
        Model model = Model.getInstance();
        model.addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        FindTheEvents findEvents = new FindTheEvents();
        ArrayList<String> eventList = model.getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[0], "practiceEvent");
    }

    @Test
    public void checkArrayPlace() {
        Model model = Model.getInstance();
        model.addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventList = model.getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[1], "practiceEvent");
    }

    @Test
    public void checkArrayDate() {
        Model model = Model.getInstance();
        model.addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventList = model.getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[2], "2/19/19");
    }

    @Test
    public void checkArraySTime() {
        Model model = Model.getInstance();
        model.addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventList = model.getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[3], "2:30 pm ");
    }

    @Test
    public void checkArrayETime() {
        Model model = Model.getInstance();
        model.addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventList = model.getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[4], " 3:30 pm ");
    }

    @Test
    public void checkArrayDescription() {
        Model model = Model.getInstance();
        model.addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventList = model.getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[5], " this is a practice event");
    }


}