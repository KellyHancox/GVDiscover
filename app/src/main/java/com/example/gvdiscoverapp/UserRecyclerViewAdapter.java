package com.example.gvdiscoverapp;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> userEvents; //= new ArrayList<String>();

    private Context mContext;


    public UserRecyclerViewAdapter(Context context, ArrayList<String> events) {
        userEvents = events;
        mContext = context;
    }

    @NonNull
    @Override
    public UserRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users_singular_event_layout,
                viewGroup, false);
        UserRecyclerViewAdapter.ViewHolder holder = new UserRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return userEvents.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");

        String[] arrayOfEventInformation = userEvents.get(i).split("~~");

        Log.d(TAG, "help " + arrayOfEventInformation[0]);
        Log.d(TAG, "help " + arrayOfEventInformation[1]);
        Log.d(TAG, "help " + arrayOfEventInformation[2]);

        if(arrayOfEventInformation.length == 6) {
            viewHolder.userEventTitle.setText(arrayOfEventInformation[0]);
            viewHolder.userEventLoc.setText(arrayOfEventInformation[1]);
            viewHolder.userEventDate.setText(arrayOfEventInformation[2]);
            viewHolder.userEventSTime.setText(arrayOfEventInformation[3]);
            viewHolder.userEventETime.setText(arrayOfEventInformation[4]);
            viewHolder.userEventDesc.setText(arrayOfEventInformation[5]);
        }else{
            Log.d(TAG, "Not enough information input");
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView userEventImage;
        public TextView userEventTitle;
        public TextView userEventDate;
        public TextView userEventSTime;
        public TextView userEventETime;
        public TextView userEventDesc;
        public TextView userEventLoc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userEventImage = itemView.findViewById(R.id.userEventCardIcon);
            userEventTitle = itemView.findViewById(R.id.userEventCardTitle);
            userEventDate = itemView.findViewById(R.id.userEventCardDate);
            userEventSTime = itemView.findViewById(R.id.userEventCardSTime);
            userEventETime = itemView.findViewById(R.id.userEventCardETime);
            userEventDesc = itemView.findViewById(R.id.userEventCardDesc);
            userEventLoc = itemView.findViewById(R.id.userEventCardLoc);
        }
    }
}
