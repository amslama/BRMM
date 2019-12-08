package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class Faculty extends BandMember implements Serializable {
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Faculty(){

    }

    public Faculty(String firstname, String lastname, String ulid, String role, int UID){
        setFname(firstname);
        setLname(lastname);
        setUlid(ulid);
        setUID(UID);
        this.role = role;
    }

    @Override
    public boolean equals (Object member)
    {
        if(!(member instanceof Faculty)) {
            if (this.getClass() != member.getClass()) return false;
            return false;
        }
        return equals((Faculty) member);
    }

    private boolean equals(Faculty member)
    {
        return member.UID == UID;
    }
}
