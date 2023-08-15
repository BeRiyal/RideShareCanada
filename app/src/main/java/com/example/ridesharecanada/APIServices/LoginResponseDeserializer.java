package com.example.ridesharecanada.APIServices;

import com.example.ridesharecanada.model.API.ApiResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;



public class LoginResponseDeserializer implements JsonDeserializer<ApiResponse> {
    @Override
    public ApiResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        ApiResponse apiResponse = new ApiResponse();

        if (jsonObject.has("success")) {
            apiResponse.setSuccess(jsonObject.get("success").getAsBoolean());
        }

        if (jsonObject.has("message")) {
            apiResponse.setMessage(jsonObject.get("message").getAsString());
        }

        if (jsonObject.has("data")) {
            JsonElement dataElement = jsonObject.get("data");
            apiResponse.setData(dataElement.toString());
        }
        return apiResponse;
    }

}

