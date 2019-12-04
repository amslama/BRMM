package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class Faculty extends BandMember implements Serializable {
    private String department;
    private String role;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Faculty(){

    }

    public Faculty(String firstname, String lastname, String ulid, String department, String role, int UID){
        setFname(firstname);
        setLname(lastname);
        setUlid(ulid);
        setUID(UID);
        this.department = department;
        this.role = role;
    }
}
