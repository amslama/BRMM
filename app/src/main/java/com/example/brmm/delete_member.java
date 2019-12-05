package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class delete_member extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_member);

        //Textview
        TextView header = findViewById(R.id.delete_member_header);

        //Dropdowns
        final Spinner pick_spin = findViewById(R.id.member_delete_member_dropdown);

        //Buttons
        Button ok_button = findViewById(R.id.ok_delete_member_button);
        Button cancel_button = findViewById(R.id.cancel_delete_member_button);

        final ArrayList<Integer> members = new ArrayList<>();
        final ArrayList<BandMember> temp = (ArrayList<BandMember>) getIntent().getSerializableExtra("memberlist");
        if (temp != null) {
            for (BandMember bm : temp) {
                members.add(bm.getUID());
            }
            if (members != null) {
                ArrayAdapter<Integer> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, members);
                pick_spin.setAdapter(memberAdapter);
            }
        }
        final BandMember bm = new BandMember();

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("bandmember", bm);
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
                if(pick_spin.getSelectedItem()!=null)
                {
                    bm = pick_spin.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                section = "";
            }
        });
    }
}
