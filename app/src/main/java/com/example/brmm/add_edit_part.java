package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class add_edit_part extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_part);
        Intent intent = getIntent();
        String headerValue = intent.getStringExtra(Intent.EXTRA_TEXT);

        TextView header = findViewById(R.id.part_filters_header_text);
        header.setText(headerValue);
    }
}
