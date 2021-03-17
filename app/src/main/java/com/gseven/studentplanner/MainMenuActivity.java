package com.gseven.studentplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final ImageButton todo_list_button = findViewById(R.id.todo_list_button);
        final ImageButton degree_tracker_button = findViewById(R.id.degree_tracker_button);
        final ImageButton planner_button = findViewById(R.id.planner_button);
        final ImageButton goal_button = findViewById(R.id.goal_button);

        degree_tracker_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DegreeTrackerActivity.class);
                startActivity(intent);
            }
        });

        goal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GoalTrackerActivity.class);
                startActivity(intent);
            }
        });


        /**
         *  Method below controls the click listener set on the todo list button
         *  Launches the to do list activity when clicked
         *  Called : NotesMainActivity
         */
        todo_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotesMainActivity.class);
                startActivity(intent);
            }
        });
    }
}