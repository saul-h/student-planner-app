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
        final EditText goalCount = findViewById(R.id.editTextNumberGoalCount);


        Button saveButton = findViewById(R.id.btnSaveGoal);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 1;
                if (goalName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_LONG).show();
                } else if (goalName.getText().toString().isEmpty()) {
                    saveNewGoal(goalName.getText().toString(),
                            goalDescription.getText().toString(),
                            count);
                } else {
                    try {
                        count = Integer.parseInt(goalCount.getText().toString());
                        saveNewGoal(goalName.getText().toString(),
                                goalDescription.getText().toString(),
                                count);
                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Must be a number", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    private void saveNewGoal(String name, String description, int count) {
        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        Goal goal = new Goal();
        goal.name = name;
        goal.description = description;
        goal.currentProgress = 0;
        goal.totalNeeded = count;
        goal.completed = false;
        db.goalDao().insertGoal(goal);
        finish();
    }
}
