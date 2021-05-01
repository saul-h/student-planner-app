package com.gseven.studentplanner;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gseven.studentplanner.data.model.Course;
import com.gseven.studentplanner.database.CourseDAO;
import com.gseven.studentplanner.database.StudentPlannerDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main activity for the Degree Tracker module.
 * Displays overall degree progress completion.
 * Displays current list of Courses which are not completed
 */
public class DegreeTrackerActivity extends AppCompatActivity {


    private static final String TAG = "DEGREE_TRACKER_ACTIVITY";
    static int LAUNCH_ADD_NEW_COURSE = 1;
    static int LAUNCH_EDIT_COURSE = 3;

    private List<Course> courses;
    private List<Course> remainingCourses;

    RecyclerView recyclerView;
    DegreeTrackerRecyclerViewAdapter adapter;

    private ProgressBar progressBar;
    private TextView completionPercentText;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree_tracker);


        StudentPlannerDatabase db = Room.databaseBuilder(getApplicationContext(),
                StudentPlannerDatabase.class,
                "studentplanner-database")
                .allowMainThreadQueries().build();

        CourseDAO courseDAO = db.courseDao();
        courses = courseDAO.getAll();

        /** filter to display only courses that are NOT completed */
        remainingCourses = courses.stream()
                                .filter(course -> !course.getStatus().equals("Complete"))
                                .collect(Collectors.toList());


        /** Create and initialize the RecyclerView and RecyclerView properties */
        recyclerView = findViewById(R.id.recyclerView_degreeTracker);

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(divider);

        adapter = new DegreeTrackerRecyclerViewAdapter(this, remainingCourses);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        completionPercentText = findViewById(R.id.txt_progBarHeader);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);

        updateCompletedCourseRatio();

    }

    /**
     * Starts AddNewCourse Activity when user clicks AddNewCourse Button. Retrieves new Course returned and adds to Course list
     * @param view
     */
    public void startAddNewCourse(View view){
        Intent intent = new Intent(this, AddNewCourseActivity.class);

        startActivityForResult(intent, LAUNCH_ADD_NEW_COURSE);
    }

    /**
     * Handler for adding and editing a  Course
     * @param requestCode Unique code specific for handling add new course intent event
     * @param resultCode status code indicating if new course creation is successful
     * @param data Newly created Course to be added to Course list
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LAUNCH_ADD_NEW_COURSE){
            if(resultCode == Activity.RESULT_OK){

                StudentPlannerDatabase db = Room.databaseBuilder(getApplicationContext(),
                        StudentPlannerDatabase.class,
                        "studentplanner-database")
                        .allowMainThreadQueries().build();

                CourseDAO courseDAO = db.courseDao();
                List<Course> updatedCourses = courseDAO.getAll();

                this.courses.clear();
                this.courses.addAll(updatedCourses);

                List<Course> tempRemainingCourses = this.courses.stream()
                                                    .filter(course -> !course.getStatus().equals("Complete"))
                                                    .collect(Collectors.toList());

                this.remainingCourses.clear();
                this.remainingCourses.addAll(tempRemainingCourses);
                this.adapter.notifyDataSetChanged();

                updateCompletedCourseRatio();

            }
        }
    }

    /**
     * Starts ViewAllCoursesActivity when user clicks AllCourses Button
     * @param view
     */
    public void startAllCourses(View view) {

        Intent intent = new Intent(this, ViewAllCoursesActivity.class);

        startActivity(intent);

    }

    /**
     * Updates the current course completion displayed on the Progress Bar
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateCompletedCourseRatio(){

        /** filter to display only courses that are NOT completed */
        List<Course> completedCourses = courses.stream()
                .filter(course -> course.getStatus().equals("Complete"))
                .collect(Collectors.toList());


        double ratio = ( (double)completedCourses.size() / this.courses.size());

        int percentCompletion = (int)Math.floor(100.0 * ratio);

        this.progressBar.setProgress( percentCompletion );
        this.completionPercentText.setText(percentCompletion + "% Degree Completion");


    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");

        StudentPlannerDatabase db = Room.databaseBuilder(getApplicationContext(),
                StudentPlannerDatabase.class,
                "studentplanner-database")
                .allowMainThreadQueries().build();

        CourseDAO courseDAO = db.courseDao();
        List<Course> updatedCourses = courseDAO.getAll();

        this.courses.clear();
        this.courses.addAll(updatedCourses);

        List<Course> tempRemainingCourses = this.courses.stream()
                .filter(course -> !course.getStatus().equals("Complete"))
                .collect(Collectors.toList());

        this.remainingCourses.clear();
        this.remainingCourses.addAll(tempRemainingCourses);
        this.adapter.notifyDataSetChanged();

        updateCompletedCourseRatio();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

  
}