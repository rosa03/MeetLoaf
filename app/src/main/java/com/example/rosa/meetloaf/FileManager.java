package com.example.rosa.meetloaf;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    private Context context;
    private ArrayList<Meeting> meetings = new ArrayList<>();
    private String[] attributes;
    private String[] attendeeList;

    private static final String FILE_NAME = "meetings.txt";

    /**
     * Construct a file manager object.
     *
     * @param context
     */
    public FileManager(Context context) {
        this.context = context;
    }

    /**
     * This method saves a meeting in the meetings.txt file.
     *
     * @param meeting
     */
    public static void saveMeetingToFile(Meeting meeting, Context context) {

        // todo: figure out how you're going to store multiple meetings in the same file
        // todo: first step needs to figure out how to read in the file or 'add' to the end of a file
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write(meeting.getTitle() + ";" +
                    meeting.getAttendees() + ";" + meeting.getNotes() + ";" + meeting.getLocation());
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }

    public static List<Meeting> readFile(Context context) {
        List<Meeting> meetings = new ArrayList<>();
        try {
            InputStream inputStream = context.openFileInput("meetings.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            Meeting meeting = null;
            String getString = "";
            while ((getString = bufferedReader.readLine()) != null) {
                String[] attributes = getString.split(";");
                if (attributes.length > 6) {
                    meeting = new Meeting(attributes[0], attributes[3]);
                    meeting.setAttendees(attributes[1]);
                    meetings.add(meeting);
                }
            }
            inputStream.close();
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return meetings;
    }
}
