package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class delete_section extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_section);

        //Textviews
        TextView header = findViewById(R.id.delete_section_header);
        TextView name_textview = findViewById(R.id.section_delete_section_textview);

        //dropdowns
        Spinner section_spin = findViewById(R.id.section_delete_section_dropdown);

        //Buttons
        Button cancel_button = findViewById(R.id.cancel_delete_section_button);
        Button ok_button = findViewById(R.id.ok_delete_section_button);

        Intent intent = new Intent();
        ArrayList<String> sectionlist = getIntent().getStringArrayListExtra("sectionlist");
        //section_spin.set

    }
}
