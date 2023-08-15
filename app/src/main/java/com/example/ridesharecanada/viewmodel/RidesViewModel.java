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
        return RideArray;
    }

    MutableLiveData<ApiResponse<SearchResponse>> RideArray;

    static IApiService apiService;
    public RidesViewModel(Context context) {
        this.context = context;
        apiService = RetrofitClient.getInstance().create(IApiService.class);
    }
    public static MutableLiveData<ApiResponse<SearchResponse>> search(String from, String to) {

        MutableLiveData<ApiResponse<SearchResponse>> SearchResponseLiveData = new MutableLiveData<>();

        SearchRideRequest searchRequest = new SearchRideRequest(from,to);
        Call<ApiResponse<SearchResponse>> call = apiService.searchRide(searchRequest);

        call.enqueue(new Callback<ApiResponse<SearchResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<SearchResponse>> call, Response<ApiResponse<SearchResponse>> response) {
                ApiResponse<SearchResponse> apiResponse = response.body();
                if (apiResponse.isSuccess()) {
                    String responseData = apiResponse.getData(); // Assuming apiResponse.getData() returns the JSON string
                    Log.d("Riyal", String.valueOf(responseData));
//                    Gson gson = new Gson();
//                    try {
//                        SearchResponse searchResponse = gson.fromJson( responseData, SearchResponse.class);
//
//                        Log.d("Riyal", String.valueOf(searchResponse));
//                        Log.d("Riyal","Hiiiii");
//                    } catch (JsonSyntaxException e) {
//                        Log.d("Riyal", String.valueOf(e));
//                        e.printStackTrace();
//                    }

                    Gson gson = new Gson();
                    try {
                        Log.d("Riyal", "Response Data: " + responseData); // Debug print
                        Type responseType = new TypeToken<List<SearchResponse.RideData>>() {}.getType();
                        List<SearchResponse.RideData> rideList = gson.fromJson(responseData, responseType);

                        for (SearchResponse.RideData ride : rideList) {
                            Log.d("Riyal", "Ride ID: " + ride.getId());
                            Log.d("Riyal", "From: " + ride.getFrom());
                            Log.d("Riyal", "To: " + ride.getTo());
                            // Print other properties as needed
                        }

                        Log.d("Riyal", "Successfully deserialized");
                    } catch (JsonSyntaxException e) {
                        Log.e("Riyal", "Error during deserialization: " + e.getMessage());
                        e.printStackTrace();
                    }



                }
                else {
                    Log.d("Riyal", "Huuuuuu");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<SearchResponse>> call, Throwable t) {
                Log.d("Riyal","ohoooooooooooooooo");

            }
        });
        return SearchResponseLiveData;
    }

    public void searchTrigger(String searchFrom, String searchTo) {
        Log.d("Riyal",searchFrom);
        Log.d("Riyal",searchTo);
        RideArray = search(searchFrom,searchTo);
        Log.d("Riyal", String.valueOf(RideArray));
    }
}
