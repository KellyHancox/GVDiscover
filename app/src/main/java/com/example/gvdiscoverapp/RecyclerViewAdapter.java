package com.example.gvdiscoverapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;

/**
 * RecyclerViewAdapter is necessary for RecyclerView to
 * put the values of the events into the cards.
 *
 * @author Kelly Hancox
 * */
public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    /**
     *  creates tag for class itself.
     */
    private static final String TAG = "RecyclerViewAdapter";

    /**
     *  list of all events created.
     */
    private final ArrayList<String> mEvents;

    /**
     *  context of this adapter.
     */
    private final Context mContext;

    /**
     * RecyclerViewAdapter is the constructor that initializes
     * the context and list of events.
     * @param context this method
     * @param events list of available events
     */
    RecyclerViewAdapter(final Context context,
                        final ArrayList<String> events) {
        mEvents = events;
        mContext = context;
    }

    /**
     * onCreateViewHolder chooses which layout we're replacing and returns
     * it for use.
     *
     * @param viewGroup the overall class of card
     * @param i place of event in arrayList
     * @return the card
     */
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(
            @NonNull final ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.event_card_layout,
                viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * getItemCount counts how many events have been created.
     *
     * @return count of events
     */
    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    /**
     * onBindViewHolder takes values from the arrayList.
     *
     * and places them into the card
     * @param viewHolder the card
     * @param i the placement in the events arrayList
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder,
                                 final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        String[] arrayOfEventInformation = mEvents.get(i).split("~~");

        final String currentEvent = mEvents.get(i);

        //calls upon the sign up button on click listener
        viewHolder.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                try {
                    Model.getInstance().signUp(currentEvent);
                    Toast.makeText(mContext,
                            "You have signed up for this event",
                            Toast.LENGTH_SHORT).show();
                    File directory = mContext.getFilesDir();
                    Model.getInstance().save(directory);
                } catch (NoUserFoundException e) {
                    Toast.makeText(mContext, "User not found",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(mContext, "Unknown exception",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                //print events list into the log for debugging
                Log.d(TAG, currentEvent + " added.");
            }
        });

        //if every input for create event was put in, then we can place these
        //values into the card
        if (arrayOfEventInformation.length == 6) {
            viewHolder.eventTitle.setText(arrayOfEventInformation[0]);
            viewHolder.eventLoc.setText(arrayOfEventInformation[1]);
            viewHolder.eventDate.setText(arrayOfEventInformation[2]);
            viewHolder.eventSTime.setText(arrayOfEventInformation[3]);
            viewHolder.eventETime.setText(arrayOfEventInformation[4]);
            viewHolder.eventDesc.setText(arrayOfEventInformation[5]);
        } else {
            Log.d(TAG, "Not enough information input");
        }
    }

    /**
     * This class binds the event info to the actual card.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         *  event title on card.
         */
        private final TextView eventTitle;

        /**
         *  event date on card.
         */
        private final TextView eventDate;

        /**
         *  event start time on card.
         */
        private final TextView eventSTime;

        /**
         *  event end time on card.
         */
        private final TextView eventETime;

        /**
         *  event description on card.
         */
        private final TextView eventDesc;

        /**
         *  event location on card.
         */
        private final TextView eventLoc;

        /**
         *  event sign up button on card.
         */
        private final Button signUpButton;


        /**
         * ViewHolder places the values into the card.
         *
         * @param itemView the card
         */
        ViewHolder(@NonNull final View itemView) {
            super(itemView);

            //these all bind the functions to each place on the card
            eventTitle = itemView.findViewById(R.id.eventCardTitle);
            eventDate = itemView.findViewById(R.id.eventCardDate);
            eventSTime = itemView.findViewById(R.id.eventCardSTime);
            eventETime = itemView.findViewById(R.id.eventCardETime);
            eventDesc = itemView.findViewById(R.id.eventCardDesc);
            eventLoc = itemView.findViewById(R.id.eventCardLoc);
            signUpButton = itemView.findViewById(R.id.signUpButton);

        }
    }

}
