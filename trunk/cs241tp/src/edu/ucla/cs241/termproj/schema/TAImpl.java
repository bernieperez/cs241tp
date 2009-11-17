package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public class TAImpl extends Person implements Student, Instructor {

    private int sid;
    
    private double salary;

    private ArrayList<Course> taught;

    private ArrayList<Course> enrolled;

    public TAImpl() {
    }
    
    public ArrayList<Course> getCoursesEnrolled() {
        return getEnrolled();
    }

    public int getStudentID() {
        return getSid();
    }

    public ArrayList<Course> getCoursesTaught() {
        return getTaught();
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ArrayList<Course> getTaught() {
        return taught;
    }

    public void setTaught(ArrayList<Course> taught) {
        this.taught = taught;
    }

    public ArrayList<Course> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(ArrayList<Course> enrolled) {
        this.enrolled = enrolled;
    }

}
