package com.gseven.studentplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gseven.studentplanner.data.model.Course;

import java.io.Serializable;
import java.util.ArrayList;
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

        /** Retrieve all courses sent from DegreeTrackerActivty */
        Bundle bundle = getIntent().getExtras();

        /** set Course list to value passed in from bundle */
        allCourses = (ArrayList<Course>) bundle.getSerializable("ALLCOURSES");


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

        Log.d("DEBUG_VIEWALLCOURSES","ENTERED ONRESULT");

        if(requestCode == this.LAUNCH_ADD_EDIT_COURSE){
            if(resultCode == Activity.RESULT_OK){

                Course updatedCourse = (Course)data.getSerializableExtra("UPDATED_COURSE");

                String action = data.getStringExtra("KEY");


                if(action.equals("UPDATE")){

                    int index = this.allCourses.indexOf(updatedCourse);

                    this.allCourses.set(index,updatedCourse);

                    adapter.notifyDataSetChanged();

                    updateCourseCountTotals();

                    Log.d("DEBUG_VIEWALLCOURSES", "UPDATED VALUE: " +this.allCourses.get(index).getSemester());
                }
                else if(action.equals("DELETE")){

                    Log.d("DEBUG_VIEWALLCOURSES", "IN DELETE");

                    int index = this.allCourses.indexOf(updatedCourse);

                    this.allCourses.remove(index);
                    adapter.notifyDataSetChanged();

                    updateCourseCountTotals();

                }

            }
            else{
                Log.d("DEBUG_VIEWALLCOURSES", "NO COURSE EDITED");

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
}

