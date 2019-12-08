package com.example.brmm;

import android.util.Base64;

public class Encryption {
    private String seed = "brmm project";
    private String stringEncoded = "";
    private String decoded = "";


    //encodes the string
    public String encode(String password){
        stringEncoded = Base64.encodeToString(password.getBytes(), 0);
        return stringEncoded;
    }

    //decodes the string
    public String decode(String password){
        decoded = new String(Base64.decode(password,0));
        return decoded;
    }




}
