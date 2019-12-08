package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class checkout_instrument extends AppCompatActivity {

    //Dropdowns
    private Spinner instrument_spin;
    private Spinner member_spin;
    //Buttons
    private Button cancel_button;
    private Button ok_button;
    private int count;

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
        member_spin = findViewById(R.id.owner_checkout_dropdown);

        //Buttons
        cancel_button = findViewById(R.id.cancel_checkout_button);
        ok_button = findViewById(R.id.checkout_checkout_button);


        final ArrayList<String> instrumentlist = new ArrayList<>();
        final ArrayList<Instrument> temp = (ArrayList<Instrument>) getIntent().getSerializableExtra("instrumentlist");
        if (temp != null) {
            for (Instrument ins : temp) {

                instrumentlist.add(ins.getName());
            }
            if (instrumentlist != null) {
                ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, instrumentlist);
                instrument_spin.setAdapter(memberAdapter);
            }
        }


        final ArrayList<Integer> members = new ArrayList<>();
        final ArrayList<BandMember> temp_bm = (ArrayList<BandMember>) getIntent().getSerializableExtra("memberlist");
        if (temp_bm != null) {
            for (BandMember bm : temp_bm) {
                if (bm instanceof Student) {
                    members.add(bm.getUID());
                }
            }
            if (!members.isEmpty()) {
                ArrayAdapter<Integer> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, members);
                member_spin.setAdapter(memberAdapter);
            }
        }

        instrument_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count = 0;
                if (member_spin.getSelectedItem() != null && temp != null) {
                    for (String str : instrumentlist) {
                        if (str == instrument_spin.getSelectedItem().toString()) {
                            Intent intent = getIntent();
                            intent.putExtra("instrument", temp.get(count));
                            intent.putExtra("count_instrument", count);
                            break;
                        }
                        count++;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        member_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count = 0;
                if (member_spin.getSelectedItem() != null && temp != null) {
                    int memberUID = Integer.parseInt(member_spin.getSelectedItem().toString());
                    for (BandMember band : temp_bm) {
                        if (band.getUID() == memberUID) {
                            Intent intent = getIntent();
                            intent.putExtra("member",temp_bm.get(count));
                            intent.putExtra("count_member", count);
                            break;
                        }
                        count++;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
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
