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

    }
}