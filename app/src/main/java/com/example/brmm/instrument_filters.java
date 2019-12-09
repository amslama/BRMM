package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class instrument_filters extends AppCompatActivity {

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

        //sets up section spinner
        final ArrayList<String> sections = getIntent().getStringArrayListExtra("sectionlist");
        if (sections != null) {
            String section = "Don't filter by Section";
            sections.add(0,section);
            ArrayAdapter<String> secAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sections);
            secAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            section_spin.setAdapter(secAdapter);
        }

        //logic for section spinner selection
        section_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String section = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String section = "";
            }
        });



        //sets up category spinner
        ArrayList<Category> catlist = (ArrayList<Category>) getIntent().getSerializableExtra("categorylist");
        if (catlist != null) {
            Category cat = new Category(null);
            cat.setName("Don't filter by Category");
            catlist.add(0,cat);
            ArrayAdapter<Category> catAdapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, catlist);
            catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cat_spin.setAdapter(catAdapter);

        }


        //logic for category spinner selection
        cat_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category category = (Category) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //applies filters
        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String owner;
                 String section;
                 String name;
                 Category category;
                 double cost;
                 int id;


                owner = owner_edittext.getText().toString();
                name = name_edittext.getText().toString();


                section = section_spin.getSelectedItem().toString();
                category = (Category) cat_spin.getSelectedItem();

                try {
                    id = Integer.parseInt(id_edittext.getText().toString());
                } catch (NumberFormatException ex) {id = -1;}

                try {
                    cost = Double.parseDouble(cost_edittext.getText().toString());
                } catch (NumberFormatException ex) {cost = -1;}

                Intent thisIntent = new Intent();
                ArrayList<Instrument> instruments = (ArrayList<Instrument>)thisIntent.getSerializableExtra("instrumentlist");

                //If there are no instruments to filter, return to main screen
                if (instruments == null)
                    finish();

                instruments = filterInstrumentInv(null, owner, section, name, category, id, cost);
                thisIntent = new Intent();
                thisIntent.putExtra("instrumentList", instruments);
                setResult(RESULT_OK,thisIntent);
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

    //main method for filtering
    public ArrayList<Instrument> filterInstrumentInv(ArrayList<Instrument> Instruments, String owner, String section, String name, Category category, int id, double cost) {
        ArrayList<Instrument> filter = new ArrayList<>();

        if(!category.getName().equals("Don't filter by Category"))
           filter = filterByCategory(filter, category);


        if (id != -1)
            filter = filterByID(filter, id);


        if (!owner.equals(""))
            filter = filterByOwner(filter, owner);


        if (!name.equals("")) {
            filter = filterByName(filter, name);
        }


        if (!section.equals("Don't filter by Section"))
            filter = filterBySection(filter, section);

        if (cost != -1)
            filter = filterByCost(filter, cost);


        return filter;

    }


    //filters by a specific section
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

    //filters by a specific category
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

    //filters by a specific cost
    public ArrayList<Instrument> filterByCost(ArrayList<Instrument> Instruments, double cost) {
        ArrayList<Instrument> filter = new ArrayList<>();
        for (Instrument Instrument : Instruments) {
            if(Instrument.getCost() == cost)
                filter.add(Instrument);
        }
        return filter;
    }

    //filters by a specific ID
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

    //filters by a specific name
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


    //filters by a owner
    public ArrayList<Instrument> filterByOwner(ArrayList<Instrument> Instruments, String owner) {
        ArrayList<Instrument> filter = new ArrayList<>();
        for (Instrument Instrument : Instruments) {
            if(Instrument.getCurrentOwner().equals(owner))
                filter.add(Instrument);
        }
        return filter;
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
