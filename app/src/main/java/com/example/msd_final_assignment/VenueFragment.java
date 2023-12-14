package com.example.msd_final_assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;


import java.util.List;

public class VenueFragment extends Fragment implements VenueAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private VenueAdapter venueAdapter;
    private List<Venue> venues;

    // Initialize your venues and adapter

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venue, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewVenues);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        venueAdapter = new VenueAdapter(venues, this);
        recyclerView.setAdapter(venueAdapter);

        // Implement Google Maps integration and fetch venues data

        return view;
    }

    @Override
    public void onAddToFavoritesClick(Venue venue) {
        // Implement adding the venue to the favorites list
    }
}
