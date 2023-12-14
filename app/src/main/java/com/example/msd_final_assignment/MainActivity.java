package com.example.msd_final_assignment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ViewPager and set it up with the sections adapter.
        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        // Setup the Tabs
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UpcomingConcertsFragment(), "Upcoming Concerts");
        adapter.addFragment(new VenuesNearMeFragment(), "Venues Near Me");
        viewPager.setAdapter(adapter);
    }
}
