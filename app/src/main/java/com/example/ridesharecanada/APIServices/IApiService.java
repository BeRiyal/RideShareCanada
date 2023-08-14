package com.example.ridesharecanada.APIServices;

import com.example.ridesharecanada.model.API.ApiResponse;
import com.example.ridesharecanada.model.API.LoginRequest;
import com.example.ridesharecanada.model.API.LoginResponse;
import com.example.ridesharecanada.model.API.RegisterRequest;
import com.example.ridesharecanada.model.API.RegisterResponse;

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
}
