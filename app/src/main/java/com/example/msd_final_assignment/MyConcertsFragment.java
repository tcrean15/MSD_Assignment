// MyConcertsFragment.java

package com.example.msd_final_assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
// MyConcertsFragment.java
// MyConcertsFragment.java
public class MyConcertsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ConcertAdapter<MyConcert> myConcertAdapter;

    public MyConcertsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_concerts, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewMyConcerts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the adapter (passing an empty list for now)
        myConcertAdapter = new ConcertAdapter<>(new ArrayList<>());
        recyclerView.setAdapter(myConcertAdapter);

        // Observe the LiveData from the Room database
        MyAppDatabase.getInstance(requireContext()).myConcertDao().getAllMyConcerts().observe(getViewLifecycleOwner(), myConcerts -> {
            // Update the adapter with the new list of MyConcerts
            myConcertAdapter.setConcerts(myConcerts);
        });

        return view;
    }
}

