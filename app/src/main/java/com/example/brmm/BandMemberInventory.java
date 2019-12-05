package com.example.brmm;

import java.io.Serializable;
import java.util.ArrayList;

public class BandMemberInventory implements Serializable {
    private ArrayList<BandMember> bandMembers;

    public BandMemberInventory()
    {
        bandMembers = new ArrayList<BandMember>();
    }

    public ArrayList<BandMember> getBandMembers() {
        return bandMembers;
    }

    public void clearInventory()
    {
        bandMembers.clear();
    }

    public void removeBandMember(int uid)
    {
        for(BandMember member : bandMembers)
        if(uid == member.getUID())
        {
            bandMembers.remove(member);
            break;
        }
        //update db
    }
    public void addBandMember(BandMember newBM)
    {
        bandMembers.add(newBM);
        //update db
    }
    public int size()
    {
        return bandMembers.size();
    }

}
