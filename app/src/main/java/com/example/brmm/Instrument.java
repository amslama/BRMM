package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class Instrument extends Rentable implements Serializable {

   // private Category category;
    private String section;
    private boolean isSelected;
    private Category category;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String note;


    public Instrument (){
        currentOwner = "School";
        section = "no type";
        name = "instrument";
        cost = 0;
        id = -1;
        category = null;
    }

    public Category getCategory() {
        return category;
    }

    public String toString() {
        return name;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Instrument(String co, String st, String na, double price, int idnum, String category) {
        currentOwner = co;
        section = st;
        name = na;
        cost = price;
        id = idnum;
       // this.category = category;
    }

    public boolean equals(Instrument ins) {
        return ins.id == id;
    }


}
