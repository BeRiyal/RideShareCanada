package com.example.ridesharecanada.views;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


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
        mainActivityViewModel.IfloginVar().observe(this,Success ->{
            Log.d("Riyal", String.valueOf(Success));
            if (Success){
                startActivity(new Intent(this, RegistrationActivity.class));
            }
            else {
                Toast.makeText(this, "Errorrrrrrrr", Toast.LENGTH_LONG).show();
            }
        });
        binding.bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityViewModel.login(binding.etUsername.getText().toString(), binding.etPassword.getText().toString());
            }
        });
    }
}
