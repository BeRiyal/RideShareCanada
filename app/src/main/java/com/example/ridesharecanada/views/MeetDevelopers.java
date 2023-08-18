package com.example.ridesharecanada.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityMeetDevelopersBinding;
import com.example.ridesharecanada.viewmodel.MeetDevsViewModel;

public class MeetDevelopers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActivityMeetDevelopersBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_meet_developers);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_developers);
        MeetDevsViewModel meetDevsViewModel = new MeetDevsViewModel();
        binding.setMeetDevsViewModel(meetDevsViewModel);
    }
}