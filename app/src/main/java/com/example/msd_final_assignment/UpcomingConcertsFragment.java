package com.example.msd_final_assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide; // Add this dependency for image loading
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import android.os.AsyncTask;
import android.widget.Toast;


public class UpcomingConcertsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ConcertAdapter concertAdapter;
    private List<Concert> concerts;

    public UpcomingConcertsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_concerts, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the list of concerts
        concerts = new ArrayList<>();

        // Initialize the adapter
        concertAdapter = new ConcertAdapter(concerts);
        recyclerView.setAdapter(concertAdapter);

        // Fetch upcoming concerts data from the Ticketmaster API
        fetchUpcomingConcerts();

        return view;
    }

    private void fetchUpcomingConcerts() {
        // Use AsyncTask, ViewModel, or other asynchronous methods to fetch data from the API

        // Example URL for fetching upcoming concerts (replace with your actual API URL)
        String apiUrl = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=51qtiLTRENFnBPDNbXngymOGBza2DYm1&classificationName=Music&city=Dublin&size=10";

        // Example of using a network library (e.g., Retrofit, Volley) to make the API request
        // You need to implement the actual network request in your app
        // For simplicity, this example uses AsyncTask to demonstrate the concept
        new FetchConcertsTask().execute(apiUrl);
    }

    private class FetchConcertsTask extends AsyncTask<String, Void, String> {
        // Implement your AsyncTask to fetch data from the Ticketmaster API
        // This is a simplified example, and you might want to use a more robust solution in a real app

        @Override
        protected String doInBackground(String... urls) {
            // Perform the network request in the background
            // You can use libraries like Retrofit, Volley, or even HttpUrlConnection for this task

            // Example using HttpUrlConnection (Replace with your actual implementation)
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try (InputStream in = new BufferedInputStream(urlConnection.getInputStream())) {
                    // Read the InputStream
                    return readStream(in);
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Process the result in the UI thread
            if (result != null) {
                // Parse the JSON response and update the list of concerts
                parseConcertsJson(result);
                concertAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            }

            concertAdapter.setOnItemClickListener(new ConcertAdapter.OnItemClickListener() {
                @Override
                public void onAddToMyConcertsClick(Concert concert) {
                    // Implement the logic for adding to My Concerts
                }

                @Override
                public void onItemClick(Concert concert) {
                    // Implement the logic for item click
                }
            });

        }

        // Handle button click to add the concert to My Concerts
        private void addToMyConcerts(Concert concert) {
            // Get a reference to the Room database
            MyAppDatabase database = MyAppDatabase.getMyAppDatabase(requireContext());

            // Create a MyConcert object with the necessary details
            MyConcert myConcert = new MyConcert();
            myConcert.setConcertTitle(concert.getTitle());
            myConcert.setConcertDate(concert.getDate());

            // Insert the concert into the Room database
            database.myConcertDao().insert(myConcert);

            // Optionally, notify the user that the concert has been added
            Toast.makeText(requireContext(), "Concert added to My Concerts", Toast.LENGTH_SHORT).show();
        }
    }

        private String readStream(InputStream inputStream) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        }


    private void parseConcertsJson(String json) {
        try {
            JSONObject response = new JSONObject(json);
            JSONObject embedded = response.getJSONObject("_embedded");
            JSONArray events = embedded.getJSONArray("events");

            // Iterate through the events and add them to the list
            for (int i = 0; i < events.length(); i++) {
                JSONObject event = events.getJSONObject(i);
                String name = event.getString("name");
                String date = event.getJSONObject("dates").getJSONObject("start").getString("localDate");

                // Get the first image if available
                String imageUrl = "";
                JSONArray images = event.getJSONArray("images");
                if (images.length() > 0) {
                    imageUrl = images.getJSONObject(0).getString("url");
                }

                // Modify this line to use "name" instead of "title"
                concerts.add(new Concert(name, date, imageUrl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
