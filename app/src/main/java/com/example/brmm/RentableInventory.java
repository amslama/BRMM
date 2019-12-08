package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class RentableInventory implements Serializable {

    //List of things in inventory
    private ArrayList<Instrument> instruments;
    private ArrayList<Part> parts;

    public RentableInventory()
    {
     instruments = new ArrayList<Instrument>();
     parts = new ArrayList<Part>();
    }

    public RentableInventory(ArrayList<Instrument> instruments, ArrayList<Part> parts) {
        this.instruments = instruments;
        this.parts = parts;
    }

    //adds an Instrument to the inventory
    public void addInstrument(Instrument newIns) {
        if(instruments.size()>0) {
            newIns.setId(instruments.get(instruments.size() - 1).getId() + 1);
        }
        else
        {
            newIns.setId(1);
        }
        instruments.add(newIns);
        //update db
    }

    //adds an Part to the inventory
    public void addPart(Part newPart) {
        parts.add(newPart);
        //update db
    }

    //Gets instrument list from inventory
    public ArrayList<Instrument> getInstrumentList() {
        return instruments;
    }

    //Gets part list from inventory
    public ArrayList<Part> getPartList() {
        return parts;
    }

    //removes instrument from inventory
    public void removeInstrument(Instrument ins) {

        for(Instrument instrument : instruments)
        {
            if(ins.getId() == instrument.getId())
            {
                instruments.remove(instrument);
                break;
            }
        }
    }

    //removes a part from the inventory
    public void removePart(Part part) {
        for(Part prt : parts)
        {
            if(prt.getName().equals(part.getName()))
            {
                parts.remove(prt);
                break;
            }
        }
    }

    //changes an instrument
    public void changeInstrument(int pos, Instrument instrument)
    {
        instruments.set(pos,instrument);
    }

    //changes a part
    public void changePart(int pos, Part part)
    {
        parts.set(pos,part);
    }

    //deletes all Rentables
    public void clearInventory() {
        instruments.clear();
        parts.clear();
    }





}