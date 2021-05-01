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
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gseven.studentplanner.data.database.AppDatabase;
import com.gseven.studentplanner.data.model.Course;

import java.util.List;


/**
 * Activity to enables user to view their total Course history to date
 * Displays all user Course history details.
 * Displays total counts for all Course statuses
 * Navigates to EditCourseActivity to update course information
 */

public class ViewAllCoursesActivity extends AppCompatActivity {

    static int LAUNCH_ADD_EDIT_COURSE = 2;


    private List<Course> allCourses;

    RecyclerView recyclerView;

    AllCoursesRecyclerViewAdapter adapter;

    private TextView completedCount;
    private TextView progressCount;
    private TextView plannedCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_courses);


        this.completedCount = findViewById(R.id.txt_completedCount);
        this.progressCount = findViewById(R.id.txt_progressCount);
        this.plannedCount = findViewById(R.id.txt_plannedCount);
        /**
        StudentPlannerDatabase db = Room.databaseBuilder(getApplicationContext(),
                StudentPlannerDatabase.class,
                "studentplanner-database")
                .allowMainThreadQueries().build();

        CourseDAO courseDAO = db.courseDao();
        this.allCourses = courseDAO.getAll();
        */

        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        this.allCourses = db.courseDao().getAll();

        /** Set UI TextViews to display course counts*/
        updateCourseCountTotals();

        /** Create and initialize the RecyclerView and RecyclerView properties */
        recyclerView = findViewById(R.id.recyclerView_allCourses);

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(divider);

        adapter = new AllCoursesRecyclerViewAdapter(this, allCourses);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == this.LAUNCH_ADD_EDIT_COURSE){
            if(resultCode == Activity.RESULT_OK) {

                Log.d("EDIT", "IN EDIT");

                /**
                StudentPlannerDatabase db = Room.databaseBuilder(getApplicationContext(),
                        StudentPlannerDatabase.class,
                        "studentplanner-database")
                        .allowMainThreadQueries().build();

                CourseDAO courseDAO = db.courseDao();

                this.allCourses.clear();
                this.allCourses.addAll(courseDAO.getAll());
                */

                AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
                this.allCourses.clear();
                this.allCourses.addAll(db.courseDao().getAll());

                updateCourseCountTotals();

            }
        }
    }


    /**
     * Updates the UI with the current course status count totals for all courses
     */
    public void updateCourseCountTotals(){

        int[] statusCounts = new int[3];

        for(Course course : this.allCourses){
            if(course.getStatus().equals("Complete")){
                statusCounts[0]++;
            }
            else if (course.getStatus().equals("In Progress")){
                statusCounts[1]++;
            }
            else{
                statusCounts[2]++;
            }
        }

        this.completedCount.setText("Courses Completed: " + statusCounts[0]);
        this.progressCount.setText("Courses In Progress: " + statusCounts[1]);
        this.plannedCount.setText("Courses Planned: " + statusCounts[2]);


    }


    public void startViewGPA(View view) {


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume() {
        super.onResume();

        /**
        StudentPlannerDatabase db = Room.databaseBuilder(getApplicationContext(),
                StudentPlannerDatabase.class,
                "studentplanner-database")
                .allowMainThreadQueries().build();

        CourseDAO courseDAO = db.courseDao();
        List<Course> updatedCourses = courseDAO.getAll();
        */

        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        List<Course> updatedCourses = db.courseDao().getAll();

        this.allCourses.clear();
        this.allCourses.addAll(updatedCourses);


        this.adapter.notifyDataSetChanged();
        this.updateCourseCountTotals();

    }





}

