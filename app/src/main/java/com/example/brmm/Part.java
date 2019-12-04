package com.example.brmm;

import java.util.ArrayList;

public class Part extends Rentable
{
    private ArrayList<Category> compWith;
    private int serialNumber;

    public Part(){

    }

    public ArrayList<Category> getCompWith() {
        return compWith;
    }

    public void setCompWith(Category compWith) {
        this.compWith.add(compWith);
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
