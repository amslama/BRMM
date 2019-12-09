
package com.example.brmm;

import android.content.Intent;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class bandmember_filters extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandmember_filters);

       final RadioGroup rgroup = findViewById(R.id.bm_type_bandmember_filters_rgroup);
       final RadioButton rbutton;

        //Textviews
        final TextView header = findViewById(R.id.bandmember_filters_header);
        final TextView fname_textview = findViewById(R.id.fname_bandmember_filters_textview);
        final TextView lname_textview = findViewById(R.id.lname_bandmember_filters_textview);
        final TextView bm_type_textview = findViewById(R.id.bm_type_bandmember_filters_textview);
        final TextView UID_textview = findViewById(R.id.UID_bandmember_filters_textview);
        final TextView section_textview = findViewById(R.id.section_bandmember_filters_textview);
        final TextView slo_textview = findViewById(R.id.slo_bandmember_filters_textview);
        final TextView instrument_textview = findViewById(R.id.instrument_bandmember_filters_textview);
        final TextView checkout_status_textview = findViewById(R.id.checkout_status_bandmember_filters_textview);

        //Edittexts
        final EditText firstNameTxt = findViewById(R.id.fname_bandmember_filters_edittext);
        final EditText lastNameTxt = findViewById(R.id.lname_bandmember_filters_edittext);
        final EditText UID_edittext = findViewById(R.id.UID_bandmember_filters_edittext);

        //Dropdowns
        final Spinner section_spin = findViewById(R.id.section_bandmember_filters_dropdown);
        final Spinner instrument_spin = findViewById(R.id.instrument_bandmember_filters_dropdown);

        //Switch
        final Switch secLeadersSwitch = findViewById(R.id.section_leader_switch);

        //Radio stuffs


        //checkboxes
        final CheckBox has_cbox = findViewById(R.id.has_bandmember_filters_cbox);
        final CheckBox no_has_cbox = findViewById(R.id.no_has_bandmember_filters_cbox);

        //buttons
        final Button apply_button = findViewById(R.id.apply_filters_button);
        final Button cancel_button = findViewById(R.id.cancel_filters_button);


        //sets up spinner containing the list of Instruments
        final ArrayList<Instrument> inslist = (ArrayList<Instrument>) getIntent().getSerializableExtra("instrumentlist");
        if (inslist != null) {
            Instrument instrument = new Instrument();
            instrument.setName("No Filter");
            inslist.add(0,instrument);
            ArrayAdapter<Instrument> insAdapter = new ArrayAdapter<Instrument>(this, android.R.layout.simple_spinner_item, inslist);
            insAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            instrument_spin.setAdapter(insAdapter);
        }

        //logic for selecting instrument from spinner
        instrument_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Instrument instrument = (Instrument) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //sets up spinner containing Sections
        final ArrayList<String> sections = getIntent().getStringArrayListExtra("sectionlist");
        if (sections != null) {
            String section = "No Filter";
            sections.add(0,section);
            ArrayAdapter<String> secAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sections);
            secAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            section_spin.setAdapter(secAdapter);
        }

        //logic for selecting a section
        section_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String section = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Makes sure other checkbox is unchecked on click
        has_cbox.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((CheckBox)v).isChecked()) {
                        no_has_cbox.setChecked(false);
                    }
                }
            }
        );

        //Makes sure other checkbox is unchecked on click
        no_has_cbox.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((CheckBox)v).isChecked()) {
                        has_cbox.setChecked(false);
                    }
                }
            }
        );


        //applies the filters
        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String firstName;
                 String lastName;
                 String section;
                 String memberType;
                 int hasInstrument = 0;
                 int UID;
                 boolean sectionLeaders;
                 Instrument instrument;

                 instrument = (Instrument) instrument_spin.getSelectedItem();
                 section = section_spin.getSelectedItem().toString();

                 int radioId;
                try {
                    radioId = rgroup.getCheckedRadioButtonId();
                }
                catch(Exception ex){radioId = -1;}


                if (radioId == 2131296678)
                   memberType = "Student";
                else if (radioId == 2131296445)
                   memberType = "Teacher";
                else
                    memberType = "Everyone";


                System.out.println("\t\t\t" + radioId);

                if (has_cbox.isChecked())
                    hasInstrument = 1;
                if (no_has_cbox.isChecked())
                    hasInstrument = 2;

                sectionLeaders = secLeadersSwitch.isChecked();
                firstName = firstNameTxt.getText().toString();
                lastName = lastNameTxt.getText().toString();

                try {
                    UID = Integer.parseInt(UID_edittext.getText().toString());
                } catch (NumberFormatException ex){UID = -1;}


                Intent thisIntent = new Intent();
                 ArrayList<BandMember> memberlist = (ArrayList<BandMember>) getIntent().getSerializableExtra("memberlist");

                //If there are no band members to filter, return to main screen
                if (memberlist == null)
                    finish();


                memberlist = filterMemberInv(memberlist,memberType,hasInstrument,sectionLeaders,firstName,lastName,UID,instrument, section);
                thisIntent.putExtra("memberlist", memberlist);
                setResult(RESULT_OK,thisIntent);
                finish();
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



    //filters the bandmembers
    public ArrayList<BandMember> filterMemberInv(ArrayList<BandMember> members, String memberType, int hasInstrument, boolean sectionLeaders, String firstName, String lastName, int UID, Instrument instrument, String section) {

        ArrayList<BandMember> filter = members;

        if(memberType.equals("Student"))
            filter = filterByStudent(filter);
        else if (memberType.equals("Teacher"))
            filter = filterByFaculty(filter);
        else {}


        if (hasInstrument == 1)
            filter = filterByHasInstrument(filter);
        else if (hasInstrument == 2)
            filter = filterByHasNoInstrument(filter);
        else {}


        if (sectionLeaders)
            filter = filterBySectionLeaders(filter);


        if (!firstName.equals("") && !lastName.equals(""))
            filter = filterByName(filter, firstName, lastName);


        if (UID != 0)
            filter = filterByUID(filter, UID);


        if (!instrument.getName().equals("No Filter"))
            filter = filterByspecInstrument(filter, instrument);


        if (!section.equals("No Filter"))
           filter = filterBySection(filter, section);


        return filter;

    }

    //returns list of faculty
    public ArrayList<BandMember> filterByFaculty(ArrayList<BandMember> members) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if (member instanceof Faculty) {
                filter.add(member);
            }

        }
        return filter;
    }

    //returns list of students
    public ArrayList<BandMember> filterByStudent(ArrayList<BandMember> members) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if (member instanceof Student) {
                filter.add(member);
            }

        }
        return filter;
    }

    //returns list of students with no instruments
    public ArrayList<BandMember> filterByHasNoInstrument(ArrayList<BandMember> members) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if( member instanceof Student) {
                if (((Student) member).getInstrument() == null) {
                    filter.add(member);
                }
            }

        }
        return filter;
    }

    //returns list of students with instruments
    public ArrayList<BandMember> filterByHasInstrument(ArrayList<BandMember> members) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if( member instanceof Student) {
                if (((Student) member).getInstrument() != null) {
                    filter.add(member);
                }
            }
        }
        return filter;
    }


    //returns list of students who are section leaders
    public ArrayList<BandMember> filterBySectionLeaders(ArrayList<BandMember> members) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if ( member instanceof Student) {
                if (!((Student) member).getSectionLead().equals(""))
                    filter.add(member);
            }

        }
        return filter;
    }


    //returns a list of members with given name, does not break immediately after finding since two people could have the same name
    public ArrayList<BandMember> filterByName(ArrayList<BandMember> members, String fName, String lName) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
                if (member.getFname().equals(fName) && member.getLname().equals(lName)) {
                    filter.add(member);
                }
        }
        return filter;
    }

    //returns a list of members with that uid
    public ArrayList<BandMember> filterByUID(ArrayList<BandMember> members, int UID) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if (member.getUID() == UID) {
                filter.add(member);
                break;
            }

        }
        return filter;
    }

    //Returns a list of members with a specific instrument
    public ArrayList<BandMember> filterByspecInstrument(ArrayList<BandMember> members, Instrument instrument) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if ( member instanceof Student) {
                if (((Student) member).getInstrument() == instrument) {
                    filter.add(member);
                    break;
                }
            }

        }
        return filter;
    }

    //Returns a list of members in a specific section
    public ArrayList<BandMember> filterBySection(ArrayList<BandMember> members, String section) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if ( member instanceof Student) {
                if(((Student) member).getSection().equals(section))
                    filter.add(member);
            }

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