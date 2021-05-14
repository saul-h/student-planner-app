package com.gseven.studentplanner.ui.goaltracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gseven.studentplanner.R;
import com.gseven.studentplanner.data.daos.GoalDao;
import com.gseven.studentplanner.data.database.AppDatabase;
import com.gseven.studentplanner.data.entities.Goal;

import java.util.ArrayList;
import java.util.List;

public class GoalListAdapter extends RecyclerView.Adapter<GoalListAdapter.MyViewHolder> {

    private Context context;
    private List<Goal> goalList = new ArrayList<>();

    public GoalListAdapter(Context context) {
        this.context = context;
    }

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.goal_tracker_recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.goalName.setText(String.valueOf(this.goalList.get(position).name));
        holder.goalCompleted.setChecked(this.goalList.get(position).completed);

        holder.goalName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.goalCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = AppDatabase.getDBInstance(context.getApplicationContext());
                Goal goal = db.goalDao().getGoalWithGid(goalList.get(position).gid);
                if (holder.goalCompleted.isChecked())
                {
                    goal.completed = true;
                } else {
                    goal.completed = false;
                }
                db.goalDao().updateGoal(goal);
            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "This was clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.goalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView goalName;
        CheckBox goalCompleted;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            goalName = view.findViewById(R.id.tvGoalName);
            goalCompleted = view.findViewById(R.id.cbGoalCompleted);
            linearLayout = view.findViewById(R.id.linLayoutGoal);
        }
    }
}
