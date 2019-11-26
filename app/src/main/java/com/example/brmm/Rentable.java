package com.example.brmm;


public abstract class Rentable
{
    protected String name;
    protected double cost;
    protected BandMember currentOwner;
    protected String notes;
    protected Category highestCategory;
    protected int id;

    public Rentable() {
        currentOwner = null;
        highestCategory = null;
        name = "no item";
        cost = 0;
        id = -1;
    }
    
}
