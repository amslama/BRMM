package com.example.brmm;

import java.io.Serializable;

public class Student extends BandMember implements Serializable {
    private String section;


    private boolean sectionLead;
    private String notes;
    private Instrument instrument;


    public boolean getSectionLead() { return sectionLead;  }

    public void setSectionLead(boolean sectionLead) {  this.sectionLead = sectionLead;  }

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
            if (this.getClass() != member.getClass()) return false;
            return false;
        }
        return equals((Student) member);
    }

    public boolean getSectionLeader(){
        return sectionLead;
    }

    private boolean equals(Student member)
    {
        return member.UID == UID;
    }
}
