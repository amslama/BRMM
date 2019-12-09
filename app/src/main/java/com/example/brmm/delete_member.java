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

public class delete_member extends AppCompatActivity {

    private BandMember bm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_member);


        //Dropdowns
        final Spinner pick_spin = findViewById(R.id.member_delete_member_dropdown);

        //Buttons
        Button ok_button = findViewById(R.id.ok_delete_member_button);
        Button cancel_button = findViewById(R.id.cancel_delete_member_button);

        bm = new BandMember();

        //sets up spinner containing bandmembers
        final ArrayList<Integer> members = new ArrayList<>();
        final ArrayList<BandMember> temp = (ArrayList<BandMember>) getIntent().getSerializableExtra("memberlist");
        if (temp != null) {
            for (BandMember bm : temp) {
                if (bm instanceof Student) {
                    if (((Student) bm).getInstrument() == null) {
                        members.add(bm.getUID());
                    }
                }
                else
                {
                    members.add(bm.getUID());
                }
            }
            if (!members.isEmpty()) {
                ArrayAdapter<Integer> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, members);
                pick_spin.setAdapter(memberAdapter);
            }
        }

        //logic for selecting BandMember from spinner
        pick_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int count = 0;
                if (pick_spin.getSelectedItem() != null && temp != null) {
                    int memberUID = Integer.parseInt(pick_spin.getSelectedItem().toString());
                    for (BandMember band : temp) {
                        if (band.getUID() == memberUID) {
                            Intent intent = getIntent();
                            intent.putExtra("count", count);
                            bm = band;
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

        //deletes bandmember
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pick_spin.getSelectedItem() != null) {
                    Intent intent = getIntent();
                    intent.putExtra("member", bm);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });


        //returns to main_screen
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
