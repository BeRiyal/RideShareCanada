package com.example.ridesharecanada.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityChooseBinding;
import com.example.ridesharecanada.viewmodel.ChooseActivityViewModel;

public class ChooseActivity extends AppCompatActivity {
        ChooseActivityViewModel chooseActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChooseBinding activityChooseBinding = DataBindingUtil.setContentView(this, R.layout.activity_choose);
        chooseActivityViewModel = new ChooseActivityViewModel();
        activityChooseBinding.setChooseViewModel(chooseActivityViewModel);

        chooseActivityViewModel.getToSearchActivity().observe(this,navigate ->{
            startActivity(new Intent(this,SearchRideActivity.class));
        });
        chooseActivityViewModel.getToAddActivity().observe(this,navigate ->{
            startActivity(new Intent(this, AddRideActivity.class));
        });

    }
}