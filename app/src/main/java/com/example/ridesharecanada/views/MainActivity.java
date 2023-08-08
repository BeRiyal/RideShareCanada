package com.example.ridesharecanada.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityMainBinding;
import com.example.ridesharecanada.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = new MainActivityViewModel(this);

        activityMainBinding.setMainviewModel(mainActivityViewModel);
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.executePendingBindings();


        mainActivityViewModel.getNavigateToOtherActivity().observe(this, navigate -> {
            if (navigate) {
                startActivity(new Intent(this, RegistrationActivity.class));
            }
        });
    }
}