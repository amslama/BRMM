
package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class BandMemberFilters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_filters);

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
        final RadioGroup rgroup = findViewById(R.id.bm_type_bandmember_filters_rgroup);
        final RadioButton student_rbutton = findViewById(R.id.student_bandmember_filters_rbutton);
        final RadioButton faculty_rbutton = findViewById(R.id.faculty_bandmember_filters_rbutton);

        //checkboxes
        final CheckBox has_cbox = findViewById(R.id.has_bandmember_filters_cbox);
        final CheckBox no_has_cbox = findViewById(R.id.no_has_bandmember_filters_cbox);
    }

    // main filter method, for ints, 0 = dont filter, 1 = filter by Faculty or is true, 2 = filter by false
    public ArrayList<BandMember> filterMemberInv(ArrayList<BandMember> members, int isFaculty, int hasInstrument, boolean sectionLeaders, String firstName, String lastName, int UID, Instrument instrument, String section) {

        ArrayList<BandMember> filter = new ArrayList<>();

        if(isFaculty == 1)
            filter = filterByFaculty(filter);
        else if (isFaculty == 2)
            filter = filterByStudent(filter);
        else {}


        if (hasInstrument == 1)
            filter = filterByHasInstrument(filter);
        else if (hasInstrument == 2)
            filter = filterByHasNoInstrument(filter);
        else {}


        if (sectionLeaders)
       //     filter = filterBySectionLeaders(filter);


        if (!firstName.equals("") && !lastName.equals("")) {
      //      filter = filterByName(filter, firstName, lastName);
        }


        if (UID != 0)
   //         filter = filterByUID(filter, UID);


        if (instrument != null){}
      //      filter = filterByspecInstrument(filter, instrument);


      //  if (section != null)
      //      filter = filterBySection(filter, section);


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
                if (((Student) member).getInstruments().isEmpty()) {
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
                if (!((Student) member).getInstruments().isEmpty()) {
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
          //      if(((Student) member).isSectionLeader())
           //         filter.add(member);
            }

        }
        return filter;
    }

    //returns a list of members with given name
    public ArrayList<BandMember> filterByName(ArrayList<BandMember> members, String fName, String lName) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {

        }
        return filter;
    }

    //returns a list of members with that uid
    public ArrayList<BandMember> filterByUID(ArrayList<BandMember> members, int UID) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if (member.getId() == UID) {
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
                if (((Student) member).getInstruments().contains(instrument)) {
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

}