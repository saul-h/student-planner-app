package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class DegreeTrackerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree_tracker);
    }

    /**
     * Opens AddNewCourse Activity when user clicks AddNewCourse Button
     * @param view
     */
    public void startAddNewCourse(View view){
        Intent intent = new Intent(this, AddNewCourseActivity.class);


        startActivity(intent);

        //TODO: Retrieve the returned Course from AddNewCourse intent and populate the student's list of Courses
    }

    /**
     * Opens ViewAllCoursesActivity when user clicks AllCourses Button
     * @param view
     */
    public void startAllCourses(View view) {
        Intent intent = new Intent(this, ViewAllCoursesActivity.class);

        //TODO: populate intent with list of Course to be displayed in the ViewAllCourses Activity

        startActivity(intent);


    }
}