package com.example.ridesharecanada.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ridesharecanada.LoginBody;
import com.example.ridesharecanada.LoginResponse;
import com.example.ridesharecanada.MainRepository;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Boolean> navigateToOtherActivity = new MutableLiveData<>();
    private MutableLiveData<String> loginResponse = new MutableLiveData<>();
    private static MutableLiveData<Boolean> IfLogin = new MutableLiveData<>();
    static MainRepository mainRepository;
    private Context context;

    public MainActivityViewModel(Context applicationContext) {
        this.context = applicationContext;
        mainRepository = new MainRepository();
    }

    public static void login(String username, String password){
        mainRepository.loginRemote(new LoginBody(username, password), new MainRepository.ILoginResponse() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                IfLogin.setValue(true);
            }

            @Override
            public void onFailure(Throwable t) {
                IfLogin.setValue(false);
            }
        });
    }


    public LiveData<Boolean> getNavigateToOtherActivity() {
        return navigateToOtherActivity;
    }

    public LiveData<Boolean> IfloginVar() {
        return IfLogin;
    }
    public void ToRegistration() {
        // Perform some business logic
        navigateToOtherActivity.setValue(true); // Trigger navigation
    }
}