package com.example.ridesharecanada.views;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ridesharecanada.R;
import com.example.ridesharecanada.model.API.SearchResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class RideAdapter extends RecyclerView.Adapter <RideAdapter.RideViewHolder> {

    private  List<SearchResponse.RideData> rideDataList;

    @Override
    public RideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ride, parent, false);
        return new RideViewHolder(itemView, new onItemClickListener() {
            @Override
            public void onRecyclerItemClick(View view, int position) {
                Intent i = new Intent(view.getContext(),RideBookingActivity.class);
                i.putExtra("From", rideDataList.get(position).getFrom());
                i.putExtra("To", rideDataList.get(position).getTo());
                i.putExtra("Date", rideDataList.get(position).getDate());
                i.putExtra("Seats", rideDataList.get(position).getAvailableSeats());
                i.putExtra("StartTime", rideDataList.get(position).getStartTime());
                i.putExtra("Driver", rideDataList.get(position).getDriverId());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull RideAdapter.RideViewHolder holder, int position) {
        SearchResponse.RideData ride = rideDataList.get(position);
        holder.bind(ride);
    }

    @Override
    public int getItemCount() {
        if(rideDataList==null){
            Log.d("riyal","empty Array");
            return 0;
        }
        else {
            Log.d("riyal", String.valueOf(rideDataList.size()));
            return rideDataList.size();
        }
    }
    public void setItems(String rides) {
        Gson gson = new Gson();
        Type responseType = new TypeToken<List<SearchResponse.RideData>>() {}.getType();
        List<SearchResponse.RideData> rideList = gson.fromJson(rides, responseType);
        for (SearchResponse.RideData ride : rideList) {
            Log.d("Riyal", "Ride ID: " + ride.getId());
            Log.d("Riyal", "From: " + ride.getFrom());
            Log.d("Riyal", "To: " + ride.getTo());
            Log.d("Riyal", "Driver: " + ride.getDriverId());
        }

        this.rideDataList  = rideList;

        notifyDataSetChanged();
    }
    public class RideViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public onItemClickListener mListener;

        private TextView rideFromTextView,rideToTextView,rideDateTextView,rideTimeTextView,rideDriverTextView,rideSeatsTextView;
        public RideViewHolder(@NonNull View rideView, onItemClickListener listener) {
            super(rideView);
            rideFromTextView =rideView.findViewById(R.id.tvFrom);
            rideToTextView = rideView.findViewById(R.id.tvTo);
            rideDateTextView = rideView.findViewById(R.id.tvDate);
            rideTimeTextView = rideView.findViewById(R.id.tvTime);
            rideDriverTextView = rideView.findViewById(R.id.tvDriver);
            rideSeatsTextView = rideView.findViewById(R.id.tvSeats);

            mListener =listener;
            rideView.setOnClickListener(this);
        }

        void bind(SearchResponse.RideData ride) {
            rideFromTextView.setText(ride.getFrom());
            rideToTextView.setText(ride.getTo());
            rideDateTextView.setText(ride.getDate());
            rideTimeTextView.setText(ride.getStartTime());
            rideDriverTextView.setText(ride.getDriverId());
            rideSeatsTextView.setText(ride.getAvailableSeats());
        }

        @Override
        public void onClick(View v) {
            mListener.onRecyclerItemClick(v, getPosition());
        }
    }
}
