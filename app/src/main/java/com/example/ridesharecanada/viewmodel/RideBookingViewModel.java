package com.example.ridesharecanada.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ridesharecanada.APIServices.DirectionsService;
import com.example.ridesharecanada.APIServices.RetrofitMapClient;
import com.example.ridesharecanada.model.API.DirectionsResponse;
import com.google.android.gms.maps.GoogleMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RideBookingViewModel extends ViewModel{

    private GoogleMap googleMap;
    private OkHttpClient client = new OkHttpClient();
    static DirectionsService directionService;

    public static MutableLiveData<DirectionsResponse> getMapApiResponse() {
        Log.d("in get map api", String.valueOf(MapApiResponse));
        return MapApiResponse;
    }

    public static void setMapApiResponse(MutableLiveData<DirectionsResponse> mapApiResponse) {
        MapApiResponse = mapApiResponse;
    }

    static MutableLiveData<DirectionsResponse> MapApiResponse;
    Context context;
    public RideBookingViewModel(Context context) {
        this.context = context;
        directionService = RetrofitMapClient.getInstance().create(DirectionsService.class);
    }
    public static MutableLiveData<DirectionsResponse> LoadMap(String origin,String destination) {

        MapApiResponse = new MutableLiveData<>();

        String apiKey = "AIzaSyBFcykIv0WwZmbt3WBW96FaSAee8OwSKxw";
        Call<DirectionsResponse> call = directionService.getDirections(origin,destination,apiKey);
        Log.d("Riyal before call", String.valueOf(call));

        call.enqueue(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                DirectionsResponse apiResponse = response.body();
                Log.d("Riyal OnResponse", String.valueOf(apiResponse));
                Log.d("Riyal OnResponse", String.valueOf(apiResponse));

                MapApiResponse.setValue(apiResponse);
                //    MapApiResponse.postValue(apiResponse);
                Log.d("Riyal OnResponse after post value", String.valueOf(apiResponse));
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                Log.d("Riyal onFailure",t.getMessage());
            }


        });


        Log.d("Riyal return from  with value", String.valueOf(MapApiResponse));

        return MapApiResponse;
    }

}
