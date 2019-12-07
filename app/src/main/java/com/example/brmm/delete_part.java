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


    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_part);


        //Dropdowns
        final Spinner pick_spin = findViewById(R.id.part_delete_part_dropdown);

        //Buttons
        Button ok_button = findViewById(R.id.ok_delete_part_button);
        Button cancel_button = findViewById(R.id.cancel_delete_part_button);

        final ArrayList<String> partlist = new ArrayList<>();
        final ArrayList<Part> temp = (ArrayList<Part>) getIntent().getSerializableExtra("partlist");
        if (temp != null) {
            for (Part part : temp) {
                partlist.add(part.getName());
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
                    temp.remove(count);
                    intent.putExtra("part", temp);
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
                if (partlist != null) {
                    count = 0;
                    for (String str : partlist) {
                        if (str == pick_spin.getSelectedItem().toString()) {
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


