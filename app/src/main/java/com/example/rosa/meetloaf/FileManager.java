package com.example.rosa.meetloaf;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileManager {

    private Context context;
    private ArrayList<Meeting> meetings = new ArrayList<>();
    private String[] meeting;
    private String[] attendeeList;

    private final String FILE_NAME = "meetings.txt";

    /**
     * Construct a file manager object.
     * @param context
     */
    public FileManager(Context context) {
        this.context = context;
    }

    /**
     * This method saves a meeting in the meetings.txt file.
     * @param meeting
     */
    public void saveMeetingToFile(Meeting meeting) {

        // todo: figure out how you're going to store multiple meetings in the same file
        // todo: first step needs to figure out how to read in the file or 'add' to the end of a file
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            outputStreamWriter.write("\n" + meeting.getID() + "\n" + meeting.getTitle() + "\n" +
                    meeting.getAttendees() + "\n" + meeting.getNotes()  + "\n" + meeting.getLocation() );
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }

    public void readFile(File file) throws IOException{
        FileInputStream is;
        BufferedReader reader;
        if(file.exists()){
            is = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while(line != null){
                System.out.println(line);
                line = reader.readLine();
            }
        }
    }
}
