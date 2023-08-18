package com.example.ridesharecanada.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityRegistrationBinding;
import com.example.ridesharecanada.viewmodel.RegistrationActivityViewModel;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    RegistrationActivityViewModel RegisterViewModel;
    ActivityRegistrationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        RegisterViewModel = new RegistrationActivityViewModel(this);
        binding.setRegisterViewModel(RegisterViewModel);
        binding.setLifecycleOwner(this);

        binding.btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidData()){

                    RegistrationActivityViewModel.register(binding.etName.getText().toString(),binding.etEmail.getText().toString(),binding.etPassword.getText().toString());
                }

            }
        });
        RegisterViewModel.ToLogin().observe(this, navigate ->{
            if (navigate){
                startActivity(new Intent(this,MainActivity.class));
                finish();
            }
        });
    }

    private boolean isValidData() {

            boolean isValid = true;

            // Validate Full Name
            String fullName = binding.etName.getText().toString().trim();
            if (fullName.isEmpty()) {
                binding.etName.setError("Full Name is Required");
                binding.etName.setFocusable(true);
                isValid = false;
            }

            // Validate Email
            String email = binding.etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                binding.etEmail.setError("Email Address is Required");
                binding.etEmail.setFocusable(true);
                isValid = false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmail.setError("Invalid Email Address");
                binding.etEmail.setFocusable(true);
                isValid = false;
            }

            // Validate Password
            String password = binding.etPassword.getText().toString().trim();
            if (password.isEmpty()) {
                binding.etPassword.setError("Password is Required");
                binding.etPassword.setFocusable(true);
                isValid = false;
            }

            // Validate Confirm Password
            String confirmPassword = binding.etConfPassword.getText().toString().trim();
            if (confirmPassword.isEmpty()) {
                binding.etConfPassword.setError("Confirm Password is Required");
                binding.etConfPassword.setFocusable(true);
                isValid = false;
            } else if (!confirmPassword.equals(password)) {
                binding.etConfPassword.setError("Passwords do not match");
                binding.etConfPassword.setFocusable(true);
                isValid = false;
            }

            // Validate Password using regex (at least 8 characters, 1 uppercase letter, 1 digit)
            String passwordRegex = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
            if (!Pattern.compile(passwordRegex).matcher(password).matches()) {
                binding.etPassword.setError("Password must contain at least 8 characters, 1 uppercase letter, and 1 digit");
                binding.etPassword.setFocusable(true);
                isValid = false;
            }

            return isValid;
        }
}