package com.example.brmm;

public class Student extends BandMember {
    private Section section;
    private boolean sectionLeader;

    public Student(){
        super();
        sectionLeader = false;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public boolean isSectionLeader() {
        return sectionLeader;
    }

    public void setSectionLeader(boolean sectionLeader) {
        this.sectionLeader = sectionLeader;
    }

}
