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
