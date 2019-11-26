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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public BandMember getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(BandMember currentOwner) {
        this.currentOwner = currentOwner;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Category getHighestCategory() {
        return highestCategory;
    }

    public void setHighestCategory(Category highestCategory) {
        this.highestCategory = highestCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
