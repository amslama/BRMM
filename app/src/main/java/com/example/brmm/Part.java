package com.example.brmm;

import java.util.ArrayList;

public class Part extends Rentable
{
    //private String category;
    private int serialNumber;

    public Part(double cost, String name, String category, int serialNumber){
        this.cost = cost;
        this.name = name;
        this.category = category;
        this.serialNumber = serialNumber;
    }


}
