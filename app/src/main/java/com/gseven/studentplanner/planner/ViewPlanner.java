package com.gseven.studentplanner.planner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.widget.CalendarView;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.gseven.studentplanner.MainMenuActivity;
import com.gseven.studentplanner.R;

public class ViewPlanner extends AppCompatActivity {
    private Button button;
    private Button add;

    private CheckedTextView checklist1, checklist2;
    CalendarView calendar;
    TextView date_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_planner);

        //This part of the code handles the "Set the Date" part of the Planner
        calendar = (CalendarView)
                findViewById(R.id.calendarView);
        date_view = (TextView)
                findViewById(R.id.date_view);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                 String Date = dayOfMonth + "-" + (month + 1) + "-" + year;
                 date_view.setText(Date);
             }
         });

        //Function should go like this
        /*
        Click on date
        List of events for that date appears
        Need database? Storing dates with events? Or can do local?
         */
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        //Button for the main menu
        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });

        //Making the buttons and function for adding tasks to the checklist per day
        checklist1 = (CheckedTextView) findViewById(R.id.checkedTextView);
        checklist2 = (CheckedTextView) findViewById(R.id.checkedTextView2);
        add = (Button) findViewById(R.id.button_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Refer to functions on adding courses on adding activities here
            }
        });

    }



    public void openMainMenu() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }



}