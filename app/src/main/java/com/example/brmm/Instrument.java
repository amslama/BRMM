package com.example.brmm;

import java.util.ArrayList;

enum Condition
{
    EXCELLENT, GOOD, FAIR, POOR, BROKEN;
}

public class Instrument extends Rentable {

    private String subtype;
    private String currentOwner;
    private Condition curCondition;



    public Instrument() {
        super();
        curCondition = null;

    }



    public boolean equals(Instrument ins) {
        if (ins.id == id) {
            return true;
        }
        return false;
    }

}
