package com.example.rosa.meetloaf;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

public class CreateFragment extends Fragment {

    protected EditText editTitle;
    protected EditText editAttendees;
    protected EditText editNotes;
    protected EditText editLocation;
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
        editLocation = view.findViewById(R.id.location);
        final Button submit = view.findViewById(R.id.submit);
        final Button clear = view.findViewById(R.id.clear);
        final Button selectDate = view.findViewById(R.id.selectDate);
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
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //selectDate(view);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//
//                    }
//                });
                //datePickerDialog.show();
            }
        });
        return view;
    }

    public void clearForm(View v){
        editTitle.setText(" ");
        editAttendees.setText(" ");
        editNotes.setText(" ");
        editLocation.setText(" ");
    }

    public void createMeeting(View v){
        // all of the data from the form
        String title = this.editTitle.getText().toString();
        String notes = this.editNotes.getText().toString();
        String attendees = this.editAttendees.getText().toString();
        String location = this.editLocation.getText().toString();
        Meeting meeting = new Meeting(title, location);
        FileManager fm = new FileManager(getContext());
        fm.saveMeetingToFile(meeting);
        Toast.makeText(getActivity(),"Saved to " + getActivity().getFilesDir() +
                "/" + "meetings.txt", Toast.LENGTH_LONG).show();

    }

    public void selectDate(View v){


    }


}
