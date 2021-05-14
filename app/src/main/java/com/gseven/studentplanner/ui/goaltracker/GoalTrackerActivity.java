package com.gseven.studentplanner.ui.goaltracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gseven.studentplanner.R;
import com.gseven.studentplanner.data.database.AppDatabase;
import com.gseven.studentplanner.data.entities.Goal;

import java.util.List;

public class GoalTrackerActivity extends AppCompatActivity {
    private GoalListAdapter goalListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_tracker);

        final Button new_goal_button = findViewById(R.id.buttonNewGoal);
        final Button update_goal = findViewById(R.id.buttonUpdateGoal);

        new_goal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(GoalTrackerActivity.this, AddGoalActivity.class), 100);
            }
        });

        update_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(GoalTrackerActivity.this, EditGoalActivity.class), 100);
            }
        });


        initRecyclerView();

        loadGoalList();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewGoals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        goalListAdapter = new GoalListAdapter(this);
        recyclerView.setAdapter(goalListAdapter);
    }

    private void loadGoalList() {
        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        List<Goal> goalList = db.goalDao().loadAllGoals();
        goalListAdapter.setGoalList(goalList);
    }

    public void updateGoal(int goalID,
                           String name,
                           String description,
                           int currentProgress,
                           int totalNeeded,
                           boolean completed) {
        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        Goal goal = db.goalDao().getGoalWithGid(goalID);
        goal.name = name;
        goal.description = description;
        goal.currentProgress = 0;
        goal.totalNeeded = totalNeeded;
        goal.completed = false;
        db.goalDao().updateGoal(goal);
        //finish();
    }

    public void deleteGoal(int goalID) {
        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
        Goal goal = db.goalDao().getGoalWithGid(goalID);
        db.goalDao().deleteGoal(goal);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            loadGoalList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}