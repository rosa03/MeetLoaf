package com.example.rosa.meetloaf;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private static final String FILE_NAME = "meetings.txt";
    EditText editTitle;
    EditText editAttendees;
    EditText editNotes;
    EditText editLocation;

    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
                writeFile(view);
            }
        });
        return view;
    }

    public void writeFile(View v){
        String text = editTitle.getText().toString() + "\n" + editAttendees.getText().toString() +
                "\n" + editNotes.getText().toString() + "\n" + editLocation.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = getContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(text.getBytes());

            editTitle.getText().clear();
            editAttendees.getText().clear();
            editNotes.getText().clear();
            editLocation.getText().clear();
            Toast.makeText(getActivity(),"Saved to " + getActivity().getFilesDir() +
            "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
