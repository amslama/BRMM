package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class Instrument extends Rentable implements Serializable {

   // private Category category;
    private String section;
    private boolean isSelected;

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
        name = "no item";
        cost = 0;
        id = -1;
    }

    /*public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }*/

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

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }

}
