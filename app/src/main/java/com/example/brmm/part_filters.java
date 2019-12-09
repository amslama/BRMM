

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
import java.util.Timer;
import java.util.TimerTask;

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

        //Buttons
        final Button cancel_button = findViewById(R.id.cancel_part_filters_button);
        final Button apply_button = findViewById(R.id.apply_part_filters_button);


        //applies filter
        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                String sn;
                double cost;

                name = name_edittext.getText().toString();
                sn = sn_edittext.getText().toString();

                try {
                    cost = Double.parseDouble(cost_edittext.getText().toString());
                } catch (NumberFormatException ex) {cost = -1;}

                Intent thisIntent = new Intent();

                //If there are no parts to filter, return to main screen
                ArrayList<Part> parts  = (ArrayList<Part>)thisIntent.getSerializableExtra("partslist");
                if (parts == null)
                    finish();

                 parts = filterParts(parts, name, sn, cost);
                Intent intent = new Intent();
                intent.putExtra("partList", parts);

                setResult(RESULT_OK,intent);
                finish();
            }
        });

        //goes back to main activity
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    //main method for filtering the parts
    public   ArrayList<Part> filterParts(ArrayList<Part> aList, String name, String sn, double cost) {
        ArrayList<Part> filter = aList;
        if (!name.equals(""))
            filter =  filterByName(filter, name);

        if (!sn.isEmpty())
            filter = filterBySn(filter,sn);

        if (cost != -1)
            filter = filterByCost(filter,cost);

        return  filter;
    }

    //filters parts by name
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

    //filters parts by serial number
    public ArrayList<Part> filterBySn(ArrayList<Part> parts, String sn) {
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

    //filters parts by cost
    public ArrayList<Part> filterByCost(ArrayList<Part> parts, double cost) {
        ArrayList<Part> filter = new ArrayList<>();
        for (Part aPart: parts) {
            if (aPart.getCost() == cost)
                filter.add(aPart);
        }
        return filter;
    }
    //Timeout Timer
    private Timer timer;

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        timer.cancel();
        timer.purge();
        timer = new Timer();
        TimerTask timeOutTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timeout from Category Interaction");
                timeOut(); }
        };
        timer.schedule(timeOutTask, main_screen.logoutTime);
    }
    //sets timer to null when no longer on screen
    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        timer.purge();
    }
    //resets timer when resuming activity
    @Override
    protected void onResume() {
        super.onResume();
        timer = new Timer();
        TimerTask timeOutTask = new TimerTask() {
            @Override
            public void run() {
                timeOut(); }
        };
        timer.schedule(timeOutTask, main_screen.logoutTime);
    }
    //return to main screen
    private void timeOut(){
        Intent intent = new Intent();
        intent.putExtra("timeOut", true);
        setResult(RESULT_OK,intent);
        finish();
    }


}
