package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class edit_member extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member);

        //Textviews
        TextView header_textview = findViewById(R.id.edit_member_header);
        TextView member_type_textview = findViewById(R.id.edit_member_type_textview);
        TextView fname_textview = findViewById(R.id.fname_edit_member_textview);
        TextView lname_textview = findViewById(R.id.lname_edit_member_textview);
        TextView dept_textview = findViewById(R.id.dept_edit_member_textview);
        TextView role_textview = findViewById(R.id.role_edit_member_textview);
        TextView UID_textview = findViewById(R.id.UID_edit_member_textview);
        TextView section_textview = findViewById(R.id.section_edit_member_textview);
        TextView notes_textview = findViewById(R.id.notes_edit_member_textview);


        //Edittexts
        EditText fname_edittext = findViewById(R.id.fname_edit_member_edittext);
        EditText lname_edittext = findViewById(R.id.fname_edit_member_edittext);
        EditText dept_edittext = findViewById(R.id.fname_edit_member_edittext);
        EditText role_edittext = findViewById(R.id.fname_edit_member_edittext);
        EditText UID_edittext = findViewById(R.id.fname_edit_member_edittext);
        EditText notes_edittext = findViewById(R.id.notes_edit_member_edittext);

        //Dropdowns
        Spinner section_spin = findViewById(R.id.section_edit_member_dropdown);

        //Radio
        RadioGroup rgroup = findViewById(R.id.member_type_edit_member_radiogroup);
        RadioButton student_rb = findViewById(R.id.student_edit_member_radiobutton);
        RadioButton faculty_rb = findViewById(R.id.faculty_edit_member_radiobutton);

    }
}
