package com.example.rosa.meetloaf;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateFragment extends Fragment {

    protected EditText editTitle;
    protected EditText editAttendees;
    protected EditText editNotes;
    protected EditText editLocation;

    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("test");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        editTitle = view.findViewById(R.id.title);
        editAttendees = view.findViewById(R.id.attendees);
        editNotes = view.findViewById(R.id.notes);
        editLocation = view.findViewById(R.id.location);
        final Button submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMeeting(view);

            }
        });
        return view;
    }

    public void createMeeting(View v){
        // all of the data from the form
        String title = this.editTitle.getText().toString();
        String notes = this.editNotes.getText().toString();
        String attendees = this.editAttendees.getText().toString();
        String location = this.editLocation.getText().toString();

        System.out.println(title);

        FileManager fm = new FileManager(getContext());
        fm.saveMeetingToFile(title, attendees, notes, location);


//        try {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        String text = CreateFragment.editTitle.getText().toString() + "\n" + CreateFragment.editAttendees.getText().toString() +
//                "\n" + CreateFragment.editNotes.getText().toString() + "\n" + CreateFragment.editLocation.getText().toString();
//
//
//        System.out.print(text);
//        System.exit(0);

    }

}
