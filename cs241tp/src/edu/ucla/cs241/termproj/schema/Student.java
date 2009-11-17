package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public interface Student {

    public int getStudentID();

    public ArrayList<Course> getCoursesEnrolled();

}