package com.example.ridesharecanada.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ridesharecanada.APIServices.IApiService;
import com.example.ridesharecanada.APIServices.RetrofitClient;
import com.example.ridesharecanada.model.API.ApiResponse;
import com.example.ridesharecanada.model.API.SearchResponse;
import com.example.ridesharecanada.model.API.SearchRideRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RidesViewModel extends ViewModel {
    Context context;

    public MutableLiveData<ApiResponse<SearchResponse>> getRideArray() {
        Log.d("Riyal","Into GetRideArray");
        Log.d("Riyal", RideArray.toString());
        return RideArray;
    }

    public void setRideArray(MutableLiveData<ApiResponse<SearchResponse>> rideArray) {
        this.RideArray = rideArray;
    }

    public static MutableLiveData<ApiResponse<SearchResponse>> RideArray;

    static IApiService apiService;
    public RidesViewModel(Context context) {
        this.context = context;
        apiService = RetrofitClient.getInstance().create(IApiService.class);
    }
    public static MutableLiveData<ApiResponse<SearchResponse>> search(String from, String to) {

        RideArray = new MutableLiveData<>();

        SearchRideRequest searchRequest = new SearchRideRequest(from,to);
        Call<ApiResponse<SearchResponse>> call = apiService.searchRide(searchRequest);

        call.enqueue(new Callback<ApiResponse<SearchResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<SearchResponse>> call, Response<ApiResponse<SearchResponse>> response) {
                ApiResponse<SearchResponse> apiResponse = response.body();
                if (apiResponse.isSuccess()) {
                    String responseData = apiResponse.getData(); // Assuming apiResponse.getData() returns the JSON string
                    Log.d("Riyal", String.valueOf(responseData));
                    Gson gson = new Gson();
                    try {
                        Log.d("Riyal", "Response Data: " + responseData); // Debug print
                        Type responseType = new TypeToken<List<SearchResponse.RideData>>() {}.getType();
                        List<SearchResponse.RideData> rideList = gson.fromJson(responseData, responseType);
                        for (SearchResponse.RideData ride : rideList) {
                            Log.d("Riyal", "Ride ID: " + ride.getId());
                            Log.d("Riyal", "From: " + ride.getFrom());
                            Log.d("Riyal", "To: " + ride.getTo());
                        }
                        RideArray.postValue(apiResponse);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                }
                else {
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<SearchResponse>> call, Throwable t) {
            }
        });
        return RideArray;
    }

    public void searchTrigger(String searchFrom, String searchTo) {
        RideArray = search(searchFrom,searchTo);
        Log.d("Riyal",RideArray.toString());
    }
}
