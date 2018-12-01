package com.example.rosa.meetloaf;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class FileManager {

    private Context context;
    private String[] meetings;
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
     * @param title
     * @param attendees
     * @param notes
     * @param location
     */
    public void saveMeetingToFile(String title, String attendees, String notes, String location) {

        // todo: figure out how you're going to store multiple meetings in the same file
        // todo: first step needs to figure out how to read in the file or 'add' to the end of a file
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            Meeting meeting = new Meeting(title, attendeeList, notes, location);
            outputStreamWriter.write(title + "\n" + attendees + "\n" + notes + "\n" + location);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }

    public void readFile(File file) throws IOException{
        Scanner scanner = new Scanner(file);

        while(scanner.hasNext()){
            String[] tokens = scanner.nextLine().split(";");
            String last = tokens[tokens.length - 1];
            System.out.println(last);
        }
    }
}
