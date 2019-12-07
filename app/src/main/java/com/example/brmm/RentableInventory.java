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

    public void setParts(ArrayList<Part> parts) {
        System.out.println("parts if of size " + parts.size());
        this.parts = parts;
    }

    public void removeInstrument(int pos) {

        instruments.remove(pos);
        //update db
    }


    public void removePart(Part part) {
        for(int i = 0;i < parts.size();i++) {
            Part temp = parts.get(i);
            if (part != null) {
                if (part.getSerialNumber().equals(temp.getSerialNumber())) {
                    if (part.getCost() == temp.getCost()) {
                        if (part.getName().equals(temp.getName()))
                            parts.remove(i);
                        System.out.println("Removal successful");
                        return;
                    }
                }
            }
        }
        System.out.println("REMOVAL FAILEDDDD-------------------------------------------------------------------------------------------------------------------------------------------");
    }


}