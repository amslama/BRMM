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

    public void changeMember(int pos, BandMember member) {
        bandMembers.set(pos, member);
    }

    public int size() {
        return bandMembers.size();
    }

}
