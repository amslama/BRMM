package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class checkout_instrument extends AppCompatActivity {

    //Dropdowns
    private Spinner instrument_spin;
    private Spinner owner_spin;
    //Buttons
    private Button cancel_button;
    private Button ok_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_instrument);

        //Textviews
        TextView header = findViewById(R.id.checkout_header);
        TextView instrument_textview = findViewById(R.id.instrument_checkout_textview);
        TextView owner_textview = findViewById(R.id.owner_checkout_textview);

        //Dropdowns
        instrument_spin = findViewById(R.id.instrument_checkout_dropdown);
        owner_spin = findViewById(R.id.owner_checkout_dropdown);

        //Buttons
        cancel_button = findViewById(R.id.cancel_checkout_button);
        ok_button = findViewById(R.id.checkout_checkout_button);

        initializeSpinners();
    }

    private void initializeSpinners() {
        Intent intent = new Intent();
        ArrayList<Instrument> ins_list = (ArrayList<Instrument>) intent.getSerializableExtra("INSTRUMENT");
        List<String> ins_tostring = new ArrayList<String>();
        for (Instrument instrument : ins_list) {
            ins_tostring.add(instrument.getName());
        }
        ArrayAdapter<String> ins_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ins_tostring);
        instrument_spin.setAdapter(ins_adapter);


        ArrayList<Instrument> owner_list = (ArrayList<Instrument>) intent.getSerializableExtra("INSTRUMENT");
        List<String> owner_tostring = new ArrayList<String>();
        for (Instrument instrument : ins_list) {
            owner_tostring.add(instrument.getCurrentOwner());
        }
        ArrayAdapter<String> owner_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, owner_tostring);
        instrument_spin.setAdapter(owner_adapter);
    }

    private void init_buttons() {

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("instrument", instrument_spin.getSelectedItem().toString());
                intent.putExtra("owner", owner_spin.getSelectedItem().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //cancels all fields
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
}
