package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class BandMemberInventory implements Serializable {
    private ArrayList<BandMember> bandMembers;

    public BandMemberInventory() {
        bandMembers = new ArrayList<BandMember>();
    }

    public ArrayList<BandMember> getBandMembers() {
        return bandMembers;
    }

    public void clearInventory() {
        bandMembers.clear();
    }

    public void removeBandMember(BandMember member) {
        for (BandMember bm : bandMembers)
            if (member.getUID() == bm.getUID()) {
                bandMembers.remove(member);
                break;
            }
        //update db
    }

    public void addBandMember(BandMember newBM) {
        bandMembers.add(newBM);
        //update db
    }

    public ArrayList<Faculty> getFaculty() {

        ArrayList<Faculty> faculty = new ArrayList<>();
        for (BandMember member : bandMembers) {
            if (member instanceof Faculty) {
                faculty.add((Faculty) member);
            }
        }
        return faculty;
    }

    public ArrayList<Student> getStudents() {

        ArrayList<Student> students = new ArrayList<>();
        for (BandMember member : bandMembers) {
            if (member instanceof Student) {
                students.add((Student) member);
            }
        }
        return students;
    }

    public void changeMember(int pos, BandMember member) {
        bandMembers.set(pos, member);
    }

    public int size() {
        return bandMembers.size();
    }

}
