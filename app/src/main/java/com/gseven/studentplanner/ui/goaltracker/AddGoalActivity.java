package com.gseven.studentplanner.ui.goaltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gseven.studentplanner.R;
import com.gseven.studentplanner.data.database.AppDatabase;
import com.gseven.studentplanner.data.entities.Goal;

public class AddGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);

        final EditText goalName = findViewById(R.id.editTextGoal);
        final EditText goalDescription = findViewById(R.id.editTextGoalDescription);

        Button saveButton = findViewById(R.id.btnSaveGoal);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewGoal(goalName.getText().toString(), goalDescription.getText().toString());
            }
        });
    }

    private void saveNewGoal(String name, String description) {
        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        Goal goal = new Goal();
        goal.name = name;
        goal.description = description;
        goal.currentProgress = 0;
        goal.completed = false;
        db.goalDao().insertGoal(goal);
        finish();
    }
}
