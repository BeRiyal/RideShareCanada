package com.example.ridesharecanada.model;

public class Ride {
    String from;
    String to;
    String startTime;
    String date;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(String availableSeats) {
        this.availableSeats = availableSeats;
    }

    String availableSeats;
}
