package com.gseven.studentplanner;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;


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
    }

    //Override the default OptionsMenu with option_menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //final MenuItem edit_profile_button = findViewById(R.id.item1);

       // edit_profile_button.setOnMenuItemClickListener(new View.OnClickListener(){}
        //);

        switch(item.getItemId()){
            case R.id.item1:
                Intent intent = new Intent(this, EditProfileActivity.class);
                this.startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }*/
}