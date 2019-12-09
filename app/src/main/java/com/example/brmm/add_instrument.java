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
import java.util.Timer;
import java.util.TimerTask;

public class add_instrument extends AppCompatActivity {
    private RentableFactory factory = new RentableFactory();
    private String section;
    private boolean addCat;

    private Instrument instrument;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instrument);
        //Textviews
        final TextView id_textview = findViewById(R.id.id_add_instrument_display_textview);

        //Edittexts
        final EditText name_edittext = findViewById(R.id.name_add_instrument_editText);
        final EditText cost_edittext = findViewById(R.id.cost_add_instrument_edittext);

        //Dropdowns
        final Spinner add_existing_spin = findViewById(R.id.add_existing_add_instrument_dropdown);
        final Spinner section_spin = findViewById(R.id.section_add_instrument_dropdown);
        final Spinner cat_spin = findViewById(R.id.cat_add_instrument_dropdown);

        //Buttons
        Button add_cat_button = findViewById(R.id.add_cat_add_instrument_button);
        Button cancel_button = findViewById(R.id.cancel_add_instrument_button);
        Button ok_button = findViewById(R.id.ok_add_instrument_button);

        //Recyclerview
        RecyclerView cat_rview = findViewById(R.id.cat_add_instrument_rview);

        //don't add category
        addCat = false;

        //sets instrument spinner
        final ArrayList<String> instrumentlist = new ArrayList<>();
        final ArrayList<Instrument> temp = (ArrayList<Instrument>) getIntent().getSerializableExtra("instrumentlist");
        if (temp != null) {
            for (Instrument ins : temp) {

                instrumentlist.add(ins.getName());
            }
            if (instrumentlist != null) {
                ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, instrumentlist);
                add_existing_spin.setAdapter(memberAdapter);
            }
        }

        //adds no section option if no sections exist
         ArrayList<String> secInput= getIntent().getStringArrayListExtra("sectionlist");
        if (secInput == null) {
            secInput = new ArrayList<>();
            secInput.add("No Existing Sections");
        }
        final ArrayList<String> sectionlist = secInput;

        //add existing spinner logic
        add_existing_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (instrumentlist != null) {
                    int count = 0;

                    for (String str : instrumentlist) {
                        if (str == add_existing_spin.getSelectedItem().toString()) {
                            name_edittext.setText(temp.get(count).getName());
                            id_textview.setText(String.format(Locale.US, "%d", temp.get(temp.size()-1).getId()+1));
                            cost_edittext.setText(String.format(Locale.US, "%.2f", temp.get(count).getCost()));
                            int sectioncount = sectionCount(sectionlist, temp.get(count).getSection());
                            if (sectioncount >= 0) {
                                section_spin.setSelection(sectioncount);
                            }


                        }
                        count++;
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


        //section adapter
        final ArrayAdapter<String> secAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sectionlist);
        secAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        section_spin.setAdapter(secAdapter);

        //section spinner logic
        section_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                section = section_spin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        //If no categories, make sure instrument cannot have a category
        ArrayList<Category> input = (ArrayList<Category>) getIntent().getSerializableExtra("categorylist");
        if (input == null) {
            input = new ArrayList<>();
            Category category = new Category(null);
            category.setName("No Existing Categories");
            input.add(category);
        }

        //sets up category spinner
        final ArrayList<Category> catlist = input;
        ArrayAdapter<Category> catAdapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, catlist);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat_spin.setAdapter(catAdapter);

        //Category spinner logic
        cat_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category category = (Category) cat_spin.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });




        //adds an instrument
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                double cost;
                if (!name_edittext.getText().toString().isEmpty() && !cost_edittext.getText().toString().isEmpty()) {

                    try {
                        cost = Double.parseDouble(cost_edittext.getText().toString());
                        if(cost >=0) {
                            Category category = (Category) cat_spin.getSelectedItem();
                            if (!category.getName().equals("No Existing Categories"))
                                category = null;

                            String section = section_spin.getSelectedItem().toString();
                            if (section.equals("No Existing Sections"))
                                section = "";

                            name = name_edittext.getText().toString();

                            RentableFactory factory = new RentableFactory();
                            Rentable instrument = (Instrument) factory.buildRentable("Instrument");

                            instrument.setCost(cost);
                            instrument.setName(name);
                            if (addCat = true)
                                ((Instrument) instrument).setCategory(category);
                            ((Instrument) instrument).setSection(section);

                            Intent intent = new Intent();
                            intent.putExtra("instrument", instrument);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                        else{
                            String invalid = "Please enter a positive number";
                            Toast incomplete_toast = Toast.makeText(getApplicationContext(), invalid, Toast.LENGTH_LONG);
                            incomplete_toast.show();
                        }
                    }  catch (NumberFormatException ex) {
                        String invalid = "Please enter a real number";
                        Toast incomplete_toast = Toast.makeText(getApplicationContext(), invalid, Toast.LENGTH_LONG);
                        incomplete_toast.show();
                    }
                }
            }
        });

        //adds category to instrument
        add_cat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCat = true;
            }
        });

        //returns to main screen
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public int sectionCount(ArrayList<String> sectionlist, String section) {
        int count = 0;
        if (sectionlist == null) {
            return -1;
        }
        for (String str : sectionlist) {
            if (str.equals(section))
                return count;
        }
        return 0;
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
