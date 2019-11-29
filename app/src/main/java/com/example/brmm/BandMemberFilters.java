
package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class BandMemberFilters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_filters);
    }

    // main filter method, for ints, 0 = dont filter, 1 = filter by Faculty or is true, 2 = filter by false
    public ArrayList<BandMember> filterMemberInv(ArrayList<BandMember> members, int isFaculty, int hasInstrument, boolean sectionLeaders, String firstName, String lastName, int UID, Instrument instrument, Section section) {
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
            filter = filterBySectionLeaders(filter);


        if (!firstName.equals("") && !lastName.equals("")) {
            filter = filterByName(filter, firstName, lastName);
        }


        if (UID != 0)
            filter = filterByUID(filter, UID);


        if (instrument != null)
            filter = filterByspecInstrument(filter, instrument);


        if (section != null)
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
            if (member.getInstruments().isEmpty()) {
                filter.add(member);
            }

        }
        return filter;
    }

    //returns list of students with instruments
    public ArrayList<BandMember> filterByHasInstrument(ArrayList<BandMember> members) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if (!member.getInstruments().isEmpty()) {
                filter.add(member);
            }

        }
        return filter;
    }


    //returns list of students who are section leaders
    public ArrayList<BandMember> filterBySectionLeaders(ArrayList<BandMember> members) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if ( member instanceof Student) {
                if(((Student) member).isSectionLeader())
                    filter.add(member);
            }

        }
        return filter;
    }

    //returns a list of members with given name
    public ArrayList<BandMember> filterByName(ArrayList<BandMember> members, String fName, String lName) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if (member.getFname().equals(fName) && member.getLname().equals(lName)) {
                filter.add(member);
                break;
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
            if (member.getInstruments().contains(instrument)) {
                filter.add(member);
                break;
            }

        }
        return filter;
    }

    //Returns a list of members in a specific section
    public ArrayList<BandMember> filterBySection(ArrayList<BandMember> members, Section section) {
        ArrayList<BandMember> filter = new ArrayList<>();
        for (BandMember member : members) {
            if ( member instanceof Student) {
                if(((Student) member).getSection() == section)
                    filter.add(member);
            }

        }
        return filter;
    }

}