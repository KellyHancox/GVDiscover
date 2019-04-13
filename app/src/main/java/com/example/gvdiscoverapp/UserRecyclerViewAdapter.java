package com.example.gvdiscoverapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * UserRecyclerViewAdapter is the same as the first, but for the
 * events that the user specifically signed up for. It will not
 * include the sign up button, but look mostly the same as the
 * find events image
 */
public class UserRecyclerViewAdapter extends RecyclerView.
        Adapter<UserRecyclerViewAdapter.ViewHolder> {

    /**
     *  creates tag for class itself.
     **/
    private static final String TAG = "RecyclerViewAdapter";

    /**
     * list of only events that this user signed up for.
     */
    private ArrayList<String> userEvents;

    /**
     * RecyclerViewAdapter is the constructor that initializes.
     * the context and list of events
     * @param context this method
     * @param events list of available events
     */
    public UserRecyclerViewAdapter(final Context context,
                                   final ArrayList<String> events) {
        userEvents = events;
        /*
           context of this adapter.
         */
        Context mContext = context;
    }

    /**
     * onCreateViewHolder chooses which layout we're replacing
     * and returns it for use.
     *
     * @param viewGroup the overall class of card
     * @param i place of event in arrayList
     * @return the card
     */
    @NonNull
    @Override
    public UserRecyclerViewAdapter.ViewHolder onCreateViewHolder(
            @NonNull final ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.users_singular_event_layout,
                viewGroup, false);
        UserRecyclerViewAdapter.ViewHolder holder =
                new UserRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }

    /**
     * getItemCount counts how many events.
     * this user has signed up for
     *
     * @return count of events
     */
    @Override
    public int getItemCount() {
        return userEvents.size();
    }

    /**
     * onBindViewHolder takes values from the signed up events arrayList.
     *
     * and places them into the card
     * @param viewHolder the card
     * @param i the placement in the events arraylist
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        String[] arrayOfEventInformation = userEvents.get(i).split("~~");

        //if every input for create event was put in, then we can place these
        //values into the card
        if (arrayOfEventInformation.length == 6) {
            viewHolder.userEventTitle.setText(arrayOfEventInformation[0]);
            viewHolder.userEventLoc.setText(arrayOfEventInformation[1]);
            viewHolder.userEventDate.setText(arrayOfEventInformation[2]);
            viewHolder.userEventSTime.setText(arrayOfEventInformation[3]);
            viewHolder.userEventETime.setText(arrayOfEventInformation[4]);
            viewHolder.userEventDesc.setText(arrayOfEventInformation[5]);
        } else {
            Log.d(TAG, "Not enough information input");
        }
    }

    /**
     * This class binds the event info to the actual card.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         *  event image on card.
         */
        private ImageView userEventImage;

        /**
         * event title on card.
         */
        private TextView userEventTitle;

        /**
         *  event date on card .
         */
        private TextView userEventDate;

        /**
         * event start time on card.
         */
        private TextView userEventSTime;

        /**
         *  event end time on card.
         */
        private TextView userEventETime;

        /**
         *  event description on card.
         */
        private TextView userEventDesc;

        /**
         *  event location on card.
         */
        private TextView userEventLoc;


        /**
         * ViewHolder places the values into the card.
         *
         * @param itemView the card
         */
        ViewHolder(@NonNull final View itemView) {
            super(itemView);

            //these all bind the functions to each place on the card
            userEventImage = itemView.findViewById(R.id.userEventCardIcon);
            userEventTitle = itemView.findViewById(R.id.userEventCardTitle);
            userEventDate = itemView.findViewById(R.id.userEventCardDate);
            userEventSTime = itemView.findViewById(R.id.userEventCardSTime);
            userEventETime = itemView.findViewById(R.id.userEventCardETime);
            userEventDesc = itemView.findViewById(R.id.userEventCardDesc);
            userEventLoc = itemView.findViewById(R.id.userEventCardLoc);
        }

        /**
         * getter for UserEventImage.
         * @return returns the image for the event
         */
        public ImageView getUserEventImage() {
            return userEventImage;
        }

        /**
         * setter for UserEventImage.
         * @param userEventImageSet image that for an event
         */
        public void setUserEventImage(final ImageView userEventImageSet) {
            this.userEventImage = userEventImageSet;
        }
    }
}
