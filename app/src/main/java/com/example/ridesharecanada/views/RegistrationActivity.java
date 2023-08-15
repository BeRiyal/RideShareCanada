package com.example.ridesharecanada.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityRegistrationBinding;
import com.example.ridesharecanada.viewmodel.RegistrationActivityViewModel;

public class RegistrationActivity extends AppCompatActivity {

    RegistrationActivityViewModel RegisterViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegistrationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        RegisterViewModel = new RegistrationActivityViewModel(this);
        binding.setRegisterViewModel(RegisterViewModel);
        binding.setLifecycleOwner(this);

        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrationActivityViewModel.register(binding.etName.getText().toString(),binding.etEmail.getText().toString(),binding.etPassword.getText().toString());

            }
        });

        RegisterViewModel.ToLogin().observe(this, navigate ->{
            if (navigate){
                startActivity(new Intent(this,MainActivity.class));
                finish();
            }
        });
    }
}