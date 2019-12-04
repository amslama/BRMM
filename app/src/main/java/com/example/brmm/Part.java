package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class Part extends Rentable implements Serializable
{
    private ArrayList<Category> compWith;
    private int serialNumber;

    boolean isSelected;

    public Part(){
        isSelected = false;
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

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
