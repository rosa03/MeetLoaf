package com.example.rosa.meetloaf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.ViewHolder> {

    private List<Meeting> meetings;
    private Context context;
    private MeetingsAdapter ma;

    /**
     * Constructs a MeetingsAdapter object.
     *
     * @param meetings
     * @param context
     */
    public MeetingsAdapter(List<Meeting> meetings, Context context) {
        this.context = context;
        this.meetings = meetings;
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
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
        public TextView meetingName;
        public TextView meetingDate;
        public TextView meetingTime;
        public List<Meeting> meetings;

        // Constructor that accepts the entire item row and references the itemViews to their corresponding IDs
        public ViewHolder(View itemView, List<Meeting> meetings) {
            // Stores the itemView which can be used to access the context from any ViewHolder instance
            super(itemView);
            this.meetings = meetings;
            meetingName = itemView.findViewById(R.id.meetingName);
            meetingDate = itemView.findViewById(R.id.meetingDate);
            meetingTime = itemView.findViewById(R.id.meetingTime);
        }
    }

    /**
     * This method recycles views when all positions are filled.
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MeetingsAdapter.ViewHolder viewHolder, int position) {
        // Gets the data model based on position
        final Meeting meeting = meetings.get(position);

        // Sets item views to corresponding data
        TextView t1 = viewHolder.meetingName;
        TextView t2 = viewHolder.meetingDate;
        TextView t3 = viewHolder.meetingTime;

        t1.setText(meeting.getTitle());
        t2.setText(meeting.getDate());
        t3.setText(meeting.getTime());

        // Creates an alert dialog when an item view is clicked
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle(meeting.getTitle());
                alertDialog.setMessage("Date: " + meeting.getDate() + "\n" + "Time: " + meeting.getTime() +
                        "\n" + "Attendees: " + meeting.getAttendees() + "\n" + "Notes: " + meeting.getNotes());
                // Creates 'OK' button on dialog
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            // Closes dialog once button is clicked
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                // Creates 'View Location' button on dialog
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "View Location",
                        new DialogInterface.OnClickListener() {
                            // Gets latitude and longitude of meeting location and opens map activity
                            public void onClick(DialogInterface dialog, int which) {
                                MapsActivity.latitude = meeting.getLatitude();
                                MapsActivity.longitude = meeting.getLongitude();
                                launchIntent();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    /**
     * This method returns number of meetings in array list.
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return meetings.size();
    }

    /**
     * This method launches the map activity.
     */
    public void launchIntent() {
        ma = this;
        Intent i = new Intent(ma.context, MapsActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }

}
