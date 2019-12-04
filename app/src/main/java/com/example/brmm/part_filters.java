package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class part_filters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_filters);

        //Textviews
        TextView header = findViewById(R.id.part_filters_header);
        TextView name_textview = findViewById(R.id.name_part_filters_textview);
        TextView compwith_textview = findViewById(R.id.name_part_filters_textview);
        TextView sn_textview = findViewById(R.id.name_part_filters_textview);
        TextView cost_textview = findViewById(R.id.name_part_filters_textview);

        //Edittexts
        EditText name_edittext = findViewById(R.id.name_part_filters_edittext);
        EditText sn_edittext = findViewById(R.id.sn_part_filters_edittext);
        EditText cost_edittext = findViewById(R.id.cost_part_filters_edittext);

        //Dropdowns
        final Spinner compwith_spin = findViewById(R.id.compwith_part_filters_dropdown);

        //Recyclerview
        final RecyclerView compwith_rview = findViewById(R.id.compwith_part_filters_rview);

        //Buttons
        final Button add_button = findViewById(R.id.add_part_filters_button);
        final Button cancel_button = findViewById(R.id.cancel_part_filters_button);
        final Button apply_button = findViewById(R.id.apply_part_filters_button);


    }



}
