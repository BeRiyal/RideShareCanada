package com.example.ridesharecanada.views;

import android.content.Intent;
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

public class RideAdapter extends RecyclerView.Adapter<RideAdapter.RideViewHolder> {

    private List<SearchResponse.RideData> rideDataList;

    // Creating view holder by inflating the ride layout
    @NonNull
    @Override
    public RideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ride, parent, false);
        return new RideViewHolder(itemView, new onItemClickListener() {
            @Override
            public void onRecyclerItemClick(View view, int position) {
                // Handling click event to start RideBookingActivity with ride details
                Intent i = new Intent(view.getContext(), RideBookingActivity.class);
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

    // Binding data to the view holder
    @Override
    public void onBindViewHolder(@NonNull RideAdapter.RideViewHolder holder, int position) {
        SearchResponse.RideData ride = rideDataList.get(position);
        holder.bind(ride);
    }

    // Getting the count of items in the data list
    @Override
    public int getItemCount() {
        return rideDataList == null ? 0 : rideDataList.size();
    }

    // Updating the adapter with new ride data
    public void setItems(String rides) {
        Gson gson = new Gson();
        Type responseType = new TypeToken<List<SearchResponse.RideData>>() {
        }.getType();
        List<SearchResponse.RideData> rideList = gson.fromJson(rides, responseType);

        // Update the internal ride data list and notify the adapter of the change
        this.rideDataList = rideList;
        notifyDataSetChanged();
    }

    // View holder for the recycler view
    public class RideViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public onItemClickListener mListener;

        private TextView rideFromTextView, rideToTextView, rideDateTextView, rideTimeTextView, rideDriverTextView, rideSeatsTextView;

        public RideViewHolder(@NonNull View rideView, onItemClickListener listener) {
            super(rideView);
            rideFromTextView = rideView.findViewById(R.id.tvFrom);
            rideToTextView = rideView.findViewById(R.id.tvTo);
            rideDateTextView = rideView.findViewById(R.id.tvDate);
            rideTimeTextView = rideView.findViewById(R.id.tvTime);
            rideDriverTextView = rideView.findViewById(R.id.tvDriver);
            rideSeatsTextView = rideView.findViewById(R.id.tvSeats);

            mListener = listener;
            rideView.setOnClickListener(this);
        }

        // Binding ride data to the view holder
        void bind(SearchResponse.RideData ride) {
            rideFromTextView.setText(ride.getFrom());
            rideToTextView.setText(ride.getTo());
            rideDateTextView.setText(ride.getDate());
            rideTimeTextView.setText(ride.getStartTime());
            rideDriverTextView.setText(ride.getDriverId());
            rideSeatsTextView.setText(ride.getAvailableSeats());
        }

        // Handling click events on the recycler view items
        @Override
        public void onClick(View v) {
            mListener.onRecyclerItemClick(v, getAdapterPosition());
        }
    }

    // Interface to handle item click events
    public interface onItemClickListener {
        void onRecyclerItemClick(View view, int position);
    }
}
