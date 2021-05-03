package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class SignUp extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openSignUpSuccess(); }
        });
    }



    //Takes the user from the sign up to the sign up success screen
    public void openSignUpSuccess() {
        Intent intent = new Intent(this, SignUpSuccess.class);
        startActivity(intent);
    }



}