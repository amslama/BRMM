package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class PartFilters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_filters);
    }

    public ArrayList<Rentable> filterBySerialNum(ArrayList<Rentable> partsList, String serial) {
       ArrayList<Rentable> part = new ArrayList<>();
        for (Rentable temp : partsList) {
            if (temp.id.equals(serial))
                part.add(temp);

        }

    return partsList;
    }
}
