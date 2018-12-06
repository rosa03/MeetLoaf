package com.example.rosa.meetloaf;

import java.util.Date;

public class Meeting {

//    private final int ID;
//    private static final AtomicInteger COUNT = new AtomicInteger(0);
    //private String[] attendees;
    private String attendees;
    private String title, notes, date, time;
    double latitude, longitude;

    //put attendees back into array
    public Meeting(String title, String convertedDate, String time){
//        this.ID = COUNT.incrementAndGet();
        this.title = title;
        this.date = convertedDate;
        this.time = time;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDate(String date){this.date = date;}

    public void setTime(String time){this.time = time;}

    public void setAttendees(String attendees){
        this.attendees = attendees;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTitle(){
        return title;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public String getAttendees(){
        return attendees;
    }

    public String getNotes(){
        return notes;
    }

    public double getLatitude(){return latitude;}

    public double getLongitude() {
        return longitude;
    }
}
