package com.example.navefirebase;

public class Grades {
    private String subject;
    private long quarter;
    private long grade;
    private String StuID;


    public Grades(){}

    public Grades(String subject , long quarter , long grade, String StuID){
        this.subject = subject;
        this.quarter = quarter;
        this.grade = grade;
        this.StuID = StuID;
    }

    public String getStuID() {
        return StuID;
    }

    public void setStuID(String StuID) {
        this.StuID=StuID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject=subject;
    }

    public long getQuarter() {
        return quarter;
    }

    public void setQuarter(long quarter) {
        this.quarter=quarter;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long Grade) {
        this.grade=Grade;
    }
}
