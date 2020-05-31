package com.example.navefirebase;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBref {
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();

    public static DatabaseReference refStudents = FBDB.getReference("Student_Private_Info");
    public static DatabaseReference refStudentGrade = FBDB.getReference("StuGrades");
}
