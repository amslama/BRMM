package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class InstrumentFilters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandmember_filters);
    }

    public ArrayList<Rentable> filterInstrumentInv(ArrayList<Rentable> rentables, String owner, String section, String name, Category category, int ID) {
        ArrayList<Rentable> filter = new ArrayList<>();

        if(category != null)
            filter = filterByCategory(filter, category);


        if (ID != 0)
            filter = filterByID(filter, ID);


        if (!owner.equals(""))
            filter = filterByName(filter, owner);


        if (!name.equals("")) {
            filter = filterByName(filter, name);
        }


        if (!section.equals(""))
            filter = filterBySection(filter, section);




        return filter;

    }



    public ArrayList<Rentable> filterBySection(ArrayList<Rentable> rentables, String section) {
        ArrayList<Rentable> filter = new ArrayList<>();
        for (Rentable rentable : rentables) {
                if(rentable.getSection().equals(section))
                    filter.add(rentable);
            }
        return filter;
    }

    public ArrayList<Rentable> filterByCategory(ArrayList<Rentable> rentables, Category category) {
        ArrayList<Rentable> filter = new ArrayList<>();
        for (Rentable rentable : rentables) {
            if(rentable.getCategory().getSuperCategories().contains(category))
                filter.add(rentable);
        }
        return filter;
    }

    public ArrayList<Rentable> filterByCost(ArrayList<Rentable> rentables, double cost) {
        ArrayList<Rentable> filter = new ArrayList<>();
        for (Rentable rentable : rentables) {
            if(rentable.getCost() == cost)
                filter.add(rentable);
        }
        return filter;
    }

    public ArrayList<Rentable> filterByID(ArrayList<Rentable> rentables, int id) {
        ArrayList<Rentable> filter = new ArrayList<>();
        for (Rentable rentable : rentables) {
            if(rentable.getID() == id) {
                filter.add(rentable);
                break;
            }
        }
        return filter;
    }

    public ArrayList<Rentable> filterByName(ArrayList<Rentable> rentables, String name) {
        ArrayList<Rentable> filter = new ArrayList<>();
        for (Rentable rentable : rentables) {
            if(rentable.getName().equals(name)) {
                filter.add(rentable);
                break;
            }
        }
        return filter;
    }


    public ArrayList<Rentable> filterByOwner(ArrayList<Rentable> rentables, String owner) {
        ArrayList<Rentable> filter = new ArrayList<>();
        for (Rentable rentable : rentables) {
            if(rentable.getCurrentOwner().equals(owner))
                filter.add(rentable);
        }
        return filter;
    }

}
