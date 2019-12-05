package com.example.brmm;

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

public class edit_member extends AppCompatActivity {

    private String section;


    //Textviews
    private TextView header_textview;
    private TextView member_type_textview;
    private TextView fname_textview;
    private TextView lname_textview;
    private TextView dept_textview;
    private TextView role_textview;
    private TextView UID_textview;
    private TextView section_textview;
    private TextView notes_textview;
    //Edittexts
    private EditText fname_edittext;
    private EditText lname_edittext;
    private EditText dept_edittext;
    private EditText role_edittext;
    private EditText UID_edittext;
    private EditText notes_edittext;
    //Dropdowns
    private Spinner section_spin;

    //Radio
    private RadioGroup rgroup;
    private RadioButton student_rb;
    private RadioButton faculty_rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member);

        //Textviews
        header_textview = findViewById(R.id.edit_member_header);
        member_type_textview = findViewById(R.id.edit_member_type_textview);
        fname_textview = findViewById(R.id.fname_edit_member_textview);
        lname_textview = findViewById(R.id.lname_edit_member_textview);
        dept_textview = findViewById(R.id.dept_edit_member_textview);
        role_textview = findViewById(R.id.role_edit_member_textview);
        UID_textview = findViewById(R.id.UID_edit_member_textview);
        section_textview = findViewById(R.id.section_edit_member_textview);
        notes_textview = findViewById(R.id.notes_edit_member_textview);

        //Edittexts
        fname_edittext = findViewById(R.id.fname_edit_member_edittext);
        lname_edittext = findViewById(R.id.fname_edit_member_edittext);
        dept_edittext = findViewById(R.id.fname_edit_member_edittext);
        role_edittext = findViewById(R.id.fname_edit_member_edittext);
        UID_edittext = findViewById(R.id.fname_edit_member_edittext);
        notes_edittext = findViewById(R.id.notes_edit_member_edittext);

        //Dropdowns
        section_spin = findViewById(R.id.section_edit_member_dropdown);
        final Spinner pick_spin = findViewById(R.id.pick_edit_member_dropdown);


        //Radio
        rgroup = findViewById(R.id.member_type_edit_member_radiogroup);
        student_rb = findViewById(R.id.student_edit_member_radiobutton);
        faculty_rb = findViewById(R.id.faculty_edit_member_radiobutton);

        //Buttons
        Button cancel_member = findViewById(R.id.cancel_add_instrument_button);
        Button ok_button = findViewById(R.id.ok_add_instrument_button);

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName;
                String lastName;
                String ulid;
                int UID;
                String role;
                String notes;
                Boolean isFaculty = false;
                //temp logic to prevent error
                BandMember member = new BandMember();

                firstName = fname_edittext.getText().toString();
                lastName = lname_edittext.getText().toString();
                ulid = dept_edittext.getText().toString();
                role = role_edittext.getText().toString();
                try {
                    UID = Integer.parseInt(UID_edittext.getText().toString());
                }
                catch (NumberFormatException ex){UID = 0;}
                notes = notes_edittext.getText().toString();
                if (student_rb.isChecked())
                    isFaculty = false;
                if (faculty_rb.isChecked())
                    isFaculty = true;

                if(!firstName.equals(""))
                    member.setFname(firstName);
                if(!lastName.equals(""))
                    member.setFname(lastName);
                if(!ulid.equals(""))
                    member.setUlid(ulid);
                if(!role.equals("")) {
                    if (member instanceof Faculty)
                    ((Faculty) member).setRole(role);
                }
                if (UID != 0)
                    member.setUID(UID);
                if (!notes.equals("")) {
                    if (member instanceof Student)
                    ((Student) member).setNotes(notes);
                }
            }
        });

        //cancels all fields
        cancel_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname_edittext.setText("");
                lname_edittext.setText("");
                dept_edittext.setText("");
                UID_edittext.setText("");
                role_edittext.setText("");
                notes_edittext.setText("");
                student_rb.setChecked(false);
                faculty_rb.setChecked(false);
                section_spin.setSelection(0);
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
