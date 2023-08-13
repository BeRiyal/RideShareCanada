package com.example.ridesharecanada;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if (retrofit == null){
            return retrofit = new Retrofit.Builder()
                    .baseUrl("https://ridesharecanadaapi.onrender.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

