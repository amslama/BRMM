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

                final Category removeCat = (Category) cat_spin.getSelectedItem();

                //displays dialog to make sure user is sure of decision
                AlertDialog.Builder builder = new AlertDialog.Builder(delete_category.this);
                builder.setMessage("Warning, Deleting a Category will uncategorize all instruments with that category. Do you still wish to continue?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removeCategory(removeCat, catlist);
                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }) ;

                AlertDialog alert = builder.create();
                alert.setTitle("Alert");
                alert.show();

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
            System.out.println("You cannot remove the top Category");
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
