package com.example.ridesharecanada.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ridesharecanada.APIServices.IApiService;
import com.example.ridesharecanada.APIServices.RetrofitClient;
import com.example.ridesharecanada.model.API.AddRideRequest;
import com.example.ridesharecanada.model.API.AddRideResponse;
import com.example.ridesharecanada.model.API.ApiResponse;
import com.example.ridesharecanada.model.SharedPrefDataSource;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRideViewModel extends ViewModel {

    private final Context context;
    static IApiService apiService;
    public AddRideViewModel(Context context) {
        this.context = context;
        apiService = RetrofitClient.getInstance().create(IApiService.class);
        CheckSession();
    }

    public static MutableLiveData<Boolean> addRide(String Did,String From, String TO, String Date, String Time, String Seats) {
        AddRideRequest request = new AddRideRequest(Did,From, TO, Date, Time, Seats);
        Call<ApiResponse<AddRideResponse>> call = apiService.addRide(request);
        call.enqueue(new Callback<ApiResponse<AddRideResponse>>() {

            @Override
            public void onResponse(Call<ApiResponse<AddRideResponse>> call, Response<ApiResponse<AddRideResponse>> response) {
                Log.d("Riyal", String.valueOf(response));
                if (response.isSuccessful()) {
                    ApiResponse<AddRideResponse> apiResponse = response.body();
                    Log.d("Riyal", String.valueOf(apiResponse));
                    if (apiResponse.isSuccess()) {
                        String responseData = apiResponse.getData(); // Assuming apiResponse.getData() returns the JSON string
                        Log.d("Riyal", String.valueOf(responseData));
                        Gson gson = new Gson();

                        try {

                            AddRideResponse loginResponse = gson.fromJson( responseData, AddRideResponse.class);

                            Log.d("Riyal", String.valueOf(loginResponse));
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            Log.d("Riyal", "Hi catch");
                        }



                    }
                } else {
                    Log.d("Riyal","Huuuuuu");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<AddRideResponse>> call, Throwable t) {
                Log.d("Riyal", "Hi fail");
            }
        });

        return null;

    }
    private static MutableLiveData<Boolean> ToMainActivity = new MutableLiveData<>();

    public void CheckSession() {
        ToMainActivity.setValue(SharedPrefDataSource.getInstance().getLoginId() == null); // Trigger navigation
    }
}
