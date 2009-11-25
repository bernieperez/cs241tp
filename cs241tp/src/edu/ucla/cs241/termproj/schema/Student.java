package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public interface Student {

    public int getStudentID();
    
    public void setStudentID(int studentID);
    
    public void addCourse(Course course);

    public ArrayList<Course> getCoursesEnrolled();

}