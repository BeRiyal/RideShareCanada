package com.example.ridesharecanada.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ridesharecanada.IApiService;
import com.example.ridesharecanada.model.LoginResponse;
import com.example.ridesharecanada.model.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    private static IApiService apiService;
    private MutableLiveData<Boolean> navigateToOtherActivity = new MutableLiveData<>();
    private static MutableLiveData<Boolean> IfLogin = new MutableLiveData<>();
    private Context context;

    public MainActivityViewModel(Context applicationContext) {
        this.context = applicationContext;
        apiService = RetrofitClient.getInstance().create(IApiService.class);
    }

    public static MutableLiveData<Boolean> login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);

        Call<LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    String responseBody = String.valueOf(response.code());
                        IfLogin.setValue(true);
                }else {
                    IfLogin.setValue(false);
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                IfLogin.setValue(false);
                //TODO: Handle failure
            }

            });
        return IfLogin;
    }


    public LiveData<Boolean> getNavigateToOtherActivity() {
        return navigateToOtherActivity;
    }

    public LiveData<Boolean> IfloginVar() {
        return IfLogin;
    }
    public void ToRegistration() {
        navigateToOtherActivity.setValue(true); // Trigger navigation
    }
}