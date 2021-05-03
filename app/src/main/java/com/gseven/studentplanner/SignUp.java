package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private EditText school;
    private EditText major;
    private EditText minor;
    private EditText password;
    private EditText postalAddress;
    private EditText emailAddress;
    private EditText phoneNumber;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstName = (EditText)findViewById(R.id.firstName);
        lastName = (EditText)findViewById(R.id.lastName);
        username = (EditText)findViewById(R.id.password);
        school = (EditText)findViewById(R.id.school);
        major = (EditText)findViewById(R.id.major);
        minor = (EditText)findViewById(R.id.minor);
        password = (EditText)findViewById(R.id.password);
        postalAddress = (EditText)findViewById(R.id.postalAddress);
        emailAddress = (EditText)findViewById(R.id.emailAddress);
        phoneNumber = (EditText)findViewById(R.id.phoneNumber);


        button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpSuccess();
            }
        });

    }

    public void validate() { //NO EFFECTIVE VALIDATION YET, placeholder for validation
        if (username.equals("Admin") && password.equals("1234")) {
            openSignUpSuccess();
        }
        else {
            openSignUpSuccess();
        }
    }

    //Takes the user from the sign up to the sign up success screen
    public void openSignUpSuccess() {
        Intent intent = new Intent(this, SignUpSuccess.class);
        startActivity(intent);
    }



}