
package com.example.brmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {
    private int counter;

    public LoginScreen() {
        counter = 0;
    }

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        //Textviews
        TextView header = findViewById(R.id.login_screen_header);

        //Edittexts
        final EditText ULID = (EditText)findViewById(R.id.ULID_Textbox);
        final EditText password = (EditText)findViewById(R.id.Password_Textbox);

        //Buttons
        final Button OK = (Button)findViewById(R.id.Login_OK_Button);
        final Button stdntLogin = (Button)findViewById(R.id.Student_Button);

        //Toast and jam
        final Toast toast = Toast.makeText(this, "Too many failed attempts", Toast.LENGTH_SHORT);

        //Image
        ImageView logo_iview = findViewById(R.id.isu_logo_iview);


        DatabaseConnection connection = new DatabaseConnection();
        Thread thread = new Thread(connection);
        thread.start();

        try{
            thread.join();
        }
        catch (Exception e){
            System.out.println("Joint failed");
        }

        System.out.println("Out of loop");


        //Goes into read-only mode for the app
        stdntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(false);
            }
        });


        //Login for faculty
        OK.setOnClickListener(
                new View.OnClickListener()
                {


                    @Override
                    public void onClick(View view)
                    {
                        String ULID_retrieved = ULID.getText().toString();
                        String password_retrieved = password.getText().toString();
                        //Authenticate(ULID_retrieved,password_retrieved);
                        if(checkCredentials(ULID_retrieved,password_retrieved))
                            login(true);
                        else {
                            counter++;

                            //disables login button for 60 seconds after 5 failed attempts
                            if (counter == 5) {
                                counter = 0;
                                toast.show();
                                OK.setEnabled(false);
                                OK.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        OK.setEnabled(true);
                                    }
                                }, 60000);
                            }
                        }


                    }


                }
        );

    }


    private void login(boolean isFaculty){
        if (isFaculty){
            Intent mainScreen = new Intent(this, MainScreen.class);
            startActivity(mainScreen);
        }

        else {
            //show read only
            Intent mainScreen = new Intent(this, MainScreen.class);
            startActivity(mainScreen);
        }

    }

    private boolean checkCredentials(String ulid, String password) {
        //if in database return true, else return false
        return true;
    }



}
