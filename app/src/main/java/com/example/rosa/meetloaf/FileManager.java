package com.example.rosa.meetloaf;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileManager {

    private Context context;

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
        // try writing to the file
        // if file doesn't exist it will automatically create one
        // todo: figure out how you're going to store multiple meetings in the same file
        // todo: first step needs to figure out how to read in the file or 'add' to the end of a file
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("meetings.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(title);
            outputStreamWriter.write(attendees);
            outputStreamWriter.write(notes);
            outputStreamWriter.write(location);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        System.out.print("hello");
    }
}
