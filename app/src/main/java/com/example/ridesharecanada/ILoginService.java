package com.example.ridesharecanada;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginService {
    @POST("login/")
    Call<LoginResponse> login(@Body LoginBody user);
}
