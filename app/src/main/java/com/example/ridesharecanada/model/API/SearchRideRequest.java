package com.example.ridesharecanada.model.API;

import com.google.gson.annotations.SerializedName;

public class SearchRideRequest {
    @SerializedName("from")
    String from;
    @SerializedName("to")
    String to;

    public SearchRideRequest(String from, String to) {
        this.from = from;
        this.to = to;
    }
}
