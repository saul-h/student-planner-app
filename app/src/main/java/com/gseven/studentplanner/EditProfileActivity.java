package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gseven.studentplanner.data.database.AppDatabase;
import com.gseven.studentplanner.data.entities.User;
import com.gseven.studentplanner.ui.login.LoginActivity;

import java.util.List;


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

        //fn_edit_text.setText("dkdkfjfk", TextView.BufferType.EDITABLE);

        String this_user_email, this_user_first_name, this_user_last_name;
        this_user_email = " ";
        this_user_first_name =" ";
        this_user_last_name = " ";
        Button save_button = findViewById(R.id.save_button);

        System.out.println(" first User reference= "+ (String) LoginActivity.global_userID);
        String user_reference = (String) LoginActivity.global_userID;


        AppDatabase this_db = AppDatabase.getDBInstance(this.getApplicationContext());

        List<User> this_user_list = this_db.userDao().load_user_list();
        System.out.println("User reference= "+ user_reference);

        //List<User> user = this_db.userDao().printUser(user_reference); //this list only has one user

        //System.out.println(user);

        //String print_fn = user.get(0).firstName


        for(int i = 0; i < this_user_list.size(); i++){
            String uid = this_user_list.get(i).getUserID();
            if(uid.equals(user_reference)){
                System.out.println("Fired User reference= "+ user_reference);
                this_user_email= this_user_list.get(i).getEmail();
                this_user_first_name = this_user_list.get(i).getFirstName();
                this_user_last_name = this_user_list.get(i).getLastName();

            }
        }

        fn_edit_text.setText(this_user_first_name, TextView.BufferType.EDITABLE);
        ln_edit_text.setText(this_user_last_name, TextView.BufferType.EDITABLE);
        email.setText(this_user_email, TextView.BufferType.EDITABLE);

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