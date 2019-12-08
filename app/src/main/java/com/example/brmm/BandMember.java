package com.example.brmm;

import java.io.Serializable;

public class BandMember implements Serializable
{
    protected String fname;
    protected String lname;
    protected int UID;
    public String ulid;


    public BandMember(){

    }

    public BandMember(String fname, String lname, int UID, String ulid){
        this.fname = fname;
        this.lname = lname;
        this.UID = UID;
        this.ulid = ulid;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    protected String section;

    public void setFname(String fname){
        this.fname = fname;
    }

    public String getFname(){
        return fname;
    }

    public void setLname(String lname){
        this.lname = lname;
    }

    public String getLname(){
        return lname;
    }

    public void setUlid(String ulid){
        this.ulid = ulid;
    }

    public String getUlid(){
        return ulid;
    }

    public int getUID(){
        return UID;
    }

    public  void setUID(int UID){this.UID = UID; }

    @Override
    public boolean equals (Object member)
    {
        if(!(member instanceof BandMember)) {
            if (this.getClass() != member.getClass()) return false;
            return false;
        }
        return equals((BandMember) member);
    }

    private boolean equals(BandMember member)
    {
        return member.UID == UID;
    }

}



