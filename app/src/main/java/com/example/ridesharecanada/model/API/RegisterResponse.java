package com.example.ridesharecanada.model.API;

import com.google.gson.annotations.SerializedName;
public class RegisterResponse {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
