package com.gseven.studentplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.widget.CalendarView;
import android.widget.TextView;

public class ViewPlanner extends AppCompatActivity {
    private Button button;

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


                button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });
    }



    public void openMainMenu() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }



}