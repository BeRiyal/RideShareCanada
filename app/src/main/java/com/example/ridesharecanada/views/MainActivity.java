package com.example.ridesharecanada.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityMainBinding;
import com.example.ridesharecanada.model.SharedPrefDataSource;
import com.example.ridesharecanada.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mainActivityViewModel;
    ActivityMainBinding binding;
    public static String erromsgs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new MainActivityViewModel(this);
        binding.setMainViewModel(mainActivityViewModel);
        binding.setLifecycleOwner(this);

        FloatingActionButton meetDevs = binding.FabMeetDevs;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            meetDevs.setTooltipText("Meet Developers");
            meetDevs.setOnClickListener(v -> {
                startActivity(new Intent(this,MeetDevelopers.class));
            });
        }
        SharedPrefDataSource.getInstance().init(getApplicationContext());

//_____Check Login
        mainActivityViewModel.CheckSessionvar().observe(this,session -> {
            if (session.equals(true)){
                startActivity(new Intent(this, ChooseActivity.class));
                finish();
            }
        });

/*_____Intent trigger on value change of MutableLiveData of bool variables for intent
       Patten of variables "To" <-Name of activity-> _____*/

        mainActivityViewModel.ToRegistraionActivity().observe(this, navigate -> {
            if (navigate) {
                startActivity(new Intent(this, RegistrationActivity.class));
            }
        });
        mainActivityViewModel.ToForgotPassActivity().observe(this,navigate ->{
            if (navigate){
                startActivity(new Intent(this,ForgotPasswordActivity.class));
            }
        });

//_____Setting ClickListener
        binding.bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidData()){
                    MainActivityViewModel.login(binding.etUsername.getText().toString(), binding.etPassword.getText().toString());
                }
            }
        });
    }

    private boolean isValidData() {
        boolean isValid = true;

        // Validate email format using regex
        String email = binding.etUsername.getText().toString().trim();
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email.isEmpty()) {
            binding.etUsername.setError("Email Address Required");
            binding.etUsername.setFocusable(true);
            isValid = false;
        } else if (!email.matches(emailRegex)) {
            binding.etUsername.setError("Invalid Email Address");
            binding.etUsername.setFocusable(true);
            isValid = false;
        }

        // Validate password
        String password = binding.etPassword.getText().toString().trim();
        if (password.isEmpty()) {
            binding.etPassword.setError("Password is Required");
            binding.etPassword.setFocusable(true);
            isValid = false;
        }


        binding.errorMsg.setText(erromsgs);

        return isValid;
    }
}
