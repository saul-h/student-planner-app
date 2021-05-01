package com.gseven.studentplanner.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gseven.studentplanner.data.model.Course;

@Database(entities = {Course.class}, version = 1)
public abstract class StudentPlannerDatabase extends RoomDatabase {
    public abstract CourseDAO courseDao();
}
