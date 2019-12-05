

package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.brmm.R;
import com.example.brmm.Part;

import java.util.ArrayList;

public class part_filters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_filters);

        //Textviews
        TextView header = findViewById(R.id.part_filters_header);
        TextView name_textview = findViewById(R.id.name_part_filters_textview);
        TextView compwith_textview = findViewById(R.id.name_part_filters_textview);
        TextView sn_textview = findViewById(R.id.name_part_filters_textview);
        TextView cost_textview = findViewById(R.id.name_part_filters_textview);

        //Edittexts
        final EditText name_edittext = findViewById(R.id.name_part_filters_edittext);
        final EditText sn_edittext = findViewById(R.id.sn_part_filters_edittext);
        final EditText cost_edittext = findViewById(R.id.cost_part_filters_edittext);

        //Dropdowns
        final Spinner compwith_spin = findViewById(R.id.compwith_part_filters_dropdown);

        //Recyclerview
        final RecyclerView compwith_rview = findViewById(R.id.compwith_part_filters_rview);

        //Buttons
        final Button add_button = findViewById(R.id.add_part_filters_button);
        final Button cancel_button = findViewById(R.id.cancel_part_filters_button);
        final Button apply_button = findViewById(R.id.apply_part_filters_button);

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_edittext.setText("");
                sn_edittext.setText("");
                cost_edittext.setText("");
            }
        });

        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                int sn;
                double cost;

                name = name_edittext.getText().toString();
                try {sn = Integer.parseInt(sn_edittext.getText().toString());}
                catch (NumberFormatException ex) {sn = 0;}

                try {
                    cost = Double.parseDouble(cost_edittext.getText().toString());
                } catch (NumberFormatException ex) {cost = -1;}

                ArrayList<Part> parts = filterParts(null, name, sn, cost);
                Intent intent = new Intent();
                intent.putExtra("partList", parts);
                setResult(RESULT_OK,intent);
                finish();
            }
        });


    }
    public   ArrayList<Part> filterParts(ArrayList<Part> aList, String name, int sn, double cost) {
        ArrayList<Part> filter = aList;
        if (!name.equals(""))
            filter =  filterByName(filter, name);

        if (sn != 0)
            filter = filterBySn(filter,sn);

        if (cost != -1)
            filter = filterByCost(filter,cost);



        return  filter;
    }

    public ArrayList<Part> filterByName(ArrayList<Part> parts, String name) {
        ArrayList<Part> filter = new ArrayList<>();
        for (Part aPart: parts) {
            if (aPart.getName().equals(name)) {
                filter.add(aPart);
                break;
            }
        }
        return filter;
    }

    public ArrayList<Part> filterBySn(ArrayList<Part> parts, int sn) {
        ArrayList<Part> filter = new ArrayList<>();
        for (Part aPart: parts) {
            if (aPart instanceof Part) {
                if (((Part) aPart).getSerialNumber() == sn) {
                    filter.add(aPart);
                    break;
                }

            }
        }
        return filter;
    }

    public ArrayList<Part> filterByCost(ArrayList<Part> parts, double cost) {
        ArrayList<Part> filter = new ArrayList<>();
        for (Part aPart: parts) {
            if (aPart.getCost() == cost)
                filter.add(aPart);
        }
        return filter;
    }

}
