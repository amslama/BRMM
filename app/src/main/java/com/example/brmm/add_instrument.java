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

public class add_instrument extends AppCompatActivity {
    private RentableFactory factory = new RentableFactory();
    private String section;
    private Category category;
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

        final ArrayList<String> sectionlist = getIntent().getStringArrayListExtra("sectionlist");

        //add existing spinner logic
        add_existing_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (instrumentlist != null) {
                    int count = 0;

                    for (String str : instrumentlist) {
                        if (str == add_existing_spin.getSelectedItem().toString()) {
                            name_edittext.setText(temp.get(count).getName());
                            id_textview.setText(String.format(Locale.US, "%d", temp.get(count).getId()+1));
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


        //section spinner logic
        section_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = (Category) section_spin.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                section = "";
            }
        });


        //cancel button logic
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cat_spin.setSelection(0);
                add_existing_spin.setSelection(0);
                section_spin.setSelection(0);
                name_edittext.setText("");
                cost_edittext.setText("");
                finish();
            }
        });

        //ok button
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                double cost;
                if (!name_edittext.getText().toString().isEmpty() && !cost_edittext.getText().toString().isEmpty()) {

                    try {
                        cost = Double.parseDouble(cost_edittext.getText().toString());
                        if(cost >=0) {
                            name = name_edittext.getText().toString();
                            RentableFactory factory = new RentableFactory();
                            Rentable instrument = (Instrument) factory.buildRentable("Instrument");
                            instrument.setCost(cost);
                            instrument.setName(name);
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
}
