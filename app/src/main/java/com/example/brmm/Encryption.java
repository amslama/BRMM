package com.example.brmm;

import android.util.Base64;

public class Encryption {
    private String seed = "";
    private String message = "I like pie";
    private String stringEncoded = "";
    private String decoded = "";


    public String encode(String password){
        stringEncoded = Base64.encodeToString(password.getBytes(), 0);
        return stringEncoded;
    }

    public String decode(){
        decoded = new String(Base64.decode(stringEncoded,0));
        return decoded;
    }




}
