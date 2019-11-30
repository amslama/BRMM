package com.example.brmm;

import java.util.ArrayList;

public class Faculty extends BandMember {
    private String department;
    private String role;

    public Faculty(){

    }

    public Faculty(String firstname, String lastname, String ulid, String department, String role){
        setFname(firstname);
        setLname(lastname);
        setUlid(ulid);
        this.department = department;
        this.role = role;
    }
}
