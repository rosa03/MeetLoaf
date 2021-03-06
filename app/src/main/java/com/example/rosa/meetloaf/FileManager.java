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
     * * @param meeting
     */
    public static void saveMeetingToFile(Meeting meeting, Context context) {

        // todo: figure out how you're going to store multiple meetings in the same file
        // todo: first step needs to figure out how to read in the file or 'add' to the end of a file
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write("\n" + meeting.getTitle() + ";" + meeting.getDate() + ";" + meeting.getTime() + ";" +
                    meeting.getAttendees() + ";" + meeting.getLatitude() + ";" + meeting.getLongitude() + ";" + meeting.getNotes());
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }

    /**
     * This method reads the file and returns a list of meeting objects.
     *
     * @param context
     * @return meetings
     */
    public static List<Meeting> readFile(Context context) {
        List<Meeting> meetings = new ArrayList<>();
        // Reads in all input data
        try {
            InputStream inputStream = context.openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            Meeting meeting = null;
            String getString = "";
            while ((getString = bufferedReader.readLine()) != null) {
                // Separates each piece of data with a semi-colon
                String[] attributes = getString.split(";");
                // Each piece of data is assigned to an index in a set array
                if (attributes.length >= 6) {
                    meeting = new Meeting(attributes[0], attributes[1], attributes[2]);
                    meeting.setAttendees(attributes[3]);
                    meeting.setLatitude(Double.parseDouble(attributes[4]));
                    meeting.setLongitude(Double.parseDouble(attributes[5]));
                    if (attributes.length > 6){
                        meeting.setNotes(attributes[6]);
                    }
                    meetings.add(meeting);
                }
            }
            inputStream.close();
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return meetings;
    }

    public static List<String> findAttendees(Context c ){
        List<Meeting> m = readFile(c);
        List<String> names = new ArrayList<>();

        for (Meeting meet : m){
            String[] n = meet.getAttendees().split(",");

            for (String s : n){
                names.add(s);
            }
        }

        return names;
    }

}