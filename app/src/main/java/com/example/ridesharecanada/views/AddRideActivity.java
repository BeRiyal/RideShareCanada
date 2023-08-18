package com.example.ridesharecanada.views;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityAddRideBinding;
import com.example.ridesharecanada.model.SharedPrefDataSource;
import com.example.ridesharecanada.viewmodel.AddRideViewModel;

import java.util.Calendar;
import java.util.Locale;

public class AddRideActivity extends AppCompatActivity {

    ActivityAddRideBinding binding;
    AddRideViewModel addRideViewModel;

    // Validate user input data
    private boolean isValidData() {
        Boolean vallidator = true;
        // Validate From location
        if (binding.etFrom.getText().toString().trim().isEmpty()) {
            binding.etFrom.setError("From location is required");
            binding.etFrom.requestFocus();
            vallidator = false;
        }

        // Validate To location
        if (binding.etTo.getText().toString().trim().isEmpty()) {
            binding.etTo.setError("To location is required");
            binding.etTo.requestFocus();
            vallidator = false;
        }

        // Validate Date
        if (binding.etDate.getText().toString().trim().isEmpty()) {
            binding.etDate.setError("Date is required");
            binding.etDate.requestFocus();
            vallidator = false;
        }

        // Validate Time
        if (binding.etTime.getText().toString().trim().isEmpty()) {
            binding.etTime.setError("Time is required");
            binding.etTime.requestFocus();
            vallidator = false;
        }

        // Validate Available Seats
        if (binding.etSeat.getText().toString().trim().isEmpty()) {
            binding.etSeat.setError("Available seats count is required");
            binding.etSeat.requestFocus();
            vallidator = false;
        }

        // Validate Available Seats
        String seats = binding.etSeat.getText().toString().trim();
        if (seats.isEmpty()) {
            binding.etSeat.setError("Available seats count is required");
            binding.etSeat.requestFocus();
            vallidator = false;
        } else if (!TextUtils.isDigitsOnly(seats)) {
            binding.etSeat.setError("Please enter a valid number");
            binding.etSeat.requestFocus();
            vallidator = false;
        }
        return vallidator;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_ride);
        addRideViewModel = new AddRideViewModel(this);
        binding.setAddRideViewModel(addRideViewModel);

        binding.etDate.setOnClickListener(v -> showDatePickerDialog());
        binding.etTime.setOnClickListener(v->showTimePickerDialog());

        binding.btnAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidData()){
                    AddRideViewModel.addRide(SharedPrefDataSource.getInstance().getLoginId(),binding.etFrom.getText().toString(),binding.etTo.getText().toString(),binding.etDate.getText().toString(),binding.etTime.getText().toString(),binding.etSeat.getText().toString());
                }
            }
        });

    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    // Get the current date
                    Calendar selectedCalendar = Calendar.getInstance();
                    selectedCalendar.set(year, monthOfYear, dayOfMonth);

                    if (selectedCalendar.before(calendar)) {
                        // Selected date is before current date, show error
                        binding.etDate.setError("Please select a future date");
                    } else {
                        // Format the selected date
                        String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
                        binding.etDate.setText(selectedDate);
                        binding.etDate.setError(null); // Clear any previous error
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    // Format the selected time
                    String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                    binding.etTime.setText(selectedTime);
                    binding.etTime.setError(null); // Clear any previous error
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true); // true indicates 24-hour format

        timePickerDialog.show();
    }




}