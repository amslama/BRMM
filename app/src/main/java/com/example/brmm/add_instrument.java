package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class add_instrument extends AppCompatActivity {
   private RentableFactory factory = new RentableFactory();
   private String name;
   private double cost;
   private String section;
   private Category category;
   private Instrument instrument;
   private int id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instrument);

        //Textviews
        /*
        TextView header_textview = findViewById(R.id.add_instrument_header);
        TextView ae_textview = findViewById(R.id.add_existing_add_instrument_textview);
        TextView name_textview = findViewById(R.id.name_add_instrument_textview);
        TextView id_textview = findViewById(R.id.id_add_instrument_textview);
        TextView id_display_textview = findViewById(R.id.id_add_instrument_display_textview);
        TextView section_textview = findViewById(R.id.section_add_instrument_textview);
        TextView cost_textview = findViewById(R.id.cost_add_part_textview);
        TextView cat_textview = findViewById(R.id.categories_add_instrument_textview);
        */

        //Edittexts
        final EditText name_edittext = findViewById(R.id.name_add_instrument_editText);
        final EditText cost_edittext = findViewById(R.id.cost_add_instrument_edittext);

        //Dropdowns
        final Spinner ae_spin = findViewById(R.id.add_existing_add_instrument_dropdown);
        final Spinner section_spin = findViewById(R.id.section_add_instrument_dropdown);
        final Spinner cat_spin = findViewById(R.id.cat_add_instrument_dropdown);

        //Buttons
        Button add_cat_button = findViewById(R.id.add_cat_add_instrument_button);
        Button cancel_button = findViewById(R.id.cancel_add_instrument_button);
        Button ok_button = findViewById(R.id.ok_add_instrument_button);

        //Recyclerview
        RecyclerView cat_rview = findViewById(R.id.cat_add_instrument_rview);

        //add existing spinner logic
        ae_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                instrument = (Instrument) ae_spin.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                section = "";
            }
        });

        //section spinner logic
        section_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                section =section_spin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                section = "";
            }
        });

        //Category spinner logic
        cat_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = (Category)section_spin.getSelectedItem();
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
                ae_spin.setSelection(0);
                section_spin.setSelection(0);
                name_edittext.setText("");
                cost_edittext.setText("");
            }
        });

        //ok button
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = name_edittext.getText().toString();
                cost = Double.parseDouble(cost_edittext.getText().toString());

            }
        });
    }
}
