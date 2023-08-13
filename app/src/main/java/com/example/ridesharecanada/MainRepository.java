package com.example.ridesharecanada;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class MainRepository {

        private static final String TAG = "MainRepository"; // Define a TAG for logging

        public MainRepository() {
        }

        public void loginRemote(LoginBody loginBody, ILoginResponse loginResponse){
            ILoginService loginService = RetrofitClientInstance.getInstance().create(ILoginService.class);
            Call<LoginResponse> initiateLogin = loginService.login(loginBody);

            Log.d("Riyal", "Attempting login...");
            Log.d("Riyal", String.valueOf(loginBody.getPassword()));
            Log.d("Riyal", String.valueOf(loginResponse));

            initiateLogin.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Log.d("Riyal", String.valueOf(response));
                    if (response.isSuccessful()){
                        loginResponse.onResponse(response.body());
                    } else {
                        loginResponse.onFailure(new Throwable(response.message()));
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    loginResponse.onFailure(t);
                }
            });

        }

        public interface ILoginResponse{
            void onResponse(LoginResponse loginResponse);
            void onFailure(Throwable t);
        }
}
