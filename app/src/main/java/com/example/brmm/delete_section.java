package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
        final Spinner pick_spin = findViewById(R.id.section_delete_section_dropdown);

        //Buttons
        Button cancel_button = findViewById(R.id.cancel_delete_section_button);
        Button ok_button = findViewById(R.id.ok_delete_section_button);

        ArrayList<String> sectionlist = getIntent().getStringArrayListExtra("sectionlist");
        if (sectionlist != null) {
            ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, sectionlist);
            pick_spin.setAdapter(memberAdapter);
        }
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pick_spin.getSelectedItem() != null) {
                    Intent intent = getIntent();
                    intent.putExtra("section", pick_spin.getSelectedItem().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });


        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
