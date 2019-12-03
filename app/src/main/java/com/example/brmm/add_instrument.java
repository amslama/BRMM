package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class add_instrument extends AppCompatActivity {

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
        EditText name_edittext = findViewById(R.id.name_add_instrument_editText);
        EditText cost_edittext = findViewById(R.id.cost_add_instrument_edittext);

        //Dropdowns
        Spinner ae_spin = findViewById(R.id.add_existing_add_instrument_dropdown);
        Spinner section_spin = findViewById(R.id.section_add_instrument_dropdown);
        Spinner cat_spin = findViewById(R.id.cat_add_instrument_dropdown);

        //Buttons
        Button add_cat_button = findViewById(R.id.add_cat_add_instrument_button);
        Button cancel_button = findViewById(R.id.cancel_add_instrument_button);
        Button ok_button = findViewById(R.id.ok_add_instrument_button);

        //Recyclerview
        RecyclerView cat_rview = findViewById(R.id.cat_add_instrument_rview);
    }
}
