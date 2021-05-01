package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.gseven.studentplanner.data.model.Course;
import com.gseven.studentplanner.database.CourseDAO;
import com.gseven.studentplanner.database.StudentPlannerDatabase;


/**
 * AddNewCourseActivity
 * Form that allows user to enter new Course information they wish to add to their existing list of Courses
 */

public class AddNewCourseActivity extends AppCompatActivity {

   int LAUNCH_ADD_NEW_COURSE = 1;

   private EditText courseName;
   private EditText gradeReceived;
   private EditText semester;
   private EditText units;

   private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);

        this.courseName = findViewById(R.id.editText_course);
        this.gradeReceived = findViewById(R.id.editText_grade);
        this.semester = findViewById(R.id.editText_semester);
        this.units = findViewById(R.id.editText_units);
        this.radioGroup = findViewById(R.id.radio_group);



    }

    /**
     * Adds a new Course to the Student current list of Courses
     * @param view
     */
    public void addNewCourse(View view) {

        String name = this.courseName.getText().toString();
        String semester = this.semester.getText().toString();
        int units = Integer.parseInt(this.units.getText().toString());

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

        Course course = new Course(name,units,status,semester,grade);


        Intent intent = new Intent();


        StudentPlannerDatabase db = Room.databaseBuilder(getApplicationContext(),
                StudentPlannerDatabase.class,
                "studentplanner-database")
                .allowMainThreadQueries().build();

        CourseDAO courseDAO = db.courseDao();
        courseDAO.insertCourses(course);

        setResult(AppCompatActivity.RESULT_OK);

        finish();

        Toast toast = Toast.makeText(this,"New Course: " + course.getCourseName() + " successfully added!", Toast.LENGTH_SHORT);
        toast.show();





    }

    /**
     * Clears all the form fields/widgets in the AddNewCourseActivity
     * @param view
     */
    public void clearAllFields(View view) {

        this.courseName.setText("");
        this.gradeReceived.setText("");
        this.semester.setText("");
        this.units.setText("");

        Toast toast = Toast.makeText(this, "Fields cleared!", Toast.LENGTH_SHORT);
        toast.show();


    }
}