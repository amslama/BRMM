package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class add_member extends AppCompatActivity {
    String section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        //Textviews
        TextView header_textview = findViewById(R.id.add_member_header);
        TextView member_type_textview = findViewById(R.id.member_type_textview);
        TextView fname_textview = findViewById(R.id.fname_add_member_textview);
        TextView lname_textview = findViewById(R.id.lname_add_member_textview);
        TextView role_textview = findViewById(R.id.role_add_member_textview);
        TextView UID_textview = findViewById(R.id.UID_add_member_textview);
        TextView section_textview = findViewById(R.id.section_add_member_textview);
        TextView notes_textview = findViewById(R.id.notes_add_member_textview);


        //Edittexts
        final EditText fname_edittext = findViewById(R.id.fname_add_member_edittext);
        final EditText lname_edittext = findViewById(R.id.fname_add_member_edittext);
        final EditText dept_edittext = findViewById(R.id.fname_add_member_edittext);
        final EditText role_edittext = findViewById(R.id.fname_add_member_edittext);
        final EditText UID_edittext = findViewById(R.id.fname_add_member_edittext);
        final EditText notes_edittext = findViewById(R.id.notes_add_member_edittext);

        //Dropdowns
        final Spinner section_spin = findViewById(R.id.section_add_member_dropdown);

        //Radio
        RadioGroup rgroup = findViewById(R.id.member_type_add_member_radiogroup);
        final RadioButton student_rb = findViewById(R.id.student_add_member_radiobutton);
        final RadioButton faculty_rb = findViewById(R.id.faculty_add_member_radiobutton);

        //Buttons
        Button add_member = findViewById(R.id.ok_edit_member_button);
        Button cancel_member = findViewById(R.id.cancel_add_member_button);

        add_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName;
                String lastName;
                String ulid;
                int UID;
                String role;
                String notes;

                boolean isFaculty = false;
                firstName = fname_edittext.getText().toString();
                lastName = lname_edittext.getText().toString();
                ulid = dept_edittext.getText().toString();
                try {
                    UID = Integer.parseInt(UID_edittext.getText().toString());
                } catch (NumberFormatException ex) {
                    UID = 0;
                }
                role = role_edittext.getText().toString();
                notes = notes_edittext.getText().toString();
                if (student_rb.isChecked())
                    isFaculty = false;
                if (faculty_rb.isChecked())
                    isFaculty = true;
                BandMemberFactory factory = new BandMemberFactory();
                BandMember member;

                boolean sectionLeader = false;

                if (isFaculty)
                    member = factory.buildBandMember("Faculty", firstName, lastName, ulid, section, sectionLeader, UID, notes, "Music", role);
                else
                    member = member = factory.buildBandMember("Student", firstName, lastName, "", section, sectionLeader, UID, notes, "Music", role);

                Intent intent = new Intent();
                intent.putExtra("member", member);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        //cancels all fields
        cancel_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
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


    }
}
