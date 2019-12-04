package com.example.brmm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class main_screen extends AppCompatActivity {

    private boolean facultyRights = false;
    private RentableInventory rent_inv;
    private BandMemberInventory member_inv;

    //Dropdown
    private Spinner inv_spin;

    //Top buttons
    private Button filter_button;
    private Button logout_button;

    //buttons for all
    private Button add_button;
    private Button remove_button;

    //buttons for rentables
    private Button edit_rentable_button;

    //buttons for instruments
    private Button notes_button;
    private Button checkout_button;

    //buttons for bandmember
    private Button edit_member_button;
    private Button set_lead_button;
    private Button add_section_button;
    private Button delete_section_button;

    //Recyclerview
    private RecyclerView inv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        instantiateButtons();
        setBandmemberButtons();
        oneTimeListeners();

        Intent intent = getIntent();
        if((ArrayList<Instrument>)intent.getSerializableExtra("INSTRUMENT")!=null) {
            for (Instrument instrument : (ArrayList<Instrument>) intent.getSerializableExtra("INSTRUMENT")) {
                rent_inv.addInstrument(instrument);
            }
        }
        if((ArrayList<Part>)intent.getSerializableExtra("PART")!=null) {
            for (Part part : (ArrayList<Part>) intent.getSerializableExtra("PART")) {
                rent_inv.addPart(part);
            }
        }
        if((ArrayList<Student>)intent.getSerializableExtra("STUDENT")!=null) {
            for (Student student : (ArrayList<Student>) intent.getSerializableExtra("STUDENT")) {
                System.out.println("----------------------------------------------------------------------------------");
                member_inv.addBandMember(student);
            }
        }
        if( (ArrayList<Faculty>)intent.getSerializableExtra("INSTRUMENT")!=null) {
            for (Faculty faculty : (ArrayList<Faculty>) intent.getSerializableExtra("INSTRUMENT")) {
                member_inv.addBandMember(faculty);
            }
        }
      //  add_button.setText(member_inv.size());


    }

    private void instantiateButtons()
    {
        member_inv = new BandMemberInventory();
        rent_inv = new RentableInventory();
        inv_spin = findViewById(R.id.inventory_main_screen_dropdown);
        filter_button = findViewById(R.id.filter_main_screen_button);
        logout_button = findViewById(R.id.logout_main_screen_button);
        add_button = findViewById(R.id.add_main_screen_button);
        remove_button = findViewById(R.id.remove_main_screen_button);
        edit_rentable_button = findViewById(R.id.edit_rentable_main_screen_button);
        notes_button = findViewById(R.id.edit_note_main_screen_button);
        checkout_button = findViewById(R.id.checkout_main_screen_button);
        edit_member_button = findViewById(R.id.edit_member_main_screen_button);
        set_lead_button = findViewById(R.id.add_section_lead_main_screen_button);
        add_section_button = findViewById(R.id.add_section_main_screen_button);
        delete_section_button = findViewById(R.id.delete_section_main_screen_button);
        inv_view = findViewById(R.id.item_list_main_screen_rview);
    }

    private void setPartsButtons() {
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), part_filters.class);
                startActivity(openFilter);
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), add_part.class);
                startActivity(openFilter);
                startActivityForResult(openFilter,0);
            }
        });
        edit_rentable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), edit_part.class);
                startActivity(openFilter);
            }
        });

        PartRecyclerAdapter adapter = new PartRecyclerAdapter((rent_inv.getPartList()));
        inv_view.setLayoutManager(new LinearLayoutManager(this));
        inv_view.setAdapter(adapter);

        edit_rentable_button.setVisibility(View.VISIBLE);
        edit_member_button.setVisibility(View.INVISIBLE);
        set_lead_button.setVisibility(View.INVISIBLE);
        checkout_button.setVisibility(View.INVISIBLE);
        notes_button.setVisibility(View.INVISIBLE);
        delete_section_button.setVisibility(View.INVISIBLE);
        add_section_button.setVisibility(View.INVISIBLE);


    }

    private void setInstrumentButtons() {
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), instrument_filters.class);
                startActivity(openFilter);
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), add_instrument.class);
                startActivity(openFilter);
                startActivityForResult(openFilter,1);
            }
        });

        edit_rentable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), edit_instrument.class);
                startActivity(openFilter);
            }
        });

        InstrumentRecyclerAdapter adapter = new InstrumentRecyclerAdapter(rent_inv.getInstrumentList());
        inv_view.setLayoutManager(new LinearLayoutManager(this));
        inv_view.setAdapter(adapter);

        edit_rentable_button.setVisibility(View.VISIBLE);
        edit_member_button.setVisibility(View.INVISIBLE);
        set_lead_button.setVisibility(View.INVISIBLE);
        checkout_button.setVisibility(View.VISIBLE);
        notes_button.setVisibility(View.VISIBLE);
        delete_section_button.setVisibility(View.INVISIBLE);
        add_section_button.setVisibility(View.INVISIBLE);

    }

    private void setBandmemberButtons() {
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), bandmember_filters.class);
                startActivity(openFilter);
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), add_member.class);
                startActivityForResult(openFilter,2);
            }
        });

        MemberRecyclerAdapter adapter = new MemberRecyclerAdapter((member_inv.getBandMembers()));
        inv_view.setLayoutManager(new LinearLayoutManager(this));
        inv_view.setAdapter(adapter);

        edit_rentable_button.setVisibility(View.INVISIBLE);
        edit_member_button.setVisibility(View.VISIBLE);
        set_lead_button.setVisibility(View.VISIBLE);
        checkout_button.setVisibility(View.INVISIBLE);
        notes_button.setVisibility(View.INVISIBLE);
        delete_section_button.setVisibility(View.VISIBLE);
        add_section_button.setVisibility(View.VISIBLE);


    }

    private void oneTimeListeners() {
        MemberRecyclerAdapter adapter = new MemberRecyclerAdapter((member_inv.getBandMembers()));
        inv_view.setLayoutManager(new LinearLayoutManager(this));
        inv_view.setAdapter(adapter);
        checkout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), checkout_instrument.class);
                startActivity(openFilter);
            }
        });
        notes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), edit_notes.class);
                startActivity(openFilter);
            }
        });
        delete_section_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), delete_section.class);
                startActivity(openFilter);
            }
        });
        add_section_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), add_section.class);
                startActivity(openFilter);
            }
        });
        edit_member_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), edit_member.class);
                startActivity(openFilter);
            }
        });
        set_lead_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), set_lead.class);
                startActivity(openFilter);
            }
        });

        inv_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (inv_spin.getSelectedItem().toString().equals("Parts"))
                {
                    setPartsButtons();
                }
                if (inv_spin.getSelectedItem().toString().equals("Instruments"))
                {
                    setInstrumentButtons();
                }

                if (inv_spin.getSelectedItem().toString().equals("Band Members"))
                {
                    setBandmemberButtons();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //instantiates logout button
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("member",member_inv);
                intent.putExtra("rentable",rent_inv);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                Part part = (Part)data.getSerializableExtra("part");
                rent_inv.addPart(part);

            }
        }
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                Instrument instrument = (Instrument)data.getSerializableExtra("instrument");
                rent_inv.addInstrument(instrument);

            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                BandMember member = (BandMember)data.getSerializableExtra("member");
                member_inv.addBandMember(member);

            }
        }
    }
}
