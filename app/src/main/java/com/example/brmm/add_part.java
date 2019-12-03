package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class add_part extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);

        //textviews
        /*
        TextView header = findViewById(R.id.add_parts_header);
        TextView name_textview = findViewById(R.id.name_add_part_textview);
        TextView sn_textview = findViewById(R.id.sn_add_part_textview);
        TextView cost_textview = findViewById(R.id.cost_add_part_textview)
        TextView compwith_textview = findViewById(R.id.compwith_add_parts_textview);
        TextView add_existing_textview = findViewById(R.id.add_existing_add_part_textview);
        */

        //edittexts
        EditText name_edittext = findViewById(R.id.name_add_part_edittext);
        EditText sn_edittext = findViewById(R.id.sn_add_part_edittext);
        EditText cost_edittext = findViewById(R.id.cost_add_part_edittext);

        //compwith objects
        Spinner compwith_spin = findViewById(R.id.compwith_add_part_dropdown);
        RecyclerView compwith_rview = findViewById(R.id.compwith_add_part_rview);

        //buttons
        Button ok_button = findViewById(R.id.ok_add_part_button);
        Button cancel_button = findViewById(R.id.cancel_add_part_button);
        Button add_cat_button = findViewById(R.id.add_cat_add_part_button);

        //Add existing dropdown
        Spinner add_existing_spin = findViewById(R.id.add_existing_add_part_dropdown);
    }
}
