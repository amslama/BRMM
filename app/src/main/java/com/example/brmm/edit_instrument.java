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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class edit_instrument extends AppCompatActivity {
    private String section;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_instrument);


        //Textviews
        final TextView header_textview = findViewById(R.id.edit_instrument_header);
        TextView name_textview = findViewById(R.id.name_edit_instrument_textview);
        TextView id_textview = findViewById(R.id.id_edit_instrument_textview);
        final TextView id_display_textview = findViewById(R.id.id_edit_instrument_display_textview);
        TextView section_textview = findViewById(R.id.section_edit_instrument_textview);
        final TextView cost_textview = findViewById(R.id.cost_edit_part_textview);
        TextView cat_textview = findViewById(R.id.cat_edit_instrument_textview);


        //Edittexts
        final EditText name_edittext = findViewById(R.id.name_edit_instrument_editText);
        final EditText cost_edittext = findViewById(R.id.cost_edit_instrument_edittext);

        //Dropdowns
        final Spinner section_spin = findViewById(R.id.section_edit_instrument_dropdown);
        final Spinner cat_spin = findViewById(R.id.cat_edit_instrument_dropdown);
        final Spinner pick_spin = findViewById(R.id.pick_edit_instrument_dropdown);

        //Buttons
        Button add_cat_button = findViewById(R.id.add_cat_edit_instrument_button);
        Button cancel_button = findViewById(R.id.cancel_edit_instrument_button);
        Button ok_button = findViewById(R.id.ok_edit_instrument_button);

        //Recyclerview
        RecyclerView cat_rview = findViewById(R.id.cat_edit_instrument_rview);

        final ArrayList<String> inslist = new ArrayList<>();
        final ArrayList<String> sections = getIntent().getStringArrayListExtra("sectionlist");
        if (sections != null) {
            ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, sections);
            section_spin.setAdapter(sectionAdapter);
        }

        final ArrayList<Instrument> temp = (ArrayList<Instrument>) getIntent().getSerializableExtra("instrumentlist");
        if (temp != null) {
            for (Instrument ins : temp) {
                inslist.add(ins.getName());
            }
            if (inslist != null) {
                ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, inslist);
                pick_spin.setAdapter(memberAdapter);
            }
        }
        //pick spin logic
        pick_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (inslist != null) {
                    for (String str : inslist) {
                        int count = 0;
                        if (str == pick_spin.getSelectedItem().toString()) {
                            name_edittext.setText(temp.get(count).getName());
                            id_display_textview.setText(Double.toString(temp.get(count).getId()));
                            if (sections != null) {
                                int pos = sectionCount(str, sections);
                                section_spin.setSelection(pos);
                                {
                                    cost_edittext.setText(Double.toString(temp.get(count).getCost()));


                                }
                                count++;
                            }
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //section spinner logic
        section_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                section = section_spin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                section = "";
            }
        });

        //Category spinner logic
        cat_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                category = (Category) section_spin.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                section = "";
            }
        });


        //cancel button logic
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cat_spin.setSelection(0);
                section_spin.setSelection(0);
                name_edittext.setText("");
                cost_edittext.setText("");
                finish();
            }
        });

        //ok button
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                double cost;
                name = name_edittext.getText().toString();

                try {
                    cost = Double.parseDouble(cost_edittext.getText().toString());
                    RentableFactory factory = new RentableFactory();
                    Instrument ins = (Instrument) factory.buildRentable("Instrument");
                    ins.setName(name);
                    ins.setId(Integer.parseInt(id_display_textview.getText().toString()));
                    ins.setSection(section);
                    ins.setCost(Double.parseDouble(cost_edittext.getText().toString()));

                    Intent intent = new Intent();
                    intent.putExtra("instrument", ins);
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (NumberFormatException ex) {
                    cost = 0;
                }


            }

        });

        //section spinner logic
        section_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                section = section_spin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                section = "";
            }
        });
    }

    private int sectionCount(String str, ArrayList<String> sectionlist)
    {
        int count = 0;
        for(String strin : sectionlist)
        {
            if(str.equals(strin))
            {
                return count;
            }
        }
        return -1;
    }
}
