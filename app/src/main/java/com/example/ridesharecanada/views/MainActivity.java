package com.example.ridesharecanada.views;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityMainBinding;
import com.example.ridesharecanada.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mainActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new MainActivityViewModel(this);
        binding.setMainViewModel(mainActivityViewModel);
        binding.setLifecycleOwner(this);

        mainActivityViewModel.getNavigateToOtherActivity().observe(this, navigate -> {
            if (navigate) {
                startActivity(new Intent(this, RegistrationActivity.class));
            }
        });

    }
}