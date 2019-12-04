package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class set_lead extends AppCompatActivity {

    //Textviews
    private TextView header;
    private TextView instrument_textview;
    private TextView owner_textview;

    //Dropdowns
    private Spinner instrument_spin;
    private Spinner owner_sping;

    //Buttons
    private Button done_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_lead);

        //Textviews
        header = findViewById(R.id.set_lead_header);
        instrument_textview = findViewById(R.id.section_set_lead_textview);
        owner_textview = findViewById(R.id.member_set_lead_textview);

        //Dropdowns
        instrument_spin = findViewById(R.id.section_set_lead_dropdown);
        owner_sping = findViewById(R.id.member_set_lead_dropdown);

        //Buttons
        done_button = findViewById(R.id.done_set_lead_button);

    }
}
