package com.gseven.studentplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewGPA extends AppCompatActivity {
    private EditText grade1, grade2, grade3, grade4, grade5, credit1, credit2, credit3, credit4, credit5;
    private TextView gpaTotal;
    double gpa=0, product=0, total_credit=0, grade=0, credit=0;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_g_p);

        grade1 = (EditText)findViewById(R.id.grade1);
        credit1 = (EditText)findViewById(R.id.credit1);
        grade2 = (EditText)findViewById(R.id.grade2);
        credit2 = (EditText)findViewById(R.id.credit2);
        grade3 = (EditText)findViewById(R.id.grade3);
        credit3 = (EditText)findViewById(R.id.credit3);
        grade4 = (EditText)findViewById(R.id.grade4);
        credit4 = (EditText)findViewById(R.id.credit4);
        grade5 = (EditText)findViewById(R.id.grade5);
        credit5 = (EditText)findViewById(R.id.credit5);
        gpaTotal = (TextView)findViewById(R.id.gpaTotal);

        //Hardcoding all the credits and grades
        credit = Double.parseDouble(credit1.getText().toString());
        grade = Double.parseDouble(grade1.getText().toString());
        product += (credit*grade);
        total_credit += credit;
        credit = Double.parseDouble(credit2.getText().toString());
        grade = Double.parseDouble(grade2.getText().toString());
        product += (credit*grade);
        total_credit += credit;
        credit = Double.parseDouble(credit3.getText().toString());
        grade = Double.parseDouble(grade3.getText().toString());
        product += (credit*grade);
        total_credit += credit;
        credit = Double.parseDouble(credit4.getText().toString());
        grade = Double.parseDouble(grade4.getText().toString());
        product += (credit*grade);
        total_credit += credit;
        credit = Double.parseDouble(credit5.getText().toString());
        grade = Double.parseDouble(grade5.getText().toString());
        product += (credit*grade);
        total_credit += credit;

        gpaTotal.setText((int) (product/total_credit)); //Displays the GPA which is equal to ((course credit * grade point)/total credits)


        button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDT();
            }
        });

    }

    //Takes the user from the GPA to the Degree Tracker
    public void openDT() {
        Intent intent = new Intent(this, DegreeTrackerActivity.class);
        startActivity(intent);
    }

}
