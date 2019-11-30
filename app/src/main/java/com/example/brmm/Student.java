package com.example.brmm;

import java.util.ArrayList;

public class Student extends BandMember {
    private String section;
    private boolean sectionLeader;
    private int UID;
    private String notes;
    private ArrayList<Rentable> instruments;

    public ArrayList<Rentable> getInstruments() {
        return instruments;
    }


    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public boolean isSectionLeader() {
        return sectionLeader;
    }

    public void setSectionLeader(boolean sectionLeader) {
        this.sectionLeader = sectionLeader;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
