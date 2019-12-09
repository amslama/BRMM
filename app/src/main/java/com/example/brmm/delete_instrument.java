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

public class delete_instrument extends AppCompatActivity {

    private Instrument ins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_instrument);


        //Dropdowns
        final Spinner pick_spin = findViewById(R.id.instrument_delete_instrument_dropdown);

        //Buttons
        Button ok_button = findViewById(R.id.ok_delete_instrument_button);
        Button cancel_button = findViewById(R.id.cancel_delete_instrument_button);

        ins = new Instrument();
        final ArrayList<Integer> instruments = new ArrayList<>();
        final ArrayList<Instrument> temp = (ArrayList<Instrument>) getIntent().getSerializableExtra("instrumentlist");
        if (temp != null) {
            for (Instrument instrument : temp) {
                instruments.add(instrument.getId());
            }
            if (!instruments.isEmpty()) {
                ArrayAdapter<Integer> instrumentAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, instruments);
                pick_spin.setAdapter(instrumentAdapter);
            }
        }

        //deletes instrument
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pick_spin.getSelectedItem() != null) {
                    Intent intent = new Intent();
                    intent.putExtra("instrument", ins);
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


        //logic for spinner containing instruments
        pick_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                if (pick_spin.getSelectedItem() != null && temp != null) {
                    int ins_id = Integer.parseInt(pick_spin.getSelectedItem().toString());
                    for (Instrument instrum : temp) {
                        if (instrum.getId() == ins_id) {
                            ins = instrum;
                            break;
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
