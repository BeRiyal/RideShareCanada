package com.example.ridesharecanada.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChooseActivityViewModel extends ViewModel {


    private MutableLiveData<Boolean> ToAddRideActivity = new MutableLiveData<>();
    private MutableLiveData<Boolean> ToSearchActivity = new MutableLiveData<>();

    public void ToAddRideActivity() {
        ToAddRideActivity.setValue(true);
    }

    public void ToSearchRideActivity() {
        ToSearchActivity.setValue(true);
    }

    public MutableLiveData<Boolean> getToSearchActivity() {
        return ToSearchActivity;
    }
    public MutableLiveData<Boolean> getToAddActivity() {
        return ToAddRideActivity;
    }
}
