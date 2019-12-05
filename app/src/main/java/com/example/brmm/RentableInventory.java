package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class RentableInventory implements Serializable {

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

    public ArrayList<Instrument> getInstrumentList() {
        return instruments;
    }

    public ArrayList<Part> getPartList() {
        return parts;
    }

    public void removeInstrument(int pos) {

        instruments.remove(pos);
        //update db
    }

    public void removePart(int sn) {

        for (Part part : parts) {
            if (sn == part.getSerialNumber()) {
                parts.remove(part);
                break;
            }
        }
        //update db
    }


}