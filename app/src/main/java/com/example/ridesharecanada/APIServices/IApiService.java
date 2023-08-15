package com.example.ridesharecanada.APIServices;

import com.example.ridesharecanada.model.API.AddRideRequest;
import com.example.ridesharecanada.model.API.AddRideResponse;
import com.example.ridesharecanada.model.API.ApiResponse;
import com.example.ridesharecanada.model.API.LoginRequest;
import com.example.ridesharecanada.model.API.LoginResponse;
import com.example.ridesharecanada.model.API.RegisterRequest;
import com.example.ridesharecanada.model.API.RegisterResponse;
import com.example.ridesharecanada.model.API.SearchResponse;
import com.example.ridesharecanada.model.API.SearchRideRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IApiService {
    //_____Login
    @POST("/api/users/login")
    Call<ApiResponse<LoginResponse>> login(@Body LoginRequest request);
    //_____Registration
    @POST("/api/users/add")
    Call<ApiResponse<RegisterResponse>> register(@Body RegisterRequest request);
    //_____AddRide
    @POST("/api/rides/add")
    Call<ApiResponse<AddRideResponse>> addRide(@Body AddRideRequest request);
    //_____SearchRide
    @POST("/api/rides/search")
    Call<ApiResponse<SearchResponse>> searchRide(@Body SearchRideRequest request);
}
