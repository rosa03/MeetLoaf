package com.example.rosa.meetloaf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class CreateFragment extends Fragment {

    protected EditText editTitle;
    protected AutoCompleteTextView editAttendees;
    protected EditText editNotes;
    protected EditText editTime;
    protected EditText editDate;
    protected Button location;
    protected ImageButton add;
    private ArrayList<String> attendees;
    static int textColour = Color.BLACK;
    static int textSize = 15;

    /**
     * Constructor for fragment.
     */
    public CreateFragment() {
        // Required empty public constructor
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        // Set title bar
        ((MainActivity) getActivity()).setActionBarTitle("Create Meeting");
        //reference all components to their IDs
        editTitle = view.findViewById(R.id.title);
        editAttendees = view.findViewById(R.id.attendees);
        editNotes = view.findViewById(R.id.notes);
        editDate = view.findViewById(R.id.date);
        editTime = view.findViewById(R.id.time);
        attendees = new ArrayList<>();
        editTitle.setTextColor(textColour);
        editAttendees.setTextColor(textColour);
        editNotes.setTextColor(textColour);
        editTime.setTextColor(textColour);
        editDate.setTextColor(textColour);
        editTitle.setTextSize(textSize);
        editAttendees.setTextSize(textSize);
        editNotes.setTextSize(textSize);
        editTime.setTextSize(textSize);
        editDate.setTextSize(textSize);
        final Button submit = view.findViewById(R.id.submit);
        final Button clear = view.findViewById(R.id.clear);
        final Button location = view.findViewById(R.id.location);
        final ImageButton add = view.findViewById(R.id.add);
        submit.setTextColor(textColour);
        clear.setTextColor(textColour);
        location.setTextColor(textColour);
        submit.setTextSize(textSize);
        clear.setTextSize(textSize);
        location.setTextSize(textSize);
        //starts map activity when location button is clicked
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MapsActivity.class);
                startActivity(i);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMeeting(view);
                clearForm(view);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearForm(view);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attendees.add(editAttendees.getText().toString());
                editAttendees.setText(" ");
                Toast.makeText(getActivity(), "Attendee Added", Toast.LENGTH_LONG).show();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.attendee_view, R.id.attendeeView, FileManager.findAttendees(getContext()));
        editAttendees.setAdapter(adapter);

        return view;
    }

    /**
     * This method clears all fields of form.
     *
     * @param v
     */
    public void clearForm(View v) {
        editTitle.setText(" ");
        editAttendees.setText(" ");
        editNotes.setText(" ");
        editTime.setText(" ");
        editDate.setText(" ");
    }

    /**
     * This method gets inputs from fields and validates them.
     *
     * @param v
     */
    public void createMeeting(View v) {
        // all of the data from the form
        Boolean valid = true;
        String title = this.editTitle.getText().toString();
        String notes = this.editNotes.getText().toString();
        String attendeesString = "";
        for (String attendee : attendees) {
            // Truncates string of attendees
            if (attendeesString.length() >= 1) {
                attendeesString += ", " + attendee;
                attendeesString = attendeesString.substring(0, attendeesString.length() - 1);
            } else {
                attendeesString += attendee;

            }
        }

        String time = this.editTime.getText().toString();
        Double latitude = MapsActivity.latitude;
        Double longitude = MapsActivity.longitude;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        Date convertedDate = new Date();
        String date = this.editDate.getText().toString();
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getActivity(), "Please enter date in the format dd/mm/yyyy", Toast.LENGTH_LONG).show();
            valid = false;
        }

        // If fields are empty, sets boolean to false
        if (title.equals("")) {
            valid = false;
        }
        if (date.equals("")) {
            valid = false;
        }
        if (time.equals("")) {
            valid = false;
        }
        if (attendeesString.equals("")) {
            valid = false;
        }

        // If boolean is true, creates meeting and adds to file, otherwise displays error message
        if (valid) {
            Meeting meeting = new Meeting(title, date, time);
            meeting.setLatitude(latitude);
            meeting.setLongitude(longitude);
            meeting.setAttendees(attendeesString);
            meeting.setNotes(notes);
            FileManager.saveMeetingToFile(meeting, getContext());
            Toast.makeText(getActivity(), "Meeting Saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Please enter all necessary fields", Toast.LENGTH_LONG).show();
        }

        valid = false;
    }


}
