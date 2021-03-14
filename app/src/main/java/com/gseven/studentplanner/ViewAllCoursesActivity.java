package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * Activity to enables user to view their total Course history to date
 * Displays all user Course history details.
 * Displays total counts for all Course statuses
 * Navigates to EditCourseActivity to update course information
 */
public class ViewAllCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_courses);
    }


    /**
     * Starts EditCourseActivity. Passes selected Course from AllCourses list to EditCourseActiity
     * @param view
     */
    public void startEditCourse(View view) {
        Intent intent = new Intent(this,EditCourseActivity.class);
        startActivity(intent);

        // TODO: Add Course to intent and pass to EditCourse. Retrieve returned Course and update Course list

    }
}