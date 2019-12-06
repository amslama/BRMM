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


    private Part part;

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
        final ArrayList<String> parts = new ArrayList<>();
        final ArrayList<Part> temp = (ArrayList<Part>) getIntent().getSerializableExtra("partlist");
        if (temp != null) {
            for (Part prt : temp) {
                parts.add(prt.getSerialNumber());
            }
            if (!parts.isEmpty()) {
                ArrayAdapter<String> partAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, parts);
                pick_spin.setAdapter(partAdapter);
            }
        }

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("instrument", part);
                setResult(RESULT_OK, intent);
                finish();
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
                if(pick_spin.getSelectedItem()!=null && temp!= null)
                {
                    int sn_id = Integer.parseInt(pick_spin.getSelectedItem().toString());
                    for( Part prt : temp)
                    {
                        if(prt.getId() == sn_id)
                        {
                            part = prt;
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
}


