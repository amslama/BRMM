package com.example.brmm;

public class BandMemberFactory {
    //returns a BandMember of specific type
    public static BandMember buildBandMember(String type) {
        BandMember member = null;
        switch (type) {
            case "Student":
                member = new Student();
                break;

            case "Faculty":
                member = new Faculty();
                break;

            default:
                break;
        }
        return member;
    }

}