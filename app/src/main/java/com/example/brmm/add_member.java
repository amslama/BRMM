package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class add_member extends AppCompatActivity {
    String section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        //TextViews
        final TextView role_textview = findViewById(R.id.role_add_member_textview);
        role_textview.setVisibility(View.INVISIBLE);
        final TextView section_textview = findViewById(R.id.section_add_member_textview);
        section_textview.setVisibility(View.INVISIBLE);
        final TextView notes_textview = findViewById(R.id.notes_add_member_textview);
        notes_textview.setVisibility(View.INVISIBLE);

        //Edittexts
        final EditText fname_edittext = findViewById(R.id.fname_add_member_edittext);
        final EditText lname_edittext = findViewById(R.id.lname_add_member_edittext);
        final EditText ULID_edittext = findViewById(R.id.ULID_add_member_edittext);
        final EditText UID_edittext = findViewById(R.id.UID_add_member_edittext);
        final EditText role_edittext = findViewById(R.id.role_add_member_edittext);
        role_edittext.setVisibility(View.INVISIBLE);
        final EditText notes_edittext = findViewById(R.id.notes_add_member_edittext);
        notes_edittext.setVisibility(View.INVISIBLE);
        //Dropdowns
        final Spinner section_spin = findViewById(R.id.section_add_member_dropdown);
        section_spin.setVisibility(View.INVISIBLE);

        //Radio
        RadioGroup rgroup = findViewById(R.id.member_type_add_member_radiogroup);
        final RadioButton student_rb = findViewById(R.id.student_add_member_radiobutton);
        final RadioButton faculty_rb = findViewById(R.id.faculty_add_member_radiobutton);

        //Buttons
        Button ok_button = findViewById(R.id.ok_edit_member_button);
        Button cancel_button = findViewById(R.id.cancel_add_member_button);

        ArrayList<String> sectionlist = getIntent().getStringArrayListExtra("sectionlist");
        if (sectionlist != null) {
            ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, sectionlist);
            section_spin.setAdapter(memberAdapter);
        }

        //add members
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (faculty_rb.isChecked() || student_rb.isChecked()) {
                    if (!fname_edittext.getText().toString().isEmpty() && !lname_edittext.getText().toString().isEmpty() &&
                            !ULID_edittext.getText().toString().isEmpty() && !UID_edittext.getText().toString().isEmpty()) {

                        try {
                            String role = "";

                            String firstName = fname_edittext.getText().toString();
                            String lastName = lname_edittext.getText().toString();
                            String ulid = ULID_edittext.getText().toString();
                            String notes = notes_edittext.getText().toString();
                            int UID = Integer.parseInt(UID_edittext.getText().toString());
                            BandMemberFactory factory = new BandMemberFactory();
                            BandMember member;
                            if (faculty_rb.isChecked()) {
                                member = (Faculty) factory.buildBandMember("Faculty");
                                ((Faculty) member).setRole(role);
                            } else {
                                member = (Student) factory.buildBandMember("Student");
                                ((Student) member).setSectionLead("");
                                member.setSection(section);
                                ((Student) member).setNotes(notes);
                            }
                            member.setUID(UID);
                            member.setFname(firstName);
                            member.setLname(lastName);
                            member.setUlid(ulid);
                            Intent intent = new Intent();
                            intent.putExtra("member", member);
                            setResult(RESULT_OK, intent);
                            finish();
                        } catch (NumberFormatException ex) {
                        }


                    }
                    else
                    {
                        String invalid = "Please fill out first name, last name, ulid, and UID";
                        Toast incomplete_toast = Toast.makeText(getApplicationContext(), invalid, Toast.LENGTH_LONG);
                        incomplete_toast.show();
                    }
                }
                else
                {
                    String invalid = "Please select a member type";
                    Toast incomplete_toast = Toast.makeText(getApplicationContext(), invalid, Toast.LENGTH_LONG);
                    incomplete_toast.show();
                }
            }
        });

        //cancels all fields
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        //section spinner logic
        section_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                section = section_spin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                section = "";
            }
        });

        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (faculty_rb.isChecked() == true) {
                    role_textview.setVisibility(View.VISIBLE);
                    role_edittext.setVisibility(View.VISIBLE);
                    section_textview.setVisibility(View.INVISIBLE);
                    section_spin.setVisibility(View.INVISIBLE);
                    notes_textview.setVisibility(View.INVISIBLE);
                    notes_edittext.setVisibility(View.INVISIBLE);
                } else {
                    role_textview.setVisibility(View.INVISIBLE);
                    role_edittext.setVisibility(View.INVISIBLE);
                    section_textview.setVisibility(View.VISIBLE);
                    section_spin.setVisibility(View.VISIBLE);
                    notes_textview.setVisibility(View.VISIBLE);
                    notes_edittext.setVisibility(View.VISIBLE);
                }
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
