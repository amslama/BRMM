package com.example.brmm;

import java.util.ArrayList;

public class Instrument extends Rentable {

    private ArrayList<Category> categories;
    private String section;
    private String currentOwner;
    private int id;


    public Instrument() {
        currentOwner = "School";
        section = "no type";
        name = "no item";
        cost = 0;
        id = -1;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(String currentOwner) {
        this.currentOwner = currentOwner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instrument(String co, String st, String na, double price, int idnum) {
        currentOwner = co;
        section = st;
        name = na;
        cost = price;
        id = idnum;
    }

    public boolean equals(Instrument ins) {
        if (ins.id == id) {
            return true;
        }
        return false;
    }

}
