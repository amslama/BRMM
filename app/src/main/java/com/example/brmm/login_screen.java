
package com.example.brmm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login_screen extends AppCompatActivity {
    private int counter;
    DatabaseWrapper wrapper;
    Thread thread2;

    public login_screen() {
        counter = 0;
    }

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        //Textviews
        TextView header = findViewById(R.id.login_screen_header);

        //Edittexts
        final EditText ULID = findViewById(R.id.ULID_Textbox);
        final EditText password = findViewById(R.id.Password_Textbox);

        //Buttons
        final Button OK = findViewById(R.id.Login_OK_Button);
        final Button stdntLogin = findViewById(R.id.Student_Button);

        final Toast toast = Toast.makeText(this, "Too many failed attempts", Toast.LENGTH_SHORT);


        DatabaseConnection connection = new DatabaseConnection();
        Thread thread = new Thread(connection);
        thread.start();

        try{
            thread.join();
        }
        catch (Exception e){
            System.out.println("Connection join failed");
        }

        wrapper = new DatabaseWrapper(connection.getConnection());




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
                            login(ULID_retrieved);
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


    private void login(String ulid){
        boolean isFaculty = false;
        wrapper.setMethod("getFacultyRights");
        wrapper.setUlid(ulid);
        thread2 = new Thread(wrapper);
        thread2.start();
        try{
            thread2.join();
        }
        catch (Exception e){
            System.out.println("Validation Failed");
        }

        isFaculty = wrapper.getFacultyRights();
        if (isFaculty)
            System.out.println("Is a faculty");
        else
            System.out.println("Is NOT a faculty");
            Intent mainScreen = new Intent(this, main_screen.class);
            mainScreen.putExtra("ISFACULTY", isFaculty);
            startActivity(mainScreen);



    }

    private boolean checkCredentials(String ulid, String password) {
        wrapper.setMethod("checkLogin");
        wrapper.setUlid(ulid);
        wrapper.setPassword(password);
        thread2 = new Thread(wrapper);
        thread2.start();
        try{
            thread2.join();
        }
        catch (Exception e){
            System.out.println("Login join failed");
        }
        return true;
        //return wrapper.getValidation();

    }



}
