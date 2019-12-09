package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class edit_notes extends AppCompatActivity {

    private Instrument ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        //Textview
        TextView header = findViewById(R.id.edit_notes_textview);

        //Edittext
        final EditText notes_edittext = findViewById(R.id.edit_notes_edittext);

        //Dropdowns
        final Spinner pick_spin = findViewById(R.id.pick_edit_note_dropdown);

        //Buttons
        Button ok_button = findViewById(R.id.ok_edit_notes_button);
        Button cancel_button = findViewById(R.id.cancel_edit_notes_button);

        //sets ups spinner of instruments
        final ArrayList<String> inslist = new ArrayList<>();
        final ArrayList<Instrument> temp = (ArrayList<Instrument>) getIntent().getSerializableExtra("instrumentlist");
        if (temp != null) {
            for (Instrument ins : temp) {
                inslist.add(ins.getName());
            }
            if (inslist != null) {
                ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, inslist);
                pick_spin.setAdapter(memberAdapter);
            }
        }

        //logic for selecting instrument
        pick_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(inslist!=null)
                {
                    int count = 0;

                    for(String str : inslist)
                    {
                        if (str.equals(pick_spin.getSelectedItem().toString()))
                        {
                            notes_edittext.setText(temp.get(count).getNote());
                            ins = temp.get(count);
                            Intent intent = getIntent();
                            intent.putExtra("count", count);
                            break;
                        }
                        notes_edittext.setText("");

                        count++;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });


        //edits the notes of the instrument
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notes = notes_edittext.getText().toString();
                ins.setNote(notes);
                Intent intent = getIntent();
                intent.putExtra("instrument", ins);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //returns to main screen
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //Timeout Timer
    private Timer timer;

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        timer.cancel();
        timer.purge();
        timer = new Timer();
        TimerTask timeOutTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timeout from Category Interaction");
                timeOut(); }
        };
        timer.schedule(timeOutTask, main_screen.logoutTime);
    }
    //sets timer to null when no longer on screen
    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        timer.purge();
    }
    //resets timer when resuming activity
    @Override
    protected void onResume() {
        super.onResume();
        timer = new Timer();
        TimerTask timeOutTask = new TimerTask() {
            @Override
            public void run() {
                timeOut(); }
        };
        timer.schedule(timeOutTask, main_screen.logoutTime);
    }
    //return to main screen
    private void timeOut(){
        Intent intent = new Intent();
        intent.putExtra("timeOut", true);
        setResult(RESULT_OK,intent);
        finish();
    }
}
