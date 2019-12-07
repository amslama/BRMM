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

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
    }
}


