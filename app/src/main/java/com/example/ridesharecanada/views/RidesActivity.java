package com.example.ridesharecanada.views;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityRidesBinding;
import com.example.ridesharecanada.model.SharedPrefDataSource;
import com.example.ridesharecanada.viewmodel.RidesViewModel;

public class RidesActivity extends AppCompatActivity {


    ActivityRidesBinding binding;
    RidesViewModel ridesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rides);
        ridesViewModel = new RidesViewModel(this);
        binding.setRidesViewModel(ridesViewModel);
        binding.setLifecycleOwner(this);

        ridesViewModel.searchTrigger(SharedPrefDataSource.getInstance().getSearchFrom(), SharedPrefDataSource.getInstance().getSearchTo());

        ridesViewModel.getRideArray().observe(this,array -> {

            Log.d("Riyal", "String.valueOf(Arrays.asList(array).indexOf(0)");
        });


    }
}