package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class edit_notes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        //Textview
        TextView header = findViewById(R.id.edit_notes_textview);

        //Edittext
        final EditText notes_edittext = findViewById(R.id.edit_notes_edittext);

        //Buttons
        Button ok_button = findViewById(R.id.ok_edit_notes_button);
        Button cancel_button = findViewById(R.id.cancel_edit_notes_button);

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notes;
                notes = notes_edittext.getText().toString();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notes_edittext.setText("");
            }
        });
    }
}
