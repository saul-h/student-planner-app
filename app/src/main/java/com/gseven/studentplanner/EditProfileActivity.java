package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


//import com.gseven.studentplanner.data.database.UserDatabase;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //final ImageButton todo_list_button = findViewById(R.id.todo_list_button);
        final EditText fn_edit_text = findViewById(R.id.fn_edit_text);
        final EditText ln_edit_text = findViewById(R.id.ln_edit_text);
        final EditText email = findViewById(R.id.email_edit_text);

        Button save_button = findViewById(R.id.save_button);

        // TODO: put this in the sign up java file
        /*UserDatabase db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "UserDatabase")
                .allowMainThreadQueries()
                .build();

        db.userDao().insertUsers();*/


        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 04/17/21 Save or update to Database

            }
        });


    }
}