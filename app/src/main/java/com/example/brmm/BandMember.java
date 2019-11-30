package com.example.brmm;

public class BandMember
{
    private String fname;
    private String lname;
    public String ulid;

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
}


