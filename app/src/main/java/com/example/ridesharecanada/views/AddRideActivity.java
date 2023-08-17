package com.example.ridesharecanada.views;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityAddRideBinding;
import com.example.ridesharecanada.model.SharedPrefDataSource;
import com.example.ridesharecanada.viewmodel.AddRideViewModel;

public class AddRideActivity extends AppCompatActivity {

    ActivityAddRideBinding binding;
    AddRideViewModel addRideViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_ride);
        addRideViewModel = new AddRideViewModel(this);
        binding.setAddRideViewModel(addRideViewModel);


        binding.btnAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRideViewModel.addRide(SharedPrefDataSource.getInstance().getLoginId(),binding.etFrom.getText().toString(),binding.etTo.getText().toString(),binding.etDate.getText().toString(),binding.etTime.getText().toString(),binding.etSeat.getText().toString());
            }
        });

    }
}