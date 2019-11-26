package com.example.brmm;

import java.util.ArrayList;

public abstract class BandMember
{
    protected String fname;
    protected String lname;
    protected String ulid;
    protected int UID;
    protected String passWord;
    protected ArrayList<Rentable> instruments;
    protected ArrayList<Rentable> parts;

    public BandMember() {
    }

    
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUlid() {
        return ulid;
    }

    public void setUlid(String ulid) {
        this.ulid = ulid;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public ArrayList<Rentable> getInstruments() {
        return instruments;
    }

    public void setInstruments(ArrayList<Rentable> instruments) {
        this.instruments = instruments;
    }

    public ArrayList<Rentable> getParts() {
        return parts;
    }

    public void setParts(ArrayList<Rentable> parts) {
        this.parts = parts;
    }
}
