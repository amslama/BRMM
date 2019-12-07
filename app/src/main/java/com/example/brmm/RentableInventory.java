package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class RentableInventory implements Serializable {

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

    public void removeInstrument(Instrument ins) {

        for(Instrument instrument : instruments)
        {
            if(ins.getId() == instrument.getId())
            {
                instruments.remove(instrument);
                break;
            }
        }
        //update db
    }

    public void changePart(int pos, Part part)
    {
        parts.set(pos,part);
    }

    public void changeInstrument(int pos, Instrument instrument)
    {
        instruments.set(pos,instrument);
    }

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


}