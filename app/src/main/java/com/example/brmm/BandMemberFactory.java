package com.example.brmm;

public class BandMemberFactory {
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
                // throw some exception
                break;
        }
        return member;
    }
}