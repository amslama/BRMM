package com.example.brmm;


public class Faculty extends BandMember {
    private boolean facultyRights;
    public Faculty() {
        super();
        facultyRights = true;
    }

    public boolean isFacultyRights() {
        return facultyRights;
    }

    public void setFacultyRights(boolean facultyRights) {
        this.facultyRights = facultyRights;
    }
}
