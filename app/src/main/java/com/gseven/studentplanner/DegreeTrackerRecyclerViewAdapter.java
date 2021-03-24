package com.gseven.studentplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gseven.studentplanner.data.model.Course;

import java.util.List;

public class DegreeTrackerRecyclerViewAdapter extends RecyclerView.Adapter<DegreeTrackerRecyclerViewAdapter.DegreeTrackerViewHolder> {

    private List<Course> courses;

    private Context context;

    public DegreeTrackerRecyclerViewAdapter(Context context, List<Course> courses) {
        this.courses = courses;
        this.context = context;
    }

    @NonNull
    @Override
    public DegreeTrackerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.degree_tracker_row_item,parent,false);

        DegreeTrackerViewHolder holder = new DegreeTrackerViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull DegreeTrackerViewHolder holder, int position) {

        holder.courseNameText.setText(this.courses.get(position).getCourseName());

        holder.unitText.setText(String.valueOf(this.courses.get(position).getUnits()));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display toast to screen when user clicks on Course in remaining Course list. Displays projected completion semester
                Toast toast = Toast.makeText(context, "Projected Completion: " + courses.get(position).getSemester(), Toast.LENGTH_SHORT);

                toast.show();





            }
        });

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


    public class DegreeTrackerViewHolder extends RecyclerView.ViewHolder{
        TextView courseNameText;
        TextView unitText;
        ConstraintLayout parentLayout;

        public DegreeTrackerViewHolder(@NonNull View itemView) {
            super(itemView);

            courseNameText = itemView.findViewById(R.id.txt_courseNameitem);
            unitText = itemView.findViewById(R.id.txt_courseUnitItem);

            parentLayout = itemView.findViewById(R.id.layout_degreeItem);

        }
    }


}
