package com.example.msd_final_assignment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyConcertDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MyConcert myConcert);

    @Query("SELECT * FROM my_concerts")
    LiveData<List<MyConcert>> getAllMyConcerts();

    // Add other CRUD operations as needed
}
