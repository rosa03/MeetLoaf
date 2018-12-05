package com.example.rosa.meetloaf;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.List;

public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.ViewHolder> {

    private List<Meeting> meetings;
    private Context context;

    // Pass in the contact array into the constructor
    public MeetingsAdapter(List<Meeting> meetings, Context context) {
        this.context = context;
        this.meetings = meetings;
    }

    @Override
    public MeetingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.item_meeting, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view, meetings);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView meetingName;
        public TextView meetingDate;
        public TextView meetingTime;
        public List<Meeting> meetings;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView, List<Meeting> meetings) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            this.meetings = meetings;
            meetingName = (TextView) itemView.findViewById(R.id.meetingName);
            meetingDate = itemView.findViewById(R.id.meetingDate);
            meetingTime = itemView.findViewById(R.id.meetingTime);
        }
    }

    @Override
    public void onBindViewHolder(MeetingsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Meeting meeting = meetings.get(position);

        // Set item views based on your views and data model
        TextView t1 = viewHolder.meetingName;
        TextView t2 = viewHolder.meetingDate;
        TextView t3 = viewHolder.meetingTime;

        t1.setText(meeting.getTitle());
        t2.setText(meeting.getDate());
        t3.setText(meeting.getTime());
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

}
