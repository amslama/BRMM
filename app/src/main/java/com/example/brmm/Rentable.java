package com.example.brmm;


public abstract class Rentable
{
    protected String name;
    protected double cost;
    protected BandMember owner;
    protected String notes;
    protected Category highestCategory;

    public Rentable(String name, double cost, BandMember owner, String notes, Category highestCategory) {
        this.name = name;
        this.cost = cost;
        this.owner = owner;
        this.notes = notes;
        this.highestCategory = highestCategory;
    }
}
