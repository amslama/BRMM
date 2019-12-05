package com.example.brmm;

public class BandMemberFactory {
    public static BandMember buildBandMember(String type, String firstname, String lastname, String ulid, String section, boolean sectionLeader, int UID, String notes, String department, String role) {
        BandMember member = null;
        switch (type) {
            case "Student":
                member = new Student(firstname,lastname,ulid,section,sectionLeader,UID,notes);
                break;

            case "Faculty":
                member = new Faculty(firstname,lastname,ulid,role,UID);
                break;

            default:
                // throw some exception
                break;
        }
        return member;
    }
}