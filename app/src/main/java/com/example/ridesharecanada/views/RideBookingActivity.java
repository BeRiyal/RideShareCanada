package com.example.ridesharecanada.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.databinding.ActivityRideBookingBinding;
import com.example.ridesharecanada.model.API.DirectionsResponse;
import com.example.ridesharecanada.model.API.PolylineUtils;
import com.example.ridesharecanada.model.API.Route;
import com.example.ridesharecanada.model.API.Step;
import com.example.ridesharecanada.model.SharedPrefDataSource;
import com.example.ridesharecanada.viewmodel.RideBookingViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class RideBookingActivity extends AppCompatActivity  implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private OkHttpClient client = new OkHttpClient();
    private Polyline directionsPolyline;
    RideBookingViewModel rideBookingViewModel;
    ActivityRideBookingBinding binding;
    MapView mapView; // Add this

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ride_booking);

        rideBookingViewModel = new RideBookingViewModel(this);
        binding.setRidesBookingViewModel(rideBookingViewModel);

        mapView = findViewById(R.id.mapView); // Initialize the MapView
        Log.d("Riyal map view find", String.valueOf(mapView));

        RideBookingViewModel.LoadMap(SharedPrefDataSource.getInstance().getSearchFrom(), SharedPrefDataSource.getInstance().getSearchTo()).observe(this, directionsResponse -> {
            drawDirectionsPolyline(directionsResponse);
          Log.d("Riyal after map view find", String.valueOf(directionsResponse));
        });

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this); // This will trigger onMapReady when the map is ready

//        Load Data From Intent
        LoadDataFromIntent();

    }

    private void LoadDataFromIntent() {
        Intent i = getIntent();
        binding.tvFrom.setText(i.getExtras().get("From").toString());
        binding.tvTo.setText(i.getExtras().get("To").toString());
        binding.tvDate.setText(i.getExtras().get("Date").toString());
        binding.tvTime.setText(i.getExtras().get("StartTime").toString());
        binding.tvDriver.setText(i.getExtras().get("Driver").toString());
        binding.tvSeats.setText(i.getExtras().get("Seats").toString());
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
       // drawDirectionsPolyline();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    private void drawDirectionsPolyline( DirectionsResponse directionsResponse) {

        if (directionsResponse != null && directionsResponse.getRoutes() != null && !directionsResponse.getRoutes().isEmpty()) {
            Log.d("Riyal drawDirectionsPolyline", "DirectionsResponse is not null");
            Log.d("Riyal drawDirectionsPolyline", directionsResponse.getRoutes().toString() );
            Route firstRoute = directionsResponse.getRoutes().get(0); // Assuming there's at least one route
            Log.d("Riyal drawDirectionsPolyline", String.valueOf(firstRoute));

            List<LatLng> polylinePoints = new ArrayList<>();

            for (Step step : firstRoute.getSteps()) {
                String encodedPolyline = step.getPolyline().getPoints();
                List<LatLng> decodedPolyline = PolylineUtils.decodePolyline(encodedPolyline);
                polylinePoints.addAll(decodedPolyline);
            }

            if (!polylinePoints.isEmpty()) {
                Log.d("Riyal drawDirectionsPolyline", "Polyline points count: " + polylinePoints.size());

                PolylineOptions polylineOptions = new PolylineOptions()
                        .addAll(polylinePoints)
                        .width(5)
                        .color(Color.BLUE);

                directionsPolyline = googleMap.addPolyline(polylineOptions);
                Log.d("Riyal directionsPolyline", String.valueOf(directionsPolyline));

                LatLng startLocation = polylinePoints.get(0);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 15));
            } else {
                Log.d("Riyal drawDirectionsPolyline", "Polyline points are empty");
            }
        } else {
            Log.d("Riyal drawDirectionsPolyline", "DirectionsResponse is null or empty");
        }
    }


}