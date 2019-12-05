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

import java.util.ArrayList;

public class edit_part extends AppCompatActivity {


    private Part return_part;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_part);

        //textviews
        /*
        TextView header = findViewById(R.id.edit_parts_header);
        TextView name_textview = findViewById(R.id.name_edit_part_textview);
        TextView sn_textview = findViewById(R.id.sn_edit_part_textview);
        TextView cost_textview = findViewById(R.id.cost_edit_part_textview)
        TextView compwith_textview = findViewById(R.id.compwith_edit_parts_textview);
        */

        //edittexts
        final EditText name_edittext = findViewById(R.id.name_edit_part_edittext);
        final EditText sn_edittext = findViewById(R.id.sn_edit_part_edittext);
        final EditText cost_edittext = findViewById(R.id.cost_edit_part_edittext);

        //compwith objects
        //Spinner compwith_spin = findViewById(R.id.compwith_edit_part_dropdown);
        //RecyclerView compwith_rview = findViewById(R.id.compwith_edit_part_rview);

        final Spinner pick_spin = findViewById(R.id.pick_edit_part_dropdown);

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

        //buttons
        Button ok_button = findViewById(R.id.ok_edit_part_button);
        Button cancel_button = findViewById(R.id.cancel_edit_part_button);


        pick_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(partlist!=null)
                {
                    for(String str : partlist)
                    {
                        int count = 0;
                        if (str == pick_spin.getSelectedItem().toString())
                        {
                            name_edittext.setText(temp.get(count).getName());
                            sn_edittext.setText(temp.get(count).getId());
                            cost_edittext.setText(Double.toString(temp.get(count).getCost()));

                        }
                        count++;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String name;
                 String sn;
                 double cost;
                try {
                    cost = Double.parseDouble(cost_edittext.getText().toString());
                }  catch (NumberFormatException ex){cost = 0;}
                return_part.setName(name_edittext.getText().toString());
                return_part.setSerialNumber(Integer.parseInt(sn_edittext.getText().toString()));
                return_part.setCost(Double.parseDouble(cost_edittext.getText().toString()));
                Intent intent = new Intent();
                intent.putExtra("part", return_part);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cost_edittext.setText("");
                name_edittext.setText("");
                sn_edittext.setText("");
                finish();
            }
        });
    }
}
