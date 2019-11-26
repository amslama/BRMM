package com.example.brmm;

import java.util.ArrayList;

enum Condition
{
    Excellent, Good, Fair, Poor, Broken;
}

public class Instrument extends Rentable {

    private ArrayList<Category> categories;
    private String subtype;
    private String currentOwner;
    private Condition curCondition;
    private int id;


    public Instrument() {
        currentOwner = "School";
        subtype = "no type";
        name = "no item";
        cost = 0;
        id = -1;
    }

    public Instrument(String co, String st, String na, double price, int idnum) {
        currentOwner = co;
        subtype = st;
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
