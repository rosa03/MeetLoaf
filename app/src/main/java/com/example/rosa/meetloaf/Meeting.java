package com.example.rosa.meetloaf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Meeting {

//    private final int ID;
//    private static final AtomicInteger COUNT = new AtomicInteger(0);
    //private String[] attendees;
    private String attendees;
    private String title, notes, location, date, time;

    //put attendees back into array
    public Meeting(String title, String location){
//        this.ID = COUNT.incrementAndGet();
        this.title = title;
        this.attendees = attendees;
        this.notes = notes;
        this.location = location;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAttendees(String attendees){
        this.attendees = attendees;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public void setLocation(String location){
        this.location = location;
    }

//    public int getID(){
//        return this.ID;
//    }

    public String getTitle(){
        return title;
    }

    public String getAttendees(){
        return attendees;
    }

    public String getNotes(){
        return notes;
    }

    public String getLocation(){
        return location;
    }

}
