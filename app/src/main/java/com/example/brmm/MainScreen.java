package com.example.brmm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class MainScreen extends AppCompatActivity {

    private boolean facultyRights = false;
    //Dropdown
    final Spinner inv_spin = findViewById(R.id.inventory_main_screen_dropdown);

    //Top buttons
    final Button filter_button = findViewById(R.id.filter_main_screen_button);
    final Button logout_button = findViewById(R.id.logout_main_screen_button);

    //buttons for all
    final Button add_button = findViewById(R.id.add_main_screen_button);
    final Button remove_button = findViewById(R.id.remove_main_screen_button);

    //buttons for rentables
    final Button edit_rentable = findViewById(R.id.edit_rentable_main_screen_button);

    //buttons for instruments
    final Button notes_button = findViewById(R.id.edit_note_main_screen_button);
    final Button checkout_button = findViewById(R.id.checkout_main_screen_button);

    //buttons for bandmember
    final Button edit_member_button = findViewById(R.id.edit_member_main_screen_button);
    final Button set_lead_button = findViewById(R.id.add_section_lead_main_screen_button);
    final Button add_section_button = findViewById(R.id.add_section_main_screen_button);
    final Button delete_section_button = findViewById(R.id.delete_section_main_screen_button);

    //Recyclerview
    final RecyclerView inv_View = findViewById(R.id.item_list_main_screen_rview);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);




        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(),PartFilters.class);
                startActivityForResult(openFilter,00);
            }
        });
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),LoginScreen.class);
                startActivityForResult(intent,99);
            }
        });

        //Buttons for all
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), add_part.class);
                startActivity(openFilter);
            }
        });

        set_lead_button.setVisibility(View.INVISIBLE);
        notes_button.setVisibility(View.INVISIBLE);

        edit_rentable.setVisibility(View.VISIBLE);
        edit_rentable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFilter = new Intent(getBaseContext(), edit_part.class);
                startActivity(openFilter);
            }
        });

        //Buttons for Band Members
        edit_member_button.setVisibility(View.INVISIBLE);
        set_lead_button.setVisibility(View.INVISIBLE);
        add_remove_Section_Button.setVisibility(View.INVISIBLE);

        //Buttons for Instruments
        checkout_button.setVisibility(View.INVISIBLE);


        inv_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if(inv_spin.getSelectedItem() == "Parts")
               {
                   notes_button.setVisibility(View.INVISIBLE);
                   edit_member_button.setVisibility(View.INVISIBLE);
                   set_lead_button.setVisibility(View.INVISIBLE);
                   add_remove_Section_Button.setVisibility(View.INVISIBLE);
                   checkout_button.setVisibility(View.INVISIBLE);
                   inv_spin.

                   filter_button.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent openFilter = new Intent(getBaseContext(),PartFilters.class);
                           startActivityForResult(openFilter,0);
                       }
                   });

                   add_button.setOnClickListener(new View.OnClickListener(){
                       @Override
                       public void onClick(View v) {
                           Intent openFilter = new Intent(getBaseContext(), add_part.class);
                           startActivityForResult(openFilter,0);
                       }
                   });

                   /*
                   import parts
                    */

               }
                if(inv_spin.getSelectedItem() == "Instruments")
                {
                    notes_button.setVisibility(View.VISIBLE);
                    edit_member_button.setVisibility(View.INVISIBLE);
                    set_lead_button.setVisibility(View.INVISIBLE);
                    add_remove_Section_Button.setVisibility(View.INVISIBLE);
                    checkout_button.setVisibility(View.VISIBLE);
                    filter_button.setOnClickListener(new View.OnClickListener() {
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

                if(inv_spin.getSelectedItem() == "Band Members")
                {
                    notes_button.setVisibility(View.INVISIBLE);
                    edit_member_button.setVisibility(View.VISIBLE);
                    set_lead_button.setVisibility(View.VISIBLE);
                    add_remove_Section_Button.setVisibility(View.VISIBLE);
                    checkout_button.setVisibility(View.INVISIBLE);
                    filter_button.setOnClickListener(new View.OnClickListener() {
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

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent login = new Intent(getBaseContext(),LoginScreen.class);
                startActivityForResult(login,7);
            }
        });



    }

}
