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
import java.util.Locale;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class add_category extends AppCompatActivity {
    Category category;
    Category newCat = new Category();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        //Textviews
        TextView header = findViewById(R.id.add_category_header);
        TextView name_textview = findViewById(R.id.name_add_category_textview);

        //Edittexts
        final EditText name_edittext = findViewById(R.id.name_add_category_edittext);

        //Spinners
        final Spinner cat_spin = findViewById(R.id.category_add_dropdown);

        //Buttons
        Button cancel_button = findViewById(R.id.cancel_add_category_button);
        Button ok_button = findViewById(R.id.ok_add_category_button);

        //review this
        final ArrayList<String> categorylist = new ArrayList<>();
        final ArrayList<Category> temp = (ArrayList<Category>) getIntent().getSerializableExtra("Categorylist");
        if (temp != null) {
            for (Category cat : temp) {

                categorylist.add(cat.getName());
            }
            if (categorylist != null) {
                ArrayAdapter<String> catAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, categorylist);
                cat_spin.setAdapter(catAdapter);
            }
        }

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;

                name = name_edittext.getText().toString();

                Intent intent = new Intent();
                //intent.putExtra("category", category);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_edittext.setText("");
                finish();
            }
        });

        //Category spinner logic
        cat_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = (Category) cat_spin.getSelectedItem();
                newCat.setSuperCategory(category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newCat = null;
            }
        });

    }
}