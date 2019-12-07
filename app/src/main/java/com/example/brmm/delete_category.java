package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class delete_category extends AppCompatActivity {
    Category removeCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);

        //Textviews
        TextView header = findViewById(R.id.delete_category_header);
        TextView name_textview = findViewById(R.id.category_delete_category_textview);

        //dropdowns
        final Spinner category_spin = findViewById(R.id.category_delete_category_dropdown);

        //Buttons
        Button cancel_button = findViewById(R.id.cancel_delete_category_button);
        Button ok_button = findViewById(R.id.ok_delete_category_button);

        Intent intent = new Intent();
        final ArrayList<String> categorylist = new ArrayList<>();
        final ArrayList<Category> temp = (ArrayList<Category>) getIntent().getSerializableExtra("categorylist");
        if (temp != null) {
            for (Category cat : temp) {

                categorylist.add(cat.getName());
            }
            if (categorylist != null) {
                ArrayAdapter<String> catAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, categorylist);
                category_spin.setAdapter(catAdapter);
            }
        }

        category_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                removeCat = (Category) category_spin.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                removeCat = null;
            }
        });

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (removeCat != null){
                    temp.remove(removeCat);
                }
                if (removeCat.getSuperCategory() == null) {
                    System.out.println("You cannot remove the top Category");
                }
                else {
                    for (Category cat : temp) {
                        if (cat.getSuperCategory() == removeCat)
                            cat.getSuperCategory().setSuperCategory(cat.getSuperCategory());
                    }
                    intent.putExtra("category", categorylist);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    };
}
