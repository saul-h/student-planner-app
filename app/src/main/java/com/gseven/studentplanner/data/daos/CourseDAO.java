package com.gseven.studentplanner.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gseven.studentplanner.data.model.Course;

import java.util.List;

/**
 * CoarseDao is the Data Access Object for Course objects.
 *
 * Contains Query, Insert, Update and Delete methods
 * for Course objects.
 */
@Dao
public interface CourseDAO {

    @Query("SELECT * FROM course")
    List<Course> getAll();

    @Insert
    public void insertCourses(Course... courses);

    @Update
    public void updateCourses(Course... courses);

    @Delete
    public void deleteCourses(Course... courses);

}
