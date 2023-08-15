package com.example.ridesharecanada.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivitySearchRideBinding;
import com.example.ridesharecanada.model.SharedPrefDataSource;
import com.example.ridesharecanada.viewmodel.RidesViewModel;

public class SearchRideActivity extends AppCompatActivity {

    ActivitySearchRideBinding binding;
    RidesViewModel searchRideViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySearchRideBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search_ride);

        binding.btSearch.setOnClickListener(v -> {
            SharedPrefDataSource.getInstance().saveSearchQuery(binding.etFrom.getText().toString(),binding.etTo.getText().toString());
            startActivity(new Intent(this,RidesActivity.class));
        });
    }
}