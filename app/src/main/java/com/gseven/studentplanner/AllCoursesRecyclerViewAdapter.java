package com.gseven.studentplanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class AllCoursesRecyclerViewAdapter extends RecyclerView.Adapter<AllCoursesRecyclerViewAdapter.AllCoursesViewHolder> {

    private List<Course> allCourses;
    private Context context;

    public AllCoursesRecyclerViewAdapter(Context context,List<Course> allCourses) {
        this.allCourses = allCourses;
        this.context = context;
    }

    @NonNull
    @Override
    public AllCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_courses_row_item,parent,false);

        AllCoursesRecyclerViewAdapter.AllCoursesViewHolder holder = new AllCoursesRecyclerViewAdapter.AllCoursesViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllCoursesViewHolder holder, int position) {
        holder.courseNameText.setText(this.allCourses.get(position).getCourseName());

        holder.semesterText.setText(this.allCourses.get(position).getSemester());

        holder.gradeText.setText(String.valueOf(this.allCourses.get(position).getGrade()));

        holder.statusText.setText(this.allCourses.get(position).getStatus());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(context, "Selected: " + allCourses.get(position).getCourseName(), Toast.LENGTH_SHORT);

                toast.show();


                Intent intent = new Intent(context,EditCourseActivity.class);

                Course course = allCourses.get(position);

                intent.putExtra("COURSE",course);



                ((Activity)context).startActivityForResult(intent,ViewAllCoursesActivity.LAUNCH_ADD_EDIT_COURSE);



            }
        });


    }

    @Override
    public int getItemCount() {
        return allCourses.size();
    }


    public class AllCoursesViewHolder extends RecyclerView.ViewHolder {

        TextView courseNameText;
        TextView semesterText;
        TextView gradeText;
        TextView statusText;

        ConstraintLayout parentLayout;




        public AllCoursesViewHolder(@NonNull View itemView) {
            super(itemView);

            courseNameText = itemView.findViewById(R.id.txt_allCoursesName);
            semesterText = itemView.findViewById(R.id.txt_allCoursesSemester);
            gradeText = itemView.findViewById(R.id.txt_allCoursesGrade);
            statusText = itemView.findViewById(R.id.txt_allCoursesStatus);

            parentLayout = itemView.findViewById(R.id.layout_allCoursesItem);

        }
    }


}
