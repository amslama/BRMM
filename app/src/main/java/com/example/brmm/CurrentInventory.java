package com.example.brmm;

import java.util.ArrayList;

public class CurrentInventory {

    private ArrayList<Instrument> instruments;
    private ArrayList<Part> parts;
    private ArrayList<BandMember> bandMembers;


    public void addInstrument(Instrument newIns) {
        instruments.add(newIns);
        //update db
    }

    public void addPart(Part newPart) {
        parts.add(newPart);
        //update db
    }

    public void addBandMember(BandMember newBM)
    {
        bandMembers.add(newBM);
        //update db
    }

    public void removeInstrument(Instrument newIns) {
        //instruments.add(newIns);
        //update db
    }

    public void removePart(Part newPart) {
       // parts.add(newPart);
        //update db
    }

    public void removeBandMember(BandMember newBM)
    {
       // bandMembers.add(newBM);
        //update db
    }
}