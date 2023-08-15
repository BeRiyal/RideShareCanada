package com.example.ridesharecanada.model.API;

import com.google.gson.annotations.SerializedName;

public class AddRideResponse {
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @SerializedName("data")
    private String data;
}
