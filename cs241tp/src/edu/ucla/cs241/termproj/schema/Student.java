package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public class Student extends Person {

    private int sid;

    private ArrayList<Course> courses;

    public Student() {

    }

    public int getStudentID() {
        return sid;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

}
