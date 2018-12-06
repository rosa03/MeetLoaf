package com.example.rosa.meetloaf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class CreateFragment extends Fragment {

    protected EditText editTitle;
    protected EditText editAttendees;
    protected EditText editNotes;
    protected EditText editTime;
    protected EditText editDate;
    protected Button location;

    private TextView theDate;

    private GoogleMap map;

    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        // Set title bar
        ((MainActivity) getActivity()).setActionBarTitle("Create Meeting");
        editTitle = view.findViewById(R.id.title);
        editAttendees = view.findViewById(R.id.attendees);
        editNotes = view.findViewById(R.id.notes);
        editDate = view.findViewById(R.id.date);
        editTime = view.findViewById(R.id.time);
        final Button submit = view.findViewById(R.id.submit);
        final Button clear = view.findViewById(R.id.clear);
        final Button location = view.findViewById(R.id.location);
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
        return view;
    }

    public void clearForm(View v) {
        editTitle.setText(" ");
        editAttendees.setText(" ");
        editNotes.setText(" ");
        editTime.setText(" ");
        editDate.setText(" ");
    }

    public void createMeeting(View v) {
        // all of the data from the form
        Boolean valid = true;

        String title = this.editTitle.getText().toString();
        String notes = this.editNotes.getText().toString();
        String attendees = this.editAttendees.getText().toString();
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

        if (title == "" || date == "" || time == "" || attendees == "" || notes == "") {
            valid = false;
        }

        if (valid) {
            Meeting meeting = new Meeting(title, date, time);
            meeting.setLatitude(latitude);
            meeting.setLongitude(longitude);
            meeting.setAttendees(attendees);
            meeting.setNotes(notes);
            FileManager.saveMeetingToFile(meeting, getContext());
            Toast.makeText(getActivity(), "Saved to " + getActivity().getFilesDir() +
                    "/" + "meetings.txt", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Enter all fields", Toast.LENGTH_LONG).show();
        }

        valid = true;

    }


}
