package com.example.gvdiscoverapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mEvents; //= new ArrayList<String>();

    private Context mContext;

    public String mTitle;
    private String m;

    private Model mmodel;

    //private ArrayList<String> mUserEvents = new ArrayList<String>();


    public RecyclerViewAdapter(Context context, ArrayList<String> events) {
        mEvents = events;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_card_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");

        String[] arrayOfEventInformation = mEvents.get(i).split("~~");

        mmodel = Model.getInstance();
        final String currentEvent = mEvents.get(i);

        //this only occurs when i click button
        //needs a try catch block


        viewHolder.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mmodel.signUp(currentEvent);
                Toast.makeText(mContext, "You have signed up for this event", Toast.LENGTH_SHORT).show();

                //print events list
                Log.d(TAG, currentEvent + " added.");
            }
        });

        if(arrayOfEventInformation.length == 6) {
            viewHolder.eventTitle.setText(arrayOfEventInformation[0]);
            viewHolder.eventLoc.setText(arrayOfEventInformation[1]);
            viewHolder.eventDate.setText(arrayOfEventInformation[2]);
            viewHolder.eventSTime.setText(arrayOfEventInformation[3]);
            viewHolder.eventETime.setText(arrayOfEventInformation[4]);
            viewHolder.eventDesc.setText(arrayOfEventInformation[5]);
        }else{
            Log.d(TAG, "Not enough information input");
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView eventImage;
        public TextView eventTitle;
        public TextView eventDate;
        public TextView eventSTime;
        public TextView eventETime;
        public TextView eventDesc;
        public TextView eventLoc;
        public Button signUpButton;

        // used in on-click listener, we may or may not need
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventImage = itemView.findViewById(R.id.eventCardIcon);
            eventTitle = itemView.findViewById(R.id.eventCardTitle);
            eventDate = itemView.findViewById(R.id.eventCardDate);
            eventSTime = itemView.findViewById(R.id.eventCardSTime);
            eventETime = itemView.findViewById(R.id.eventCardETime);
            eventDesc = itemView.findViewById(R.id.eventCardDesc);
            eventLoc = itemView.findViewById(R.id.eventCardLoc);
            signUpButton = (Button) itemView.findViewById(R.id.signUpButton);

            //for on-click listener
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
