package com.example.brmm;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class delete_category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);

        //Textviews
        TextView header = findViewById(R.id.delete_category_header);
        TextView name_textview = findViewById(R.id.category_delete_category_textview);

        //dropdowns
        final Spinner cat_spin = findViewById(R.id.category_delete_category_dropdown);

        //Buttons
        Button cancel_button = findViewById(R.id.cancel_delete_category_button);
        Button ok_button = findViewById(R.id.ok_delete_category_button);

        Intent intent = new Intent();

        //Instruments
        final ArrayList<Instrument> instruments = (ArrayList<Instrument>) getIntent().getSerializableExtra("instrumentlist");
        for (Instrument ins : instruments){
            System.out.println(ins.getName());
            System.out.println(ins.getCategory());
        }



        //sets up category spinner
        final ArrayList<Category> catlist = (ArrayList<Category>) getIntent().getSerializableExtra("categorylist");
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
            }
        });


        //removes the category
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deletable = true;

                final Category removeCat = (Category) cat_spin.getSelectedItem();
                for (Instrument ins: instruments) {
                    if (ins.getCategory().getName().equals(removeCat.getName()))
                        deletable = false;
                        break;
                }

                System.out.println(deletable);
                if (removeCat.getSuperCategory() == null) {
                    makeToast1();
                } else if (deletable == false) {
                    makeToast2();
                }
                else
                    removeCategory(removeCat,catlist);
            }
        });


        //returns to main screen
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    };

    //removes the category
    public void removeCategory(Category removeCat, ArrayList<Category> catlist) {

        Intent intent = new Intent();
        if (removeCat != null){
            catlist.remove(removeCat);
        }
        if (removeCat.getSuperCategory() == null) {
        }
        else {
            for (Category cat : catlist) {
                if (cat.getSuperCategory() == removeCat)
                    cat.getSuperCategory().setSuperCategory(cat.getSuperCategory());
            }
            intent.putExtra("category", catlist);
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    public void makeToast1(){
        Toast toast = Toast.makeText(this, "You cannot delete the top category", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void makeToast2(){
        Toast toast = Toast.makeText(this, "You cannot delete a Category that currently contains instuments", Toast.LENGTH_SHORT);
        toast.show();
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
