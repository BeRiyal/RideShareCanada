package com.example.ridesharecanada.APIServices;

import com.example.ridesharecanada.model.API.ApiResponse;
import com.example.ridesharecanada.model.LoginRequest;
import com.example.ridesharecanada.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IApiService {
//    @POST("/api/users/login")
//    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/api/users/login")
    Call<ApiResponse<LoginResponse>> login(@Body LoginRequest request);

}
