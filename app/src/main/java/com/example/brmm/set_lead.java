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
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class set_lead extends AppCompatActivity {
    private int count;

    //Textviews
    private TextView header;
    private TextView section_textview;
    private TextView member_textview;
    private TextView current_textview;

    //Dropdowns
    private Spinner section_spin;
    private Spinner member_spin;

    //Buttons
    private Button set_lead_button;
    private Button cancel_button;
    private Button remove_lead_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_lead);

        //Textviews
        header = findViewById(R.id.set_lead_header);
        section_textview = findViewById(R.id.section_set_lead_textview);
        member_textview = findViewById(R.id.member_set_lead_textview);
        current_textview = findViewById(R.id.current_set_lead_textview);

        //Dropdowns
        section_spin = findViewById(R.id.section_set_lead_dropdown);
        member_spin = findViewById(R.id.member_set_lead_dropdown);

        //Buttons
        set_lead_button = findViewById(R.id.set_lead_set_lead_button);
        cancel_button = findViewById(R.id.cancel_set_lead_button);
        remove_lead_button = findViewById(R.id.delete_set_lead_button);


        ArrayList<String> sectionlist = getIntent().getStringArrayListExtra("sectionlist");
        if (sectionlist != null) {
            ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, sectionlist);
            section_spin.setAdapter(memberAdapter);
        }
        final ArrayList<Integer> members = new ArrayList<>();
        final ArrayList<BandMember> temp = (ArrayList<BandMember>) getIntent().getSerializableExtra("memberlist");
        if (temp != null) {
            for (BandMember bm : temp) {
                if(bm instanceof Student) {
                    members.add(bm.getUID());
                }
            }
            if (!members.isEmpty()) {
                ArrayAdapter<Integer> memberAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, members);
                member_spin.setAdapter(memberAdapter);
            }
        }

        member_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count = 0;
                if (member_spin.getSelectedItem() != null && temp != null) {
                    int memberUID = Integer.parseInt(member_spin.getSelectedItem().toString());
                    for (BandMember band : temp) {
                        if (band.getUID() == memberUID) {
                            if(!band.getSection().equals(""))
                            {
                                System.out.println(band.getSection());
                                current_textview.setText(band.getSection());
                            }
                            else
                            {
                                current_textview.setText("");

                            }
                            Intent intent = getIntent();
                            intent.putExtra("count", count);
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

        set_lead_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(section_spin.getSelectedItem()!=null && member_spin.getSelectedItem()!=null) {
                    BandMember member = temp.get(count);
                    member.setSection(section_spin.getSelectedItem().toString());
                    Intent intent = getIntent();
                    intent.putExtra("member", member);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        remove_lead_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(section_spin.getSelectedItem()!=null && member_spin.getSelectedItem()!=null) {
                    BandMember member = temp.get(count);
                    if(!member.getSection().equals(""))
                    {
                        member.setSection("");
                    }
                    else
                    {
                        String invalid = "Member is not a section lead";
                        Toast incomplete_toast = Toast.makeText(getApplicationContext(), invalid, Toast.LENGTH_LONG);
                        incomplete_toast.show();
                    }
                    Intent intent = getIntent();
                    intent.putExtra("member", member);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
