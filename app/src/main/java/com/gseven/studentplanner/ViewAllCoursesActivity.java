package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewAllCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_courses);
    }

    public void startEditCourse(View view) {
        Intent intent = new Intent(this,EditCourseActivity.class);
        startActivity(intent);
    }
}