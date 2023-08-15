package com.example.ridesharecanada.model.API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("data")
    private List<RideData> rideList;

    public List<RideData> getRideList() {
        return rideList;
    }

    public void setRideList(List<RideData> rideList) {
        this.rideList = rideList;
    }

    // Define the RideData class that represents a single ride
    public static class RideData {
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        @SerializedName("_id")
        private String id;

        @SerializedName("from")
        private String from;

        @SerializedName("to")
        private String to;

        @SerializedName("startTime")
        private String startTime;

        @SerializedName("date")
        private String date;

        @SerializedName("availableSeats")
        private String availableSeats;

        @SerializedName("participants")
        private List<String> participants;

        @SerializedName("__v")
        private String version;

        @Override
        public String toString() {
            return "RideData{" +
                    "id='" + id + '\'' +
                    ", from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    // Add other properties here
                    '}';
        }
    }
}
