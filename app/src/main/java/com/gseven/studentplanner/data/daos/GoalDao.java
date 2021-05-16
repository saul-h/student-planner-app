package com.gseven.studentplanner.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gseven.studentplanner.data.entities.Goal;

import java.util.List;

/**
 * The Data Access Object for the Goal entity.
 * 
 * Contains Query, Insert, Update and Delete methods
 * for the Goal.
 */
@Dao
public interface GoalDao {

    @Query("SELECT * FROM goal ORDER BY goal.completed ASC")
    List<Goal> loadAllGoals();

    @Query("SELECT * FROM goal WHERE goal.gid = :gid")
    Goal getGoalWithGid(int gid);

    @Insert
    void insertGoal(Goal... goal);

    @Update
    void updateGoal(Goal... goal);

    @Delete
    void deleteGoal(Goal... goal);
}
