package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class delete_instrument extends AppCompatActivity {

    private Instrument ins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_instrument);


        //Dropdowns
        final Spinner pick_spin = findViewById(R.id.instrument_delete_instrument_dropdown);

        //Buttons
        Button ok_button = findViewById(R.id.ok_delete_instrument_button);
        Button cancel_button = findViewById(R.id.cancel_delete_instrument_button);

        ins = new Instrument();
        final ArrayList<Integer> instruments = new ArrayList<>();
        final ArrayList<Instrument> temp = (ArrayList<Instrument>) getIntent().getSerializableExtra("instrumentlist");
        if (temp != null) {
            for (Instrument instrument : temp) {
                instruments.add(instrument.getId());
            }
            if (!instruments.isEmpty()) {
                ArrayAdapter<Integer> instrumentAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, instruments);
                pick_spin.setAdapter(instrumentAdapter);
            }
        }

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("instrument", ins);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pick_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(pick_spin.getSelectedItem()!=null && temp!= null)
                {
                    int ins_id = Integer.parseInt(pick_spin.getSelectedItem().toString());
                    for( Instrument instrum : temp)
                    {
                        if(instrum.getId() == ins_id)
                        {
                            ins = instrum;
                            break;
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
