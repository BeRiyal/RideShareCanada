package com.example.ridesharecanada.APIServices;

import com.example.ridesharecanada.model.API.ApiResponse;
import com.example.ridesharecanada.APIServices.LoginResponseDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://ridesharecanadaapi.onrender.com";
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();


            Gson gson = new GsonBuilder()
                    .setLenient()
                    .registerTypeAdapter(ApiResponse.class, new LoginResponseDeserializer())
                    .create();



            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)  // Set the custom OkHttpClient with the logging interceptor
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}
