package com.example.msd_final_assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.bumptech.glide.Glide;
import android.widget.ImageView;


// ConcertAdapter.java
public class ConcertAdapter<T extends Concert> extends RecyclerView.Adapter<ConcertAdapter.ViewHolder> {
    private final List<T> concerts;
    private OnItemClickListener<T> listener;

    public ConcertAdapter(List<T> concerts) {
        this.concerts = concerts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_concert, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        T concert = concerts.get(position);

        holder.textViewName.setText(concert.getTitle());
        holder.textViewDate.setText(concert.getDate());

        // Set an OnClickListener for the "Add to My Concerts" button
        holder.buttonAddToMyConcerts.setOnClickListener(v -> {
            // Handle button click in your RecyclerView adapter
            if (listener != null) {
                listener.onAddToMyConcertsClick(concert);
            }
        });

        // Load image using Glide library (add Glide dependency in your app)
        Glide.with(holder.itemView.getContext())
                .load(concert.getImageUrl())
                .into(holder.imageView);
    }

    // Setter method for the click listener
    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return concerts.size();
    }

    public interface OnItemClickListener<T> {
        void onAddToMyConcertsClick(T concert);
        void onItemClick(T concert);
    }

    // Inside ConcertAdapter.java
    public void setConcerts(List<MyConcert> myConcerts) {
        this.MyConcerts = myConcerts;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDate;
        ImageView imageView;
        Button buttonAddToMyConcerts;

        ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            imageView = itemView.findViewById(R.id.imageView);
            buttonAddToMyConcerts = itemView.findViewById(R.id.buttonAddToMyConcerts);
        }
    }
}
}