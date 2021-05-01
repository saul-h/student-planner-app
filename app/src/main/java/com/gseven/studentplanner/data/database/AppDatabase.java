package com.gseven.studentplanner.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gseven.studentplanner.data.daos.GoalDao;
import com.gseven.studentplanner.data.entities.Goal;

@Database(entities = {Goal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GoalDao goalDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDBInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder
                    (context.getApplicationContext(), AppDatabase.class, "database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
