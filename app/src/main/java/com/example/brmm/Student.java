package com.example.brmm;

public class Student extends BandMember {
    private String section;
    private boolean sectionLeader;
    //private int UID;
    private String notes;

    public Student(){

    }

    public Student(String firstname, String lastname, String ulid, String section, boolean sectionLeader, int UID, String notes){
        setFname(firstname);
        setLname(lastname);
        setUlid(ulid);
        this.section = section;
        this.sectionLeader = sectionLeader;
        setUID(UID);
        this.notes = notes;
    }

}
