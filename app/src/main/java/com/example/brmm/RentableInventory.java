package com.example.brmm;

import java.util.ArrayList;

public class RentableInventory {

    private ArrayList<Instrument> instruments;
    private ArrayList<Part> parts;


    public void addInstrument(Instrument newIns) {
        instruments.add(newIns);
        //update db
    }

    public void addPart(Part newPart) {
        parts.add(newPart);
        //update db
    }

    public ArrayList<Instrument> getInstrumentList()
    {
        return instruments;
    }

    public ArrayList<Part> getPartList()
    {
        return parts;
    }

    public void removeInstrument(Instrument newIns) {

        instruments.remove(newIns);
        //update db
    }

    public void removePart(Part newPart) {
        parts.remove(newPart);
        //update db
    }


}