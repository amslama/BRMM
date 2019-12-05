package com.example.brmm;

import java.io.Serializable;

public class BandMember implements Serializable
{
    protected String fname;
    protected String lname;
    protected int UID;
    public String ulid;

    BandMember()
    {
        fname = "";
        lname = "";
        UID = -1;
        ulid = "";
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

}


