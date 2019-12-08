package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class BandMemberInventory implements Serializable {
    //list of all current BandMembers
    private ArrayList<BandMember> bandMembers;

    public BandMemberInventory() {
        bandMembers = new ArrayList<BandMember>();
    }

    //Gets all BandMembers in inventory
    public ArrayList<BandMember> getBandMembers() {
        return bandMembers;
    }

    //Gets all BandMembers that are Faculty
    public ArrayList<Faculty> getFaculty() {

        ArrayList<Faculty> faculty = new ArrayList<>();
        for (BandMember member : bandMembers) {
            if (member instanceof Faculty) {
                faculty.add((Faculty) member);
            }
        }
        return faculty;
    }

    //Gets all BandMembers that are Students
    public ArrayList<Student> getStudents() {

        ArrayList<Student> students = new ArrayList<>();
        for (BandMember member : bandMembers) {
            if (member instanceof Student) {
                students.add((Student) member);
            }
        }
        return students;
    }


    //Removes a specific BandMember
    public void removeBandMember(BandMember member) {
        for (BandMember bm : bandMembers)
            if (member.getUID() == bm.getUID()) {
                bandMembers.remove(member);
                break;
            }
        //update db
    }

    //adds a BandMember
    public void addBandMember(BandMember newBM) {
        bandMembers.add(newBM);
        //update db
    }


    //Changes position of a BandMember
    public void changeMember(int pos, BandMember member) {
        bandMembers.set(pos, member);
    }

    //Gives number of BandMembers in inventory
    public int size() {
        return bandMembers.size();
    }

    //deletes all BandMembers
    public void clearInventory() {
        bandMembers.clear();
    }

}
