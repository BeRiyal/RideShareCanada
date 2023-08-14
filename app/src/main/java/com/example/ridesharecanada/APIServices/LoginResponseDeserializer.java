package com.example.ridesharecanada.APIServices;

import android.util.Log;

import com.example.ridesharecanada.model.API.ApiResponse;
import com.example.ridesharecanada.model.LoginResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;



public class LoginResponseDeserializer implements JsonDeserializer<ApiResponse<LoginResponse>> {
    @Override
    public ApiResponse<LoginResponse> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        ApiResponse<LoginResponse> apiResponse = new ApiResponse<LoginResponse>();

        if (jsonObject.has("success")) {
            apiResponse.setSuccess(jsonObject.get("success").getAsBoolean());
        }

        if (jsonObject.has("message")) {
            apiResponse.setMessage(jsonObject.get("message").getAsString());
        }

        if (jsonObject.has("data")) {
            JsonElement dataElement = jsonObject.get("data");
            LoginResponse data = context.deserialize(dataElement, LoginResponse.class);
            apiResponse.setData(data);
        }
        return apiResponse;
    }

}

