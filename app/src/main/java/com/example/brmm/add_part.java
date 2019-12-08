package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class add_part extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);

        //edittexts
        final EditText name_edittext = findViewById(R.id.name_add_part_edittext);
        final EditText sn_edittext = findViewById(R.id.sn_add_part_edittext);
        final EditText cost_edittext = findViewById(R.id.cost_add_part_edittext);


        //buttons
        Button ok_button = findViewById(R.id.ok_add_part_button);
        Button cancel_button = findViewById(R.id.cancel_add_part_button);

        //Add existing dropdown
        final Spinner add_existing_spin = findViewById(R.id.add_existing_add_part_dropdown);

        //sets up part spinner
        final ArrayList<String> partlist = new ArrayList<>();
        final ArrayList<Part> temp = (ArrayList<Part>) getIntent().getSerializableExtra("partlist");
        if (temp != null) {
            for (Part part : temp) {

                partlist.add(part.getName());
            }
            if (partlist != null) {
                ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, partlist);
                add_existing_spin.setAdapter(memberAdapter);
            }
        }

        //adds a part
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                String sn;
                double cost = 0;

                if (!name_edittext.getText().toString().isEmpty() && !sn_edittext.getText().toString().isEmpty()
                        && !cost_edittext.getText().toString().isEmpty()) {


                    try {
                        cost = Double.parseDouble(cost_edittext.getText().toString());
                        if (cost >= 0) {
                            name = name_edittext.getText().toString();
                            sn = sn_edittext.getText().toString();
                            RentableFactory factory = new RentableFactory();
                            Part part = (Part) factory.buildRentable("Part");
                            part.setName(name);
                            part.setCost(cost);
                            part.setSerialNumber(sn);
                            Intent intent = new Intent();
                            intent.putExtra("part", part);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            String invalid = "Please enter a positive number";
                            Toast incomplete_toast = Toast.makeText(getApplicationContext(), invalid, Toast.LENGTH_LONG);
                            incomplete_toast.show();
                        }
                    } catch (NumberFormatException ex) {
                        String invalid = "Please enter a real number";
                        Toast incomplete_toast = Toast.makeText(getApplicationContext(), invalid, Toast.LENGTH_LONG);
                        incomplete_toast.show();
                    }
                    cost_edittext.setText("");

                } else {
                    String incomplete = "Please fill out ALL forms";
                    Toast incomplete_toast = Toast.makeText(getApplicationContext(), incomplete, Toast.LENGTH_LONG);
                    incomplete_toast.show();
                }
            }
        });


        //returns to main screen
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //logic for add_existing_spin
        add_existing_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(partlist!=null)
                {
                    int count = 0;

                    for(String str : partlist)
                    {
                        if (str == add_existing_spin.getSelectedItem().toString())
                        {
                            name_edittext.setText(temp.get(count).getName());
                            sn_edittext.setText(temp.get(count).getSerialNumber());
                            cost_edittext.setText(Double.toString(temp.get(count).getCost()));

                        }
                        count++;
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }
}
