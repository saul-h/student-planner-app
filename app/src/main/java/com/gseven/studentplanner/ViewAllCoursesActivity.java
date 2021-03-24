package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gseven.studentplanner.data.model.Course;

import java.util.ArrayList;
import java.util.List;


/**
 * Activity to enables user to view their total Course history to date
 * Displays all user Course history details.
 * Displays total counts for all Course statuses
 * Navigates to EditCourseActivity to update course information
 */
public class ViewAllCoursesActivity extends AppCompatActivity {


    private List<Course> allCourses;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_courses);

        /** Retrieve all courses sent from DegreeTrackerActivty */
        Bundle bundle = getIntent().getExtras();

        /** set Course list to value passed in from bundle */
        List<Course> allCourses = (ArrayList<Course>) bundle.getSerializable("ALLCOURSES");

        /** Create and initialize the RecyclerView and RecyclerView properties */
        recyclerView = findViewById(R.id.recyclerView_allCourses);

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(divider);

        AllCoursesRecyclerViewAdapter adapter = new AllCoursesRecyclerViewAdapter(this, allCourses);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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