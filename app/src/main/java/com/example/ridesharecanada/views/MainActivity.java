package com.example.ridesharecanada.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityMainBinding;
import com.example.ridesharecanada.model.SharedPrefDataSource;
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
                MainActivityViewModel.login(binding.etUsername.getText().toString(), binding.etPassword.getText().toString());
            }
        });
    }
}
