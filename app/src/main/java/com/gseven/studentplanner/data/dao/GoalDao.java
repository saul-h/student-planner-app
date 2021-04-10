package com.gseven.studentplanner.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gseven.studentplanner.data.entities.Goal;

@Dao
public interface GoalDao {
    @Insert
    public void insertGoals(Goal... goals);

    @Update
    public void updateGoals(Goal... goals);

    @Delete
    public void deleteGoals(Goal... goals);

    @Query("SELECT * FROM goal")
    public Goal[] loadAllGoals();

}
