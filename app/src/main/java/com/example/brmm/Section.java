package com.example.brmm;

import java.util.ArrayList;

public class Section {
    private BandMember leader;
    private ArrayList<BandMember> members;

    public BandMember getLeader() {
        return leader;
    }

    public void setLeader(BandMember leader) {
        this.leader = leader;
    }

    public ArrayList<BandMember> getMembers() {
        return members;
    }
    

    public Section(BandMember newLeader) {
        leader = newLeader;
        members.add(newLeader);
    }

    public void addMember(BandMember sectionMember) {
        members.add(sectionMember);
    }

    public void removeLeader(BandMember sectionMember) {
        leader = null;
    }


}
