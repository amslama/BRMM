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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

public class add_category extends AppCompatActivity {
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

        //sets up category spinner
        ArrayList<Category> catlist = (ArrayList<Category>) getIntent().getSerializableExtra("categorylist");
        if (catlist != null) {
            ArrayAdapter<Category> catAdapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, catlist);
            catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cat_spin.setAdapter(catAdapter);
        }

        //logic for category spinner
        cat_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category category = (Category) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Category category = null;
            }
        });

        //add the new category
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                Category category;

                category = (Category) cat_spin.getSelectedItem();
                name = name_edittext.getText().toString();

                Category newCategory = new Category(category);
                newCategory.setName(name);

                Intent intent = new Intent();
                intent.putExtra("category", newCategory);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        //goes back to main activity
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    //Timeout Timer
    private Timer timer;

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        timer.cancel();
        timer.purge();
        timer = new Timer();
        TimerTask timeOutTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timeout from Category Interaction");
                timeOut(); }
        };
        timer.schedule(timeOutTask, main_screen.logoutTime);
    }
    //sets timer to null when no longer on screen
    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        timer.purge();
    }
    //resets timer when resuming activity
    @Override
    protected void onResume() {
        super.onResume();
        timer = new Timer();
        TimerTask timeOutTask = new TimerTask() {
            @Override
            public void run() {
                timeOut(); }
        };
        timer.schedule(timeOutTask, main_screen.logoutTime);
    }
    //return to main screen
    private void timeOut(){
        Intent intent = new Intent();
        intent.putExtra("timeOut", true);
        setResult(RESULT_OK,intent);
        finish();
    }

}