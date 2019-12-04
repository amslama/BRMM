package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class checkout_instrument extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_instrument);

        //Textviews
        TextView header = findViewById(R.id.checkout_header);
        TextView instrument_textview = findViewById(R.id.instrument_checkout_textview);
        TextView owner_textview = findViewById(R.id.owner_checkout_textview);

        //Dropdowns
        Spinner instrument_spin = findViewById(R.id.instrument_checkout_dropdown);
        Spinner owner_sping = findViewById(R.id.owner_checkout_dropdown);

        //Buttons
        Button cancel_button = findViewById(R.id.cancel_checkout_button);
        Button ok_button = findViewById(R.id.checkout_checkout_button);

    }
}
