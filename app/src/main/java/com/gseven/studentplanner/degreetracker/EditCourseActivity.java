package com.gseven.studentplanner.degreetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gseven.studentplanner.R;
import com.gseven.studentplanner.data.database.AppDatabase;
import com.gseven.studentplanner.data.model.Course;

/**
 * EditCourseActivity
 * Takes a Course passed from the parent Activity and updates the Course based on user submitted form fields. Returns updated Course
 */
public class EditCourseActivity extends AppCompatActivity {

    private EditText courseName;
    private EditText gradeReceived;
    private EditText semester;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        this.courseName = findViewById(R.id.editText_editName);
        this.gradeReceived = findViewById(R.id.editText_editGrade);
        this.semester = findViewById(R.id.editText_editSemester);
        this.radioGroup = findViewById(R.id.radiogroup_edit);


        Course receivedCourse = (Course)getIntent().getSerializableExtra("COURSE");

        this.courseName.setText(receivedCourse.getCourseName());
        this.courseName.setFocusable(false);
        this.courseName.setEnabled(false);
        this.semester.setText(receivedCourse.getSemester());

        if(receivedCourse.getGrade() == '\0'){
            this.gradeReceived.setText("");
        }
        else {
            this.gradeReceived.setText(String.valueOf(receivedCourse.getGrade()));
        }

        if(receivedCourse.getStatus().equals("Planned")){
            ((RadioButton)this.radioGroup.getChildAt(0)).setChecked(true);
        }
        else if(receivedCourse.getStatus().equals("In Progress")){
            ((RadioButton)this.radioGroup.getChildAt(1)).setChecked(true);
        }
        else{
            ((RadioButton)this.radioGroup.getChildAt(2)).setChecked(true);
        }

    }

    /**
     * Retrieves a Course from parent Activity intent and updates the given Course based on user data entered in UpdateCourseActivity
     * @param view
     */
    public void updateCourse(View view) {

        String name = this.courseName.getText().toString();
        String semester = this.semester.getText().toString();

        char grade;

        if (this.gradeReceived.getText().toString().equals("") || this.gradeReceived.getText() == null){
            grade = '\0';
            Log.d("EDIT_COURSE", "GRADE EMPTY");
        } else {

            grade = this.gradeReceived.getText().toString().toUpperCase().charAt(0);

            String temp = this.gradeReceived.getText().toString();

            Log.d("EDIT_COURSE", "GRADE NOT EMPTY ->" + temp);
        }

        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

        String status = selectedRadioButton.getText().toString();

        Course course = new Course(name,3,status,semester,grade);

        /**
        StudentPlannerDatabase db = Room.databaseBuilder(getApplicationContext(),
                StudentPlannerDatabase.class,
                "studentplanner-database")
                .allowMainThreadQueries().build();

        CourseDAO courseDAO = db.courseDao();

        courseDAO.updateCourses(course);
        */

        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());

        db.courseDao().updateCourses(course);

        Intent intent = new Intent();


        setResult(AppCompatActivity.RESULT_OK);

        Toast toast = Toast.makeText(this,"Course: " + name +" updated!", Toast.LENGTH_SHORT);

        toast.show();

        finish();

    }

    /**
     * Deletes the currently displayed Course in the form from the Student list of Courses
     * @param view
     */
    public void deleteCourse(View view) {



        String name = this.courseName.getText().toString();
        String semester = this.semester.getText().toString();

        char grade;

        if (this.gradeReceived.getText().toString().equals("") || this.gradeReceived.getText() == null){
            grade = '\0';
        }
        else{
            grade = this.gradeReceived.getText().toString().toUpperCase().charAt(0);
        }

        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

        String status = selectedRadioButton.getText().toString();

        Course course = new Course(name,3,status,semester,grade);

        /**
        StudentPlannerDatabase db = Room.databaseBuilder(getApplicationContext(),
                StudentPlannerDatabase.class,
                "studentplanner-database")
                .allowMainThreadQueries().build();

        CourseDAO courseDAO = db.courseDao();

        courseDAO.deleteCourses(course);
        */

        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        db.courseDao().deleteCourses(course);

        Intent intent = new Intent();

        setResult(AppCompatActivity.RESULT_OK);

        Toast toast = Toast.makeText(this,"Removed Course: " + course.getCourseName(), Toast.LENGTH_SHORT);
        toast.show();

        finish();

    }
}