package com.gseven.studentplanner.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gseven.studentplanner.data.dao.GoalDao;
import com.gseven.studentplanner.data.entities.Goal;

@Database(entities = {Goal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GoalDao goalDao();
}
