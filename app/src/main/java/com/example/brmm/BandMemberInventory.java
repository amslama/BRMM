package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class BandMemberInventory implements Serializable {
    private ArrayList<BandMember> bandMembers;

    //list of all current BandMembers
    public BandMemberInventory() {
        bandMembers = new ArrayList<BandMember>();
    }

    public ArrayList<BandMember> getBandMembers() {
        return bandMembers;
    }

    //deletes all BandMembers
    public void clearInventory() {
        bandMembers.clear();
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

    //Returns all BandMembers that are Students
    public ArrayList<Faculty> getFaculty() {

        ArrayList<Faculty> faculty = new ArrayList<>();
        for (BandMember member : bandMembers) {
            if (member instanceof Faculty) {
                faculty.add((Faculty) member);
            }
        }
        return faculty;
    }

    //Returns all BandMembers that are Students
    public ArrayList<Student> getStudents() {

        ArrayList<Student> students = new ArrayList<>();
        for (BandMember member : bandMembers) {
            if (member instanceof Student) {
                students.add((Student) member);
            }
        }
        return students;
    }

    //Changes position of a BandMember
    public void changeMember(int pos, BandMember member) {
        bandMembers.set(pos, member);
    }

    //Gives number of BandMembers in inventory
    public int size() {
        return bandMembers.size();
    }

}
