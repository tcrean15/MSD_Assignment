package com.example.msd_final_assignment;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MyConcert.class}, version = 1, exportSchema = false)
public abstract class MyAppDatabase extends RoomDatabase {

    private static MyAppDatabase instance;

    public abstract MyConcertDao myConcertDao();

    public static synchronized MyAppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MyAppDatabase.class, "my_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}