package com.gseven.studentplanner;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class NoteIdeaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_idea);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = findViewById(R.id.fab);
        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         */

        // TODO: wire up retreival of text in text box for storage in the app

        // TODO: wire up the save button to commit changes

        // TODO: wire up a recycler or scroller view to see past notes or ideas

        // TODO: work on persistence of data as well as design / aesthetics



    }
}