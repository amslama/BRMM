package com.example.brmm;

import android.widget.SectionIndexer;

import java.util.ArrayList;

public class UserInventory {
    private ArrayList<BandMember> students;
    private ArrayList<BandMember> faculty;
    private ArrayList<Section> sections;

    public void addStudent(BandMember student) {
        students.add(student);
    }

    public void removeStudent(BandMember student) {
        students.remove(student);
    }

    public void addFaculty(BandMember remFaculty) {
        faculty.add(remFaculty);
    }

    public void removeFaculty(BandMember remFaculty) {
        faculty.remove(remFaculty);
    }
}
