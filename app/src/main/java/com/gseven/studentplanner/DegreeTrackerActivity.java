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
 * Main activity for the Degree Tracker module.
 * Displays overall degree progress completion.
 * Displays current list of Courses which are not completed
 */
public class DegreeTrackerActivity extends AppCompatActivity {


    private List<Course> courses;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree_tracker);

        /** Hard coded Courses for testing/debugging. Will remove when we're able to persist with DB */

        Course course1 = new Course("CECS 445",3,"Complete","Spring 2020",'A');
        Course course2 = new Course("CECS 491B",3,"Complete","Spring 2020",'A');
        Course course3 = new Course("CECS 378",3,"Complete","Spring 2020",'B');
        Course course4 = new Course("CECS 453",3,"Complete","Spring 2020",'B');
        Course course5 = new Course("CECS 476",3,"Complete","Spring 2020",'A');
        Course course6 = new Course("ENGR 361",3,"Complete","Spring 2020",'A');
        Course course7 = new Course("CECS 474",3,"Complete","Spring 2020",'B');
        Course course8 = new Course("CECS 475",3,"Complete","Spring 2020",'B');
        Course course9 = new Course("CECS 342",3,"Complete","Spring 2020",'B');

        courses = new ArrayList<>();

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
        courses.add(course6);
        courses.add(course7);
        courses.add(course8);
        courses.add(course9);


        /** Create and initialize the RecyclerView and RecyclerView properties */
        recyclerView = findViewById(R.id.recyclerView_degreeTracker);

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(divider);

        DegreeTrackerRecyclerViewAdapter adapter = new DegreeTrackerRecyclerViewAdapter(this, courses);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * Opens AddNewCourse Activity when user clicks AddNewCourse Button. Retrieves new Course returned and adds to Course list
     * @param view
     */
    public void startAddNewCourse(View view){
        Intent intent = new Intent(this, AddNewCourseActivity.class);


        startActivity(intent);

        //TODO: Retrieve the returned Course from AddNewCourse intent and update/populate the student's list of Courses
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