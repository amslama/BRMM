package com.example.brmm;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Part extends Rentable implements Serializable
{
    //private String category;
    private int serialNumber;
    private ArrayList<Category> compWith;



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

    public Part(double cost, String name, String category, int serialNumber){
        this.cost = cost;
        this.name = name;
        this.category = category;
        this.serialNumber = serialNumber;

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
