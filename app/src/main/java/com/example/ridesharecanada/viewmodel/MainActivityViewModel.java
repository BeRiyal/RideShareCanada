package com.example.ridesharecanada.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Boolean> navigateToOtherActivity = new MutableLiveData<>();

    private Context context;

    public MainActivityViewModel(Context applicationContext) {
        this.context = applicationContext;
    }


    public LiveData<Boolean> getNavigateToOtherActivity() {
        return navigateToOtherActivity;
    }
    public void ToRegistration() {
        // Perform some business logic
        navigateToOtherActivity.setValue(true); // Trigger navigation
    }
}