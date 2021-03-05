package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * EditCourseActivity
 * Takes a Course passed from the parent Activity and updates the Course based on user submitted form fields. Returns updated Course
 */
public class EditCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
    }

    /**
     * Retrieves a Course from parent Activity intent and updates the given Course based on user data entered in UpdateCourseActivity
     * @param view
     */
    public void updateCourse(View view) {

        Toast toast = Toast.makeText(this,"UpdateCourse Button pressed!", Toast.LENGTH_LONG);
        toast.show();

        //TODO: 1.) Update the given Course based on user submitted fields in UpdateCourseActivity form
        //TODO: 2.) Update the Student list of Courses to replace the old Course with updated version of Course
        //TODO: 2.) Notify user the course was successfully updated

    }

    /**
     * Deletes the currently displayed Course in the form from the Student list of Courses
     * @param view
     */
    public void deleteCourse(View view) {

        Toast toast = Toast.makeText(this,"DeleteCourse Button pressed!", Toast.LENGTH_LONG);
        toast.show();

        //TODO: 1.) Remove Course from Student list of Courses
        //TODO: 2.) Notify user removed course was successful

    }
}