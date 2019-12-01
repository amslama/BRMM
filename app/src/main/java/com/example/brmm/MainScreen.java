package com.example.brmm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;

public class MainScreen extends AppCompatActivity {

    private boolean facultyRights = false;
    private String add_string = "Add";
    private String edit_string = "Edit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Spinner inv_Dropdown = findViewById(R.id.Inventory_Dropdown);
        final Button filter_Button = findViewById(R.id.Button_Filters);
        filter_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(),PartFilters.class);
                startActivityForResult(openFilter,00);
            }
        });
        final Button logout_Button = findViewById(R.id.Logout_Button);
        logout_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),LoginScreen.class);
                startActivityForResult(intent,99);
            }
        });
        final RecyclerView inv_View = findViewById(R.id.Object_List);

        //Buttons for all
        final Button add_Button = findViewById(R.id.Add_Button);
        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), add_edit_part.class);
                getIntent().putExtra(Intent.EXTRA_TEXT, add_string);
                startActivity(openFilter);
            }
        });
        final Button remove_Button = findViewById(R.id.Remove_button);

        //Buttons for Band Members and Instruments
        final Button notes_Button = findViewById(R.id.Edit_Note_Button);\
        notes_Button.setVisibility(View.INVISIBLE);

        //Buttons for Band Members
        final Button edit_Member_Button = findViewById(R.id.Edit_Member_Button);
        edit_Member_Button.setVisibility(View.INVISIBLE);
        final Button set_Lead_Button = findViewById(R.id.Add_Section_Lead_Button);
        set_Lead_Button.setVisibility(View.INVISIBLE);
        final Button add_remove_Section_Button = findViewById(R.id.Delete_Section_Button);
        add_remove_Section_Button.setVisibility(View.INVISIBLE);

        //Buttons for Instruments
        final Button checkout_Button = findViewById(R.id.Checkout_Button);
        checkout_Button.setVisibility(View.INVISIBLE);


        inv_Dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if(inv_Dropdown.getSelectedItem() == "Parts")
               {
                   notes_Button.setVisibility(View.INVISIBLE);
                   edit_Member_Button.setVisibility(View.INVISIBLE);
                   set_Lead_Button.setVisibility(View.INVISIBLE);
                   add_remove_Section_Button.setVisibility(View.INVISIBLE);
                   checkout_Button.setVisibility(View.INVISIBLE);

                   filter_Button.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent openFilter = new Intent(getBaseContext(),PartFilters.class);
                           startActivityForResult(openFilter,0);
                       }
                   });

                   add_Button.setOnClickListener(new View.OnClickListener(){
                       @Override
                       public void onClick(View v) {
                           Intent openFilter = new Intent(getBaseContext(),add_edit_part.class);
                           getIntent().putExtra(Intent.EXTRA_TEXT,("Add Part"));
                           startActivityForResult(openFilter,0);
                       }
                   });

                   /*
                   import parts
                    */

               }
                if(inv_Dropdown.getSelectedItem() == "Instruments")
                {
                    notes_Button.setVisibility(View.INVISIBLE);
                    edit_Member_Button.setVisibility(View.INVISIBLE);
                    set_Lead_Button.setVisibility(View.INVISIBLE);
                    add_remove_Section_Button.setVisibility(View.INVISIBLE);
                    checkout_Button.setVisibility(View.VISIBLE);
                    filter_Button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent openFilter = new Intent(getBaseContext(),InstrumentFilters.class);
                            startActivityForResult(openFilter,0);
                        }
                    });
                    /*
                    import instruments
                     */
                }

                if(inv_Dropdown.getSelectedItem() == "Band Members")
                {
                    notes_Button.setVisibility(View.VISIBLE);
                    edit_Member_Button.setVisibility(View.VISIBLE);
                    set_Lead_Button.setVisibility(View.VISIBLE);
                    add_remove_Section_Button.setVisibility(View.VISIBLE);
                    checkout_Button.setVisibility(View.INVISIBLE);
                    filter_Button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent openFilter = new Intent(getBaseContext(),BandMemberFilters.class);
                            startActivityForResult(openFilter,0);
                        }
                    });
                    /*
                    import Band Members
                     */
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        logout_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent login = new Intent(getBaseContext(),LoginScreen.class);
                startActivityForResult(login,7);
            }
        });



    }

}
