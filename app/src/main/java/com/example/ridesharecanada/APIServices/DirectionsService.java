package com.example.ridesharecanada.APIServices;

import com.example.ridesharecanada.model.API.DirectionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface DirectionsService {
    @GET("directions/json")
    Call<DirectionsResponse> getDirections(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("key") String apiKey
    ) ;


}
