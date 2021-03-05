package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


/**
 * AddNewCourseActivity
 * Form that allows user to enter new Course information they wish to add to their existing list of Courses
 */
public class AddNewCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
    }

    /**
     * Adds a new Course to the Student current list of Courses
     * @param view
     */
    public void addNewCourse(View view) {

        Toast toast = Toast.makeText(this,"AddNewCourse Button pressed!", Toast.LENGTH_SHORT);
        toast.show();

        //TODO: 1.) Create Course with fields from AddNewCourse Activity
        //TODO: 2.) Add Course to Student list of Courses
        //TODO: 3.) Update RecyclerView if needed

    }

    /**
     * Clears all the form fields/widgets in the AddNewCourseActivity
     * @param view
     */
    public void clearAllFields(View view) {
        Toast toast = Toast.makeText(this, "ClearButton pressed!", Toast.LENGTH_LONG);
        toast.show();

        //TODO: 1.) Set method to clear all form fields in AddNewCourse activity
    }
}