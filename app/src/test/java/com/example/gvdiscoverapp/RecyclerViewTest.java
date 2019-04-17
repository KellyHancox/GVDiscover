package com.example.gvdiscoverapp;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RecyclerViewTest {

    //when run by itself, this works. Then when run again, it does not.
    @Test
    public void checkRecyclerViewAdapterItems() {
        Model.reset();

        Model.getInstance().addEvent("practiceEvent~~practiceEvent~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        FindTheEvents findEvents = new FindTheEvents();

        ArrayList<String> eventList = Model.getInstance().getEventsList();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(findEvents, eventList);

        assertEquals(adapter.getItemCount(), 1);
    }

    @Test
    public void checkRecyclerViewAdapterItemCount() {
        Model.reset();
        Model.getInstance().addEvent("practiceEvent~~practiceEvent~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        FindTheEvents findEvents = new FindTheEvents();

        ArrayList<String> eventList = Model.getInstance().getEventsList();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(findEvents, eventList);

        assertEquals(adapter.getItemCount(), 1);
    }

    @Test
    public void checkArrayName() {
        Model.reset();
        Model.getInstance().addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventList = Model.getInstance().getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[0], "practiceEvent");
    }

    @Test
    public void checkArrayPlace() {
        Model.reset();
        Model.getInstance().addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventList = Model.getInstance().getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[1], "Event");
    }

    @Test
    public void checkArrayDate() {
        Model.reset();
        Model.getInstance().addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventList = Model.getInstance().getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[2], "2/19/19");
    }

    @Test
    public void checkArraySTime() {
        Model.reset();
        Model.getInstance().addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventList = Model.getInstance().getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[3], "2:30 pm ");
    }

    @Test
    public void checkArrayETime() {
        Model.reset();
        Model.getInstance().addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventList = Model.getInstance().getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[4], " 3:30 pm ");
    }

    @Test
    public void checkArrayDescription() {
        Model.reset();
        Model.getInstance().addEvent("practiceEvent~~Event~~2/19/19~~2:30 pm " +
                "~~ 3:30 pm ~~ this is a practice event");

        ArrayList<String> eventList = Model.getInstance().getEventsList();

        String[] arrayOfEventInformation = eventList.get(0).split("~~");

        assertEquals(arrayOfEventInformation[5], " this is a practice event");
    }
}