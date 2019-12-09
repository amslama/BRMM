package com.example.brmm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class main_screen extends AppCompatActivity {

    //boolean for visibility
    private boolean facultyRights = false;

    //Inventories
    private RentableInventory rent_inv;
    private BandMemberInventory member_inv;

    //Lists
    private ArrayList<String> sections;
    private ArrayList<Category> categories;
    private ArrayList<String> ins_concepts;
    private ArrayList<String> part_concepts;

    //BandMember
    private BandMember temp_bm;

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
    private Button add_category_button;
    private Button delete_category_button;

    //Recyclerview
    private RecyclerView inv_view;

    //Timer
    private Timer timer;
    //Time till logout
    public static final int logoutTime = 600000; //10 minutes


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        timer = new Timer();
        System.out.println("Main Activity Created");


        Intent intent = getIntent();

        facultyRights = intent.getBooleanExtra("ISFACULTY", false);

        //Gets information needed to run application from Login Screen
        instantiatelists();

        if ((ArrayList<Instrument>) intent.getSerializableExtra("INSTRUMENT") != null) {
            for (Instrument instrument : (ArrayList<Instrument>) intent.getSerializableExtra("INSTRUMENT")) {
                rent_inv.addInstrument(instrument);
            }
        }
        if ((ArrayList<Part>) intent.getSerializableExtra("PART") != null) {
            for (Part part : (ArrayList<Part>) intent.getSerializableExtra("PART")) {
                rent_inv.addPart(part);
            }
        }
        if ((ArrayList<Student>) intent.getSerializableExtra("STUDENT") != null) {
            for (Student student : (ArrayList<Student>) intent.getSerializableExtra("STUDENT")) {
                System.out.println("----------------------------------------------------------------------------------");
                member_inv.addBandMember(student);
            }
        }
        if ((ArrayList<Faculty>) intent.getSerializableExtra("MEMBER") != null) {
            for (Faculty faculty : (ArrayList<Faculty>) intent.getSerializableExtra("MEMBER")) {
                member_inv.addBandMember(faculty);
            }
        }
        if (intent.getStringArrayListExtra("SECTION") != null) {
            for (String sect :intent.getStringArrayListExtra("SECTION")) {
                sections.add(sect);
            }
        }
        if ((ArrayList<Category>) intent.getSerializableExtra("CATEGORY") != null) {
            for (Category cat : (ArrayList<Category>) intent.getSerializableExtra("CATEGORY")) {
                categories.add(cat);
            }
        }


        ArrayList<Instrument> insList = rent_inv.getInstrumentList();
        for (Instrument ins: insList){
            System.out.println(ins.getName());
            System.out.println(ins.getCategory());
            System.out.println();
            System.out.println();
            System.out.println();
        }


        instantiateButtons();
        oneTimeListeners();
        setPartsButtons();

        //hide buttons for BandMembers who are not Faculty
        if (facultyRights == false) {
            hideButtons();
        }

    }

    //hides buttons
    private void hideButtons() {
        add_button.setVisibility(View.INVISIBLE);
        remove_button.setVisibility(View.INVISIBLE);
        edit_rentable_button.setVisibility(View.INVISIBLE);
        notes_button.setVisibility(View.INVISIBLE);
        checkout_button.setVisibility(View.INVISIBLE);
        edit_member_button.setVisibility(View.INVISIBLE);
        set_lead_button.setVisibility(View.INVISIBLE);
        add_section_button.setVisibility(View.INVISIBLE);
        delete_section_button.setVisibility(View.INVISIBLE);
        add_category_button.setVisibility(View.INVISIBLE);
        delete_category_button.setVisibility(View.INVISIBLE);
    }

    //instantiates all lists
    private void instantiatelists() {
        member_inv = new BandMemberInventory();
        rent_inv = new RentableInventory();
        ins_concepts = new ArrayList<>();
        part_concepts = new ArrayList<>();
        categories = new ArrayList<>();
    }

    //instantiates all buttons
    private void instantiateButtons() {
        sections = new ArrayList<>(/*IMPORT SECTIONS*/);
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
        add_category_button = findViewById(R.id.add_category_main_screen_button);
        delete_category_button =findViewById(R.id.delete_category_main_screen_button);
        inv_view = findViewById(R.id.item_list_main_screen_rview);

        if (rent_inv.getInstrumentList() != null) {
            for (Instrument ins : rent_inv.getInstrumentList()) {
                if (!ins_concepts.contains(ins.getName())) {
                    ins_concepts.add(ins.getName());
                }
            }
        }
        if (rent_inv.getPartList() != null) {
            for (Part part : rent_inv.getPartList()) {
                if (!part_concepts.contains(part.getName())) {
                    part_concepts.add(part.getName());
                }
            }
        }
    }

    //sets Buttons for when user has parts selected from dropdown
    private void setPartsButtons() {

        //prepares filter button for Parts
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), part_filters.class);
                openFilter.putExtra("partlist", rent_inv.getPartList());
                startActivityForResult(openFilter, 9);
            }
        });

        PartRecyclerAdapter adapter = new PartRecyclerAdapter((rent_inv.getPartList()));
        inv_view.setLayoutManager(new LinearLayoutManager(this));
        inv_view.setAdapter(adapter);


        if (facultyRights == true) {
            //prepares add Parts button
            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), add_part.class);
                    openFilter.putExtra("partlist", rent_inv.getPartList());
                    startActivityForResult(openFilter, 0);
                }
            });
            //prepares edit Part button
            edit_rentable_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), edit_part.class);
                    openFilter.putExtra("partlist", rent_inv.getPartList());
                    startActivityForResult(openFilter, 8);
                }
            });
            //prepares remove Part button
            remove_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openFilter = new Intent(getBaseContext(), delete_part.class);
                    openFilter.putExtra("partlist", rent_inv.getPartList());
                    startActivityForResult(openFilter, 14);
                }
            });


            //sets visibility
            edit_rentable_button.setVisibility(View.VISIBLE);
            edit_member_button.setVisibility(View.INVISIBLE);
            set_lead_button.setVisibility(View.INVISIBLE);
            checkout_button.setVisibility(View.INVISIBLE);
            notes_button.setVisibility(View.INVISIBLE);
            delete_section_button.setVisibility(View.INVISIBLE);
            add_section_button.setVisibility(View.INVISIBLE);
            delete_category_button.setVisibility(View.INVISIBLE);
            add_category_button.setVisibility(View.INVISIBLE);
        }


    }

    //sets Buttons for when user has Instruments selected from dropdown
    private void setInstrumentButtons() {

        //Prepares filter button for Instruments
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Instrument> insList = rent_inv.getInstrumentList();
                for (Instrument ins: insList){
                    System.out.println(ins.getName());
                    System.out.println(ins.getCategory());
                    System.out.println();
                    System.out.println();
                    System.out.println();
                }
                Intent openFilter = new Intent(getBaseContext(), instrument_filters.class);
                openFilter.putExtra("sectionlist", sections);
                openFilter.putExtra("categorylist", categories);
                openFilter.putExtra("instrumentlist", rent_inv.getInstrumentList());
                startActivityForResult(openFilter, 10);
            }
        });

        InstrumentRecyclerAdapter adapter = new InstrumentRecyclerAdapter(rent_inv.getInstrumentList());
        inv_view.setLayoutManager(new LinearLayoutManager(this));
        inv_view.setAdapter(adapter);

        if (facultyRights == true) {
            //Prepares add button for Instruments
            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), add_instrument.class);
                    openFilter.putExtra("instrumentlist", rent_inv.getInstrumentList());
                    openFilter.putExtra("categorylist", categories);
                    openFilter.putExtra("sectionlist", sections);

                    startActivityForResult(openFilter, 1);
                }
            });
            //Prepares edit button for Instruments
            edit_rentable_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), edit_instrument.class);
                    openFilter.putExtra("instrumentlist", rent_inv.getInstrumentList());
                    openFilter.putExtra("categorylist", categories);
                    openFilter.putExtra("sectionlist", sections);

                    startActivityForResult(openFilter, 6);
                }
            });
            //Prepares remove button for Instruments
            remove_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openFilter = new Intent(getBaseContext(), delete_instrument.class);
                    openFilter.putExtra("instrumentlist", rent_inv.getInstrumentList());
                    startActivityForResult(openFilter, 12);
                }
            });

            //sets visibility of buttons for dropdown selection
            edit_rentable_button.setVisibility(View.VISIBLE);
            edit_member_button.setVisibility(View.INVISIBLE);
            set_lead_button.setVisibility(View.INVISIBLE);
            checkout_button.setVisibility(View.VISIBLE);
            notes_button.setVisibility(View.VISIBLE);
            delete_category_button.setVisibility(View.VISIBLE);
            add_category_button.setVisibility(View.VISIBLE);
            delete_section_button.setVisibility(View.INVISIBLE);
            add_section_button.setVisibility(View.INVISIBLE);
        }

    }

    private void setBandmemberButtons() {
        //Prepares filter button for BandMembers
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), bandmember_filters.class);
                openFilter.putExtra("sectionlist", sections);
                openFilter.putExtra("instrumentlist", rent_inv.getInstrumentList());
                openFilter.putExtra("bandmemberlist", member_inv.getBandMembers());
                startActivityForResult(openFilter, 11);
            }
        });

        MemberRecyclerAdapter adapter = new MemberRecyclerAdapter((member_inv.getBandMembers()));
        inv_view.setLayoutManager(new LinearLayoutManager(this));
        inv_view.setAdapter(adapter);

        if (facultyRights == true) {
            //Prepares add button for BandMembers
            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), add_member.class);
                    openFilter.putExtra("sectionlist", sections);
                    startActivityForResult(openFilter, 2);
                }
            });
            //Prepares remove button for BandMembers
            remove_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openFilter = new Intent(getBaseContext(), delete_member.class);
                    openFilter.putExtra("memberlist", member_inv.getBandMembers());
                    startActivityForResult(openFilter, 13);
                }
            });

            //Sets visibility for when dropdown is BandMembers
            edit_rentable_button.setVisibility(View.INVISIBLE);
            edit_member_button.setVisibility(View.VISIBLE);
            set_lead_button.setVisibility(View.VISIBLE);
            checkout_button.setVisibility(View.INVISIBLE);
            notes_button.setVisibility(View.INVISIBLE);
            delete_section_button.setVisibility(View.VISIBLE);
            add_section_button.setVisibility(View.VISIBLE);
        }


    }

    private void oneTimeListeners() {

        if (facultyRights == true) {
            //sets checkout button
            checkout_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), checkout_instrument.class);
                    openFilter.putExtra("instrumentlist", rent_inv.getInstrumentList());
                    openFilter.putExtra("memberlist", member_inv.getBandMembers());
                    startActivityForResult(openFilter, 3);
                }
            });
            //sets notes button
            notes_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), edit_notes.class);
                    openFilter.putExtra("instrumentlist", rent_inv.getInstrumentList());
                    startActivityForResult(openFilter, 6);
                }
            });
            //sets delete section button
            delete_section_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), delete_section.class);
                    openFilter.putExtra("sectionlist", sections);
                    startActivityForResult(openFilter, 5);
                }
            });
            //sets add section button
            add_section_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), add_section.class);

                    startActivityForResult(openFilter, 4);
                }
            });
            //sets edit member button
            edit_member_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent openFilter = new Intent(getBaseContext(), edit_member.class);
                    openFilter.putExtra("memberlist", member_inv.getBandMembers());
                    openFilter.putExtra("sectionlist", sections);
                    startActivityForResult(openFilter, 7);
                }
            });
            //sets set lead button
            set_lead_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), set_lead.class);
                    openFilter.putExtra("memberlist", member_inv.getBandMembers());
                    openFilter.putExtra("sectionlist", sections);
                    startActivityForResult(openFilter, 7);
                }
            });
            //sets add category button
            add_category_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), add_category.class);
                    openFilter.putExtra("categorylist", categories);
                    startActivityForResult(openFilter, 15);
                }
            });
            //sets delete category button
            delete_category_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openFilter = new Intent(getBaseContext(), delete_category.class);
                    openFilter.putExtra("categorylist", categories);
                    startActivityForResult(openFilter, 16);
                }
            });

        }

        //logic for picking what the user currently wants to look at (Parts, Instruments, or BandMembers)
        inv_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (inv_spin.getSelectedItem().toString().equals("Parts")) {
                    setPartsButtons();
                }
                if (inv_spin.getSelectedItem().toString().equals("Instruments")) {
                    setInstrumentButtons();
                }

                if (inv_spin.getSelectedItem().toString().equals("Band Members")) {
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
                logout();
            }
        });

    }

    //request codes for all activity operations
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //add part
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    Part part = (Part) data.getSerializableExtra("part");
                    rent_inv.addPart(part);
                    inv_view.getAdapter().notifyDataSetChanged();
                }
            }
        }

        //add instrument
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    Instrument instrument = (Instrument) data.getSerializableExtra("instrument");
                    rent_inv.addInstrument(instrument);
                    InstrumentRecyclerAdapter adapter = new InstrumentRecyclerAdapter(rent_inv.getInstrumentList());
                    inv_view.setLayoutManager(new LinearLayoutManager(this));
                    inv_view.setAdapter(adapter);
                }
            }
        }

        //add Member
        if (requestCode == 2) {

            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                BandMember member = (BandMember) data.getSerializableExtra("member");
                member_inv.addBandMember(member);
                MemberRecyclerAdapter adapter = new MemberRecyclerAdapter(member_inv.getBandMembers());
                inv_view.setLayoutManager(new LinearLayoutManager(this));
                inv_view.setAdapter(adapter);
                }

            }
        }

        //checkout
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    int count = data.getIntExtra("count_instrument", -2056);
                    BandMember member = (BandMember) data.getSerializableExtra("member");
                    Instrument instrument = (Instrument) data.getSerializableExtra("instrument");
                    if(instrument.getCurrentOwner() != "School") {
                        instrument.setCurrentOwner(member.getUlid());
                        ((Student) member).setInstrument(instrument);
                    }


                    if (count >= 0) {
                        rent_inv.changeInstrument(count, instrument);
                    }
                    count = data.getIntExtra("count_member", -256);

                    if (count >= 0) {
                        member_inv.changeMember(count, member);

                    }
                    inv_view.getAdapter().notifyDataSetChanged();
                }
            }


        }

        //add section
        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    String section = data.getStringExtra("section");
                    sections.add(section);
                    System.out.println("=============================================================================" + sections.size());
                }
            }
        }

        //delete section
        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    String section = data.getStringExtra("section");
                    for (BandMember member : member_inv.getBandMembers()) {
                        if (member.getSection().equals(section)) {
                            member.setSection("");
                        }
                    }
                    sections.remove(section);
                    inv_view.getAdapter().notifyDataSetChanged();
                }
            }
        }

        //edit Instrument and edit note
        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    int count = data.getIntExtra("count", -2056);

                    if (count >= 0) {
                        Instrument instrument = (Instrument) data.getSerializableExtra("instrument");
                        rent_inv.changeInstrument(count, instrument);
                    }
                    inv_view.getAdapter().notifyDataSetChanged();
                }
            }

        }

        //edit member and set lead
        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    int count = data.getIntExtra("count", -256);
                    System.out.println("jaadjioasjoadsjiaodsadijo----------------------" + count);

                    if (count >= 0) {
                        BandMember member = (BandMember) data.getSerializableExtra("member");
                        member_inv.changeMember(count, member);
                    }
                    inv_view.getAdapter().notifyDataSetChanged();
                }
            }

        }

        //edit part
        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {

                    int count = data.getIntExtra("count", -128);

                    if (count >= 0) {
                        Part part = (Part) data.getSerializableExtra("part");
                        rent_inv.changePart(count, part);
                    }
                    inv_view.getAdapter().notifyDataSetChanged();

                    // PartRecyclerAdapter adapter = new PartRecyclerAdapter(rent_inv.getPartList());
                    //   inv_view.setLayoutManager(new LinearLayoutManager(this));
                    //  inv_view.setAdapter(adapter);
                }
            }

        }

        //part filters
        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    ArrayList<Part> partlist = (ArrayList<Part>) data.getSerializableExtra("partlist");
                    PartRecyclerAdapter adapter = new PartRecyclerAdapter(partlist);
                    inv_view.setLayoutManager(new LinearLayoutManager(this));
                    inv_view.setAdapter(adapter);
                }
            }
        }

        //instrument filters
        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    ArrayList<Instrument> inslist = (ArrayList<Instrument>) data.getSerializableExtra("inslist");
                    InstrumentRecyclerAdapter adapter = new InstrumentRecyclerAdapter(inslist);
                    inv_view.setLayoutManager(new LinearLayoutManager(this));
                    inv_view.setAdapter(adapter);
                }
            }

        }

        //Bandmember filters
        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    ArrayList<BandMember> memberlist = (ArrayList<BandMember>) data.getSerializableExtra("memberlist");
                    MemberRecyclerAdapter adapter = new MemberRecyclerAdapter(memberlist);
                    inv_view.setLayoutManager(new LinearLayoutManager(this));
                    inv_view.setAdapter(adapter);
                }
            }

        }

        //delete Instrument
        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    Instrument instrument = (Instrument) data.getSerializableExtra("instrument");
                    rent_inv.removeInstrument(instrument);
                    inv_view.getAdapter().notifyDataSetChanged();
                }
            }

        }

        //delete member
        if (requestCode == 13) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    BandMember member = (BandMember) data.getSerializableExtra("member");
                    member_inv.removeBandMember(member);
                    inv_view.getAdapter().notifyDataSetChanged();
                }
            }

        }

        //delete part
        if (requestCode == 14) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    Part part = (Part) data.getSerializableExtra("part");
                    rent_inv.removePart(part);
                    inv_view.getAdapter().notifyDataSetChanged();
                }
            }

        }


        //add category
        if (requestCode == 15) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    Category cat = (Category) data.getSerializableExtra("category");
                    categories.add(cat);
                }
            }


        }

        //delete category
        if (requestCode == 16) {
            if (resultCode == RESULT_OK) {
                if (data.getBooleanExtra("timeOut", false))
                    logout();
                else {
                    ArrayList<Category> cat = new ArrayList<>();
                    cat = (ArrayList<Category>) data.getSerializableExtra("category");
                    categories = cat;
                }
            }
        }


    }

    //logs out of application
    private void logout() {
        Intent intent = new Intent();
        ArrayList<Faculty> fac = member_inv.getFaculty();
        ArrayList<Student> stu = member_inv.getStudents();
        ArrayList<Part> parts = rent_inv.getPartList();
        ArrayList<Instrument> instruments = rent_inv.getInstrumentList();
        intent.putExtra("Faculty", fac);
        intent.putExtra("Students", stu);
        intent.putExtra("Parts", parts);
        intent.putExtra("Instruments", instruments);
        intent.putExtra("Categories", categories);
        setResult(RESULT_OK, intent);
        finish();
    }

    //resets session timeout timer when user interacts
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        TimerTask logoutTimeTask = new TimerTask() {
            @Override
            public void run() {
                logout(); }};
        timer.cancel();
        timer.purge();
        timer = new Timer();
        timer.schedule(logoutTimeTask, logoutTime);
    }
    //disables timer when leaving activity
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
        TimerTask logoutTimeTask = new TimerTask() {
            @Override
            public void run() {
                logout(); }
        };
        timer.schedule(logoutTimeTask, logoutTime);
    }
}

