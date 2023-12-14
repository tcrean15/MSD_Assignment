package com.example.msd_final_assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.ViewHolder> {
    private List<Venue> venues;
    private OnItemClickListener listener;

    public VenueAdapter(List<Venue> venues, OnItemClickListener listener) {
        this.venues = venues;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_venue, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Venue venue = venues.get(position);

        holder.textViewName.setText(venue.getName());

        holder.buttonAddToFavorites.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAddToFavoritesClick(venue);
            }
        });
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        Button buttonAddToFavorites;

        ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            buttonAddToFavorites = itemView.findViewById(R.id.buttonAddToFavorites);
        }
    }

    interface OnItemClickListener {
        void onAddToFavoritesClick(Venue venue);
    }
}
