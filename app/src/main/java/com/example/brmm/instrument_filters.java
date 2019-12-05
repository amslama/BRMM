package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class instrument_filters extends AppCompatActivity {
    private String owner;
    private String section;
    private String name;
    private Category category;
    private double cost;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_filters);


        //Textviews
        TextView header = findViewById(R.id.instrument_filters_header);
        TextView name_textview = findViewById(R.id.name_instrument_filters_textview);
        TextView cost_textview = findViewById(R.id.cost_intrument_filters_textview);
        TextView id_textview = findViewById(R.id.id_instrument_filters_textview);
        TextView owner_textview = findViewById(R.id.owner_instrument_filters_textview);
        TextView section_textview = findViewById(R.id.section_instrument_filters_textview);
        TextView cat_textview = findViewById(R.id.cat_instrument_filters_textview);

        //Edittexts
        final EditText id_edittext = findViewById(R.id.id_instrument_filters_edittext);
        final EditText name_edittext = findViewById(R.id.name_instrument_filters_edittext);
        final EditText cost_edittext = findViewById(R.id.cost_instrument_filters_edittext);
        final EditText owner_edittext = findViewById(R.id.owner_instrument_filters_edittext);

        //Dropdowns
        final Spinner section_spin = findViewById(R.id.section_instrument_filters_dropdown);
        final Spinner cat_spin = findViewById(R.id.cat_instrument_filters_dropdown);

        //Buttons
        final Button apply_button = findViewById(R.id.ok_instrument_filters_button);
        final Button cancel_button = findViewById(R.id.cancel_instrument_filters_button);

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_edittext.setText("");
                cost_edittext.setText("");
                owner_edittext.setText("");
                name_edittext.setText("");
                section_spin.setSelection(0);
                cat_spin.setSelection(0);
            }
        });


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

        cat_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = (Category) cat_spin.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                category = null;
            }
        });

        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                owner = owner_edittext.getText().toString();
                name = name_edittext.getText().toString();
                id = Integer.parseInt(id_edittext.getText().toString());

                cost = Double.parseDouble(cost_edittext.getText().toString());

                //  ArrayList<Instrument> instruments = (ArrayList<Instrument>)data.getSerializableExtra("Instrumentlist");
                filterInstrumentInv(null, owner, section, name, category, id, cost);
                Intent thisIntent = new Intent();
                //  intent.putExtra(instrumentList", instruments);
                //setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    public ArrayList<Instrument> filterInstrumentInv(ArrayList<Instrument> Instruments, String owner, String section, String name, Category category, int id, double cost) {
        ArrayList<Instrument> filter = new ArrayList<>();

        if(category != null)
            filter = filterByCategory(filter, category);


        if (id != 0)
            filter = filterByID(filter, id);


        if (!owner.equals(""))
            filter = filterByOwner(filter, owner);


        if (!name.equals("")) {
            filter = filterByName(filter, name);
        }

        if (category != null)
            filter =filterByCategory(filter, category);

        if (!section.equals(""))
            filter = filterBySection(filter, section);

        if (cost != 0)
            filter = filterByCost(filter, cost);




        return filter;

    }



    public ArrayList<Instrument> filterBySection(ArrayList<Instrument> Instruments, String section) {
        ArrayList<Instrument> filter = new ArrayList<>();
        for (Instrument Instrument : Instruments) {
            if (Instrument instanceof Instrument) {
                if (((Instrument) Instrument).getSection().equals(section))
                    filter.add(Instrument);
            }
        }
        return filter;
    }

    public ArrayList<Instrument> filterByCategory(ArrayList<Instrument> Instruments, Category category) {
        ArrayList<Instrument> filter = new ArrayList<>();
        for (Instrument Instrument : Instruments) {
            if (Instrument instanceof Instrument) {
                if (((Instrument) Instrument).getCategory().getSuperCategories().contains(category))
                    filter.add(Instrument);
            }
        }
        return filter;
    }

    public ArrayList<Instrument> filterByCost(ArrayList<Instrument> Instruments, double cost) {
        ArrayList<Instrument> filter = new ArrayList<>();
        for (Instrument Instrument : Instruments) {
            if(Instrument.getCost() == cost)
                filter.add(Instrument);
        }
        return filter;
    }

    public ArrayList<Instrument> filterByID(ArrayList<Instrument> Instruments, int id) {
        ArrayList<Instrument> filter = new ArrayList<>();
        for (Instrument Instrument : Instruments) {
            if(Instrument.getId() == id) {
                filter.add(Instrument);
                break;
            }
        }
        return filter;
    }

    public ArrayList<Instrument> filterByName(ArrayList<Instrument> Instruments, String name) {
        ArrayList<Instrument> filter = new ArrayList<>();
        for (Instrument Instrument : Instruments) {
            if(Instrument.getName().equals(name)) {
                filter.add(Instrument);
                break;
            }
        }
        return filter;
    }


    public ArrayList<Instrument> filterByOwner(ArrayList<Instrument> Instruments, String owner) {
        ArrayList<Instrument> filter = new ArrayList<>();
        for (Instrument Instrument : Instruments) {
            if(Instrument.getCurrentOwner().equals(owner))
                filter.add(Instrument);
        }
        return filter;
    }

}
