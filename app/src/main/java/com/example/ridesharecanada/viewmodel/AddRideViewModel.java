package com.example.ridesharecanada.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ridesharecanada.APIServices.IApiService;
import com.example.ridesharecanada.APIServices.RetrofitClient;
import com.example.ridesharecanada.model.API.AddRideRequest;
import com.example.ridesharecanada.model.API.ApiResponse;
import com.example.ridesharecanada.model.API.LoginResponse;
import com.example.ridesharecanada.model.API.RegisterResponse;
import com.example.ridesharecanada.model.SharedPrefDataSource;

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

    public static MutableLiveData<Boolean> addRide(String From, String TO, String Date, String Time, String Seats) {

        MutableLiveData<ApiResponse<LoginResponse>> AddRideResponseLiveData = new MutableLiveData<>();

        AddRideRequest request = new AddRideRequest(From, TO, Date, Time, Seats, SharedPrefDataSource.getInstance().getLoginId());
        Call<ApiResponse<RegisterResponse>> call = apiService.addRide(request);
        call.enqueue(new Callback<ApiResponse<RegisterResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<RegisterResponse>> call, Response<ApiResponse<RegisterResponse>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<RegisterResponse> apiResponse = response.body();
                    if (apiResponse.isSuccess()) {
                        RegisterResponse registerResponse = apiResponse.getData();
                        SharedPrefDataSource.getInstance().setLoginId(registerResponse.getToken());
                        Log.d("Riyal","Hiiiii");
                    }
                } else {
                    Log.d("Riyal","Huuuuuu");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<RegisterResponse>> call, Throwable t) {

            }
        });

        return null;

    }
    private static MutableLiveData<Boolean> ToMainActivity = new MutableLiveData<>();

    public void CheckSession() {
        ToMainActivity.setValue(SharedPrefDataSource.getInstance().getLoginId() == null); // Trigger navigation
    }
}
