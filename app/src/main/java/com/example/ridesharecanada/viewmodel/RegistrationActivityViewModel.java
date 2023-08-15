package com.example.ridesharecanada.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ridesharecanada.APIServices.IApiService;
import com.example.ridesharecanada.APIServices.RetrofitClient;
import com.example.ridesharecanada.model.API.ApiResponse;
import com.example.ridesharecanada.model.API.LoginResponse;
import com.example.ridesharecanada.model.API.RegisterRequest;
import com.example.ridesharecanada.model.API.RegisterResponse;
import com.example.ridesharecanada.model.SharedPrefDataSource;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivityViewModel extends ViewModel {
    private static IApiService apiService;
    private Context context;
    private static MutableLiveData<Boolean> ToSearchActivity = new MutableLiveData<>();
    private static MutableLiveData<Boolean> ToLoginActivity = new MutableLiveData<>();

    public RegistrationActivityViewModel(Context context){
        this.context = context;
        apiService = RetrofitClient.getInstance().create(IApiService.class);
        CheckSession();
    }
    public static MutableLiveData<Boolean> register(String username, String email, String password) {
        MutableLiveData<ApiResponse<LoginResponse>> registerResponseLiveData = new MutableLiveData<>();

        RegisterRequest request = new RegisterRequest(username,email,password);

        Call<ApiResponse<RegisterResponse>> call = apiService.register(request);
        call.enqueue(new Callback<ApiResponse<RegisterResponse>>() {
            @Override
            public void onResponse(Call<ApiResponse<RegisterResponse>> call, Response<ApiResponse<RegisterResponse>> response) {
                if (response.isSuccessful()) {
                    ApiResponse<RegisterResponse> apiResponse = response.body();

                    if (apiResponse.isSuccess()) {
                       String responseData = apiResponse.getData(); // Assuming apiResponse.getData() returns the JSON string
                        Gson gson = new Gson();
                        try {
                            RegisterResponse registerResponse = gson.fromJson( responseData, RegisterResponse.class);

                            Log.d("Riyal", String.valueOf(registerResponse));
                            SharedPrefDataSource.getInstance().setLoginId(registerResponse.getToken());
                            Log.d("Riyal","Hiiiii");
                            ToLoginActivity.setValue(true);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            ToLoginActivity.setValue(false);
                        }

                    }
                } else {
                    Log.d("Riyal","Huuuuuu");
                    ToLoginActivity.setValue(false);
                }


            }

            @Override
            public void onFailure(Call<ApiResponse<RegisterResponse>> call, Throwable t) {
                Log.d("Riyal","huhhhhh");
                ToLoginActivity.setValue(false);
            }
        });
        return ToLoginActivity;
    }
        public void CheckSession() {
        ToSearchActivity.setValue(SharedPrefDataSource.getInstance().getLoginId() != null); // Trigger navigation
    }

    public LiveData<Boolean> ToLogin(){
        return ToLoginActivity ;
    }

}
