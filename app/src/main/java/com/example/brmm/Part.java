package com.example.brmm;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Part extends Rentable implements Serializable
{
    //private String category;
    private String serialNumber;

    public Part ()
    {
        cost = 0;
        name = "invalid";
        category = null;
        serialNumber = "notreal";

    }

    public Part(double cost, String name, String category, String serialNumber){
        this.cost = cost;
        this.name = name;
        this.category = category;
        this.serialNumber = serialNumber;

    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
