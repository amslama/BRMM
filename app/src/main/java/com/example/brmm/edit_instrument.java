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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class edit_instrument extends AppCompatActivity {
    private String section;
    private int count;
    private boolean addCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_instrument);


        //Textviews
        final TextView header_textview = findViewById(R.id.edit_instrument_header);
        TextView name_textview = findViewById(R.id.name_edit_instrument_textview);
        TextView id_textview = findViewById(R.id.id_edit_instrument_textview);
        final TextView id_display_textview = findViewById(R.id.id_edit_instrument_display_textview);
        TextView section_textview = findViewById(R.id.section_edit_instrument_textview);
        final TextView cost_textview = findViewById(R.id.cost_edit_part_textview);
        TextView cat_textview = findViewById(R.id.cat_edit_instrument_textview);


        //Edittexts
        final EditText name_edittext = findViewById(R.id.name_edit_instrument_editText);
        final EditText cost_edittext = findViewById(R.id.cost_edit_instrument_edittext);

        //Dropdowns
        final Spinner section_spin = findViewById(R.id.section_edit_instrument_dropdown);
        final Spinner cat_spin = findViewById(R.id.cat_edit_instrument_dropdown);
        final Spinner pick_spin = findViewById(R.id.pick_edit_instrument_dropdown);

        //Buttons
        Button add_cat_button = findViewById(R.id.add_cat_edit_instrument_button);
        Button cancel_button = findViewById(R.id.cancel_edit_instrument_button);
        Button ok_button = findViewById(R.id.ok_edit_instrument_button);

        //Recyclerview
        RecyclerView cat_rview = findViewById(R.id.cat_edit_instrument_rview);

        //don't add category
        addCat = false;

        //sets up sectionlist spinner
        final ArrayList<String> sections = getIntent().getStringArrayListExtra("sectionlist");
        if (sections != null) {
            ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, sections);
            section_spin.setAdapter(sectionAdapter);
        }

        //sets up instrument list spinner
        final ArrayList<String> inslist = new ArrayList<>();
        final ArrayList<Instrument> temp = (ArrayList<Instrument>) getIntent().getSerializableExtra("instrumentlist");
        if (temp != null) {
            for (Instrument ins : temp) {
                inslist.add(ins.getName());
            }
            if (inslist != null) {
                ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, inslist);
                pick_spin.setAdapter(memberAdapter);
            }
        }

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

        //pick spin logic
        pick_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (inslist != null) {
                    count = 0;
                    for (String str : inslist) {
                        if (str == pick_spin.getSelectedItem().toString()) {
                            name_edittext.setText(temp.get(count).getName());
                            id_display_textview.setText(Integer.toString(temp.get(count).getId()));
                            cost_edittext.setText(Double.toString(temp.get(count).getCost()));
                            if (sections != null) {
                                int pos = sectionCount(str, sections);
                                section_spin.setSelection(pos);
                            }
                            Intent intent = getIntent();
                            intent.putExtra("count", count);
                            break;
                        }
                        count++;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        //section spinner logic
        section_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
                section = section_spin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                section = "";
            }
        });

        //Category spinner logic
        cat_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,
                                       long id) {
               Category category = (Category) section_spin.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //ok button
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    RentableFactory factory = new RentableFactory();
                    Instrument ins = (Instrument) factory.buildRentable("Instrument");
                    String name = name_edittext.getText().toString();
                    double cost = Double.parseDouble(cost_edittext.getText().toString());
                    int id = Integer.parseInt(id_display_textview.getText().toString());
                    Category category = (Category) cat_spin.getSelectedItem();
                    ins.setName(name);
                    ins.setSection(section);
                    ins.setCost(cost);
                    ins.setId(id);
                   // if (deleteCat == true)
                        ins.setCategory(category);


                    Intent intent = getIntent();
                    intent.putExtra("instrument", ins);
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (NumberFormatException ex) {
                    String invalid = "Please enter a real number";
                    Toast incomplete_toast = Toast.makeText(getApplicationContext(), invalid, Toast.LENGTH_LONG);
                    incomplete_toast.show();
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

    private int sectionCount(String str, ArrayList<String> sectionlist) {
        int count = 0;
        for (String strin : sectionlist) {
            if (str.equals(strin)) {
                return count;
            }
        }
        return -1;
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

