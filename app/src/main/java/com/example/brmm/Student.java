package com.example.brmm;

import java.io.Serializable;

public class Student extends BandMember implements Serializable {
    private String section;


    private String sectionLead;
    private String notes;
    private Instrument instrument;


    public String getSectionLead() { return sectionLead;  }

    public void setSectionLead(String sectionLead) {  this.sectionLead = sectionLead;  }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

    public Student(){

    }

    public Student(String firstname, String lastname, String ulid, String section, boolean sectionLeader, int UID, String notes){
        setFname(firstname);
        setLname(lastname);
        setUlid(ulid);
        this.section = section;
        this.sectionLead = sectionLead;
        setUID(UID);
        this.notes = notes;
    }

    @Override
    public boolean equals (Object member)
    {
        if(!(member instanceof Student)) {
            return false;
        }
        return equals((Student) member);
    }

    private boolean equals(Student member)
    {
        return member.UID == UID;
    }
}
