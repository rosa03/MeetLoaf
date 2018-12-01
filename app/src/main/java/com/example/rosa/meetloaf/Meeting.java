package com.example.rosa.meetloaf;

public class Meeting {

    private static int id;
    private String[] attendees;
    private String title, notes, location, date, time;

    public Meeting(String title, String[] attendees, String notes, String location){
        this.title = title;
        this.attendees = attendees;
        this.notes = notes;
        this.location = location;
    }


}
