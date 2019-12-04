package com.example.brmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class add_section extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_section);

        //Textviews
        TextView header = findViewById(R.id.add_section_header);
        TextView name_textview = findViewById(R.id.name_add_section_textview);

        //Edittexts
        final EditText name_edittext = findViewById(R.id.name_add_section_edittext);

        //Buttons
        Button cancel_button = findViewById(R.id.cancel_add_section_button);
        Button ok_button = findViewById(R.id.ok_add_section_button);

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                name = name_edittext.getText().toString();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_edittext.setText("");
            }
        });

    }
}
