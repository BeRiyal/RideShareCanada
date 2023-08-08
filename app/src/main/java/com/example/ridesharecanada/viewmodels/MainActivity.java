package com.example.ridesharecanada.viewmodels;

import android.content.Context;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ridesharecanada.R;

public class MainActivity extends ViewModel {
    private final Context context;
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginResult = new MutableLiveData<>();

    public MainActivity(Context context) {
        this.context = context;
    }

    public void onLoginButtonClick() {
        Toast.makeText(context, "ferferferf", Toast.LENGTH_LONG).show();
    }
}