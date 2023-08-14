package com.example.ridesharecanada.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ridesharecanada.APIServices.IApiService;
import com.example.ridesharecanada.APIServices.RetrofitClient;
import com.example.ridesharecanada.model.API.ApiResponse;
import com.example.ridesharecanada.model.LoginResponse;
import com.example.ridesharecanada.model.LoginRequest;
import com.example.ridesharecanada.model.SharedPrefDataSource;
import com.example.ridesharecanada.views.SearchRideActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    private static IApiService apiService;


    private MutableLiveData<Boolean> ToRegistrationActivity = new MutableLiveData<>();
    private MutableLiveData<Boolean> ToForgotPassActivity = new MutableLiveData<>();
    private static MutableLiveData<Boolean> ToSearchActivity = new MutableLiveData<>();
    private Context context;

    public MainActivityViewModel(Context applicationContext) {
        this.context = applicationContext;
        apiService = RetrofitClient.getInstance().create(IApiService.class);
        CheckSession();
    }

        public static MutableLiveData<Boolean> login(String username, String password) {

        MutableLiveData<ApiResponse<LoginResponse>> loginResponseLiveData = new MutableLiveData<>();

        LoginRequest loginRequest = new LoginRequest(username, password);
        Call<ApiResponse<LoginResponse>> call = apiService.login(loginRequest);
        call.enqueue(new Callback<ApiResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<LoginResponse>> call, Response<ApiResponse<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<LoginResponse> apiResponse = response.body();
                    if (apiResponse.isSuccess()) {
                        LoginResponse loginResponse = apiResponse.getData();
                        SharedPrefDataSource.getInstance().setLoginId(loginResponse.getToken());
//                        String loginId = SharedPrefDataSource.getInstance().getLoginId();
                        ToSearchActivity.setValue(true);
                    }
                } else {
                    ToSearchActivity.setValue(false);
                }
            }
            @Override
            public void onFailure(Call<ApiResponse<LoginResponse>> call, Throwable t) {
                if (call.isExecuted() && call.request().body() != null) {
                    try {
                        ToSearchActivity.setValue(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return ToSearchActivity;
    }

    public LiveData<Boolean> ToRegistraionActivity() {
        return ToRegistrationActivity;
    }
    public LiveData<Boolean> ToForgotPassActivity(){
        return ToForgotPassActivity;
    }

    public LiveData<Boolean> CheckSessionvar() {
        return ToSearchActivity;
    }
    public void ToRegistration() {
        ToRegistrationActivity.setValue(true); // Trigger navigation
    }
    public void ToForgotPass() {
        ToForgotPassActivity.setValue(true); // Trigger navigation
    }

    public void CheckSession() {
        ToSearchActivity.setValue(SharedPrefDataSource.getInstance().getLoginId() != null); // Trigger navigation
    }
}