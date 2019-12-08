package com.example.brmm;

import android.content.Intent;
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

public class edit_part extends AppCompatActivity {

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_part);


        //edittexts
        final EditText name_edittext = findViewById(R.id.name_edit_part_edittext);
        final EditText sn_edittext = findViewById(R.id.sn_edit_part_edittext);
        final EditText cost_edittext = findViewById(R.id.cost_edit_part_edittext);

        //spinners
        final Spinner pick_spin = findViewById(R.id.pick_edit_part_dropdown);

        //sets up parts spinner
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


        Part temp_part = new Part();

        //buttons
        Button ok_button = findViewById(R.id.ok_edit_part_button);
        Button cancel_button = findViewById(R.id.cancel_edit_part_button);

        //logic for picking a part
        pick_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (partlist != null) {
                    count = 0;
                    for (String str : partlist) {
                        if (str == pick_spin.getSelectedItem().toString()) {
                            name_edittext.setText(temp.get(count).getName());
                            sn_edittext.setText(temp.get(count).getSerialNumber());
                            cost_edittext.setText(Double.toString(temp.get(count).getCost()));
                            Intent intent = getIntent();
                            intent.putExtra("count", count);
                            break;
                         }
                        count++;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        //edits the part
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!name_edittext.getText().toString().isEmpty() && !sn_edittext.getText().toString().isEmpty()
                        && !cost_edittext.getText().toString().isEmpty()) {

                    try {
                        double cost = Double.parseDouble(cost_edittext.getText().toString());

                        if (cost >= 0) {

                            String name = name_edittext.getText().toString();
                            String sn = sn_edittext.getText().toString();
                            cost = Double.parseDouble(cost_edittext.getText().toString());
                            RentableFactory factory = new RentableFactory();
                            Part part = (Part) factory.buildRentable("Part");
                            part.setSerialNumber(sn);
                            part.setCost(cost);
                            part.setName(name);
                            Intent intent = getIntent();
                            intent.putExtra("part", part);
                            setResult(RESULT_OK, intent);
                            finish();
                        }


                    } catch (NumberFormatException ex) {
                        String invalid = "Please enter a real number";
                        Toast incomplete_toast = Toast.makeText(getApplicationContext(), invalid, Toast.LENGTH_LONG);
                        incomplete_toast.show();
                    }

                } else {
                    String incomplete = "Please fill out ALL forms";
                    Toast incomplete_toast = Toast.makeText(getApplicationContext(), incomplete, Toast.LENGTH_LONG);
                    incomplete_toast.show();
                }
            }
        });

        //returns to mainscreen
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
