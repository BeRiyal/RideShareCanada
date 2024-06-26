package com.example.ridesharecanada.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ridesharecanada.APIServices.IApiService;
import com.example.ridesharecanada.APIServices.RetrofitClient;
import com.example.ridesharecanada.model.API.ApiResponse;
import com.example.ridesharecanada.model.API.LoginRequest;
import com.example.ridesharecanada.model.API.LoginResponse;
import com.example.ridesharecanada.model.SharedPrefDataSource;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivityViewModel extends ViewModel {
    private static IApiService apiService;

    private MutableLiveData<Boolean> ToRegistrationActivity = new MutableLiveData<>();
    private MutableLiveData<Boolean> ToForgotPassActivity = new MutableLiveData<>();
    private static MutableLiveData<Boolean> ToSearchActivity = new MutableLiveData<>();

    public static MutableLiveData<String> getWrongCredential() {
        return WrongCredential; // for credential error
    }

    private static MutableLiveData<String> WrongCredential = new MutableLiveData<>();
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
                        String responseData = apiResponse.getData(); // Assuming apiResponse.getData() returns the JSON string

                        Gson gson = new Gson();
                        try {
                            LoginResponse loginResponse = gson.fromJson(responseData, LoginResponse.class);
                            SharedPrefDataSource.getInstance().setLoginId(loginResponse.getToken());
                            ToSearchActivity.setValue(true);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            ToSearchActivity.setValue(false);
                        }
                    } else {

                        WrongCredential.setValue("Hello");
                        ToSearchActivity.setValue(false);
                    }
                }
                else {
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
        ToRegistrationActivity.setValue(true); // Trigger navigation to register
    }
    public void ToForgotPass() {
        ToForgotPassActivity.setValue(true); // Trigger navigation to forgetpassword page
    }
    public void CheckSession() {
        ToSearchActivity.setValue(SharedPrefDataSource.getInstance().getLoginId() != null); // Check for session
    }
}