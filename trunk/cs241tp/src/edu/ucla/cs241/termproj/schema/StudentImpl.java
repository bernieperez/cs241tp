package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public class StudentImpl extends Person implements Student {

    private int sid;

    private ArrayList<Course> enrolled;

    public StudentImpl() {
    }
    
    public int getStudentID() {
        return getSid();
    }
    
    public ArrayList<Course> getCoursesEnrolled() {
        return getCourses();
    }
    
    public void addCourse(Course course) {
        enrolled.add(course);
    }
    
    /**
     * Getters and Setters
     */

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public ArrayList<Course> getCourses() {
        return enrolled;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.enrolled = courses;
    }

}
