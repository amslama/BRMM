package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class delete_part extends AppCompatActivity {


    Part part;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_part);


        //Dropdowns
        final Spinner pick_spin = findViewById(R.id.part_delete_part_dropdown);

        //Buttons
        Button ok_button = findViewById(R.id.ok_delete_part_button);
        Button cancel_button = findViewById(R.id.cancel_delete_part_button);


        //sets up spinner containing Parts
        part = new Part();
        final ArrayList<String> partlist = new ArrayList<>();
        final ArrayList<Part> temp = (ArrayList<Part>) getIntent().getSerializableExtra("partlist");
        if (temp != null) {
            for (Part prt : temp) {
                partlist.add(prt.getName());
            }
            if (partlist != null) {
                ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, partlist);
                pick_spin.setAdapter(memberAdapter);
            }
        }

        //logic for getting Part from spinner
        pick_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int count = 0;
                if (pick_spin.getSelectedItem() != null && temp != null) {
                    String part_name = pick_spin.getSelectedItem().toString();
                    for (Part prt : temp) {
                        if (prt.getName().equals(part_name)) {
                            part = prt;
                            Intent intent = new Intent();
                            intent.putExtra("count", count);
                            setResult(RESULT_OK, intent);
                            break;
                        }
                        count++;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //deletes the part
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pick_spin.getSelectedItem() != null) {
                    Intent intent = new Intent();
                    intent.putExtra("part", part);
                    setResult(RESULT_OK, intent);
                    finish();
                }
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


