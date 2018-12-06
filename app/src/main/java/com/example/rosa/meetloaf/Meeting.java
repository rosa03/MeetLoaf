package com.example.rosa.meetloaf;

import java.util.Date;

public class Meeting {

    private String attendees;
    private String title, notes, date, time;
    double latitude, longitude;

    /**
     * Constructs a meeting object.
     *
     * @param title
     * @param convertedDate
     * @param time
     */
    public Meeting(String title, String convertedDate, String time) {
        this.title = title;
        this.date = convertedDate;
        this.time = time;
    }

    /**
     * This method sets the meeting title.
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method sets the meeting date.
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * This method sets the meeting time.
     *
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * This method sets the meeting's attendees.
     *
     * @param attendees
     */
    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    /**
     * This method sets the meeting's notes.
     *
     * @param notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * This method sets the latitude of the meeting's location.
     *
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method sets the longitude of the meeting's location.
     *
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method returns the meeting title.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method returns the meeting date.
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * This method returns the meeting time.
     *
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * This method returns the meeting's attendees.
     *
     * @return attendees
     */
    public String getAttendees() {
        return attendees;
    }

    /**
     * This method returns the meeting's notes.
     *
     * @return notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * This method returns the latitude.
     *
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * This method returns the longitude.
     *
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }
}
