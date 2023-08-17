package com.example.ridesharecanada.model.API;

import com.google.gson.annotations.SerializedName;

public class AddRideRequest {
    @SerializedName("driverId")
    String DriverId;

    @SerializedName("from")
    String From;
    @SerializedName("to")
    String To;
    @SerializedName("date")
    String Date;
    @SerializedName("startTime")
    String Time;
    @SerializedName("availableSeats")
    String Seats;

    public AddRideRequest(String driverId,String from, String to, String date, String time, String seats) {
        this.From = from;
        this.To = to;
        this.Date = date;
        this.Time = time;
        this.Seats = seats;
        this.DriverId = driverId;
    }


    public String getDid() {
        return DriverId;
    }

    public void setDid(String driverId) {
        DriverId = driverId;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSeats() {
        return Seats;
    }

    public void setSeats(String seats) {
        Seats = seats;
    }
}
