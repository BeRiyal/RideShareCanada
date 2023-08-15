package com.example.ridesharecanada.model.API;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private String data;

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    private String message; // Change the type to String

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getData() {
        return data;
    }

    public String getMessage() { // Add a getter for the message field
        return message;
    }
}
