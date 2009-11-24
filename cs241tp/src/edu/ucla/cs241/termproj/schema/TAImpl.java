package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public class TAImpl extends Person implements Student, Instructor {

    private int sid;

    private double salary;

    private ArrayList<Course> taught = new ArrayList<Course>();

    private ArrayList<Course> enrolled = new ArrayList<Course>();

    private Department assigned;

    public TAImpl() {
    }
    
    public double getSalary() {
        return salary;
    }
    
    public ArrayList<Course> getCoursesEnrolled() {
        return getEnrolled();
    }

    public int getStudentID() {
        return getSid();
    }

    public Department getAssignedDepartment() {
        return getAssigned();
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

    public Department getAssigned() {
        return assigned;
    }

    public void setAssigned(Department assigned) {
        this.assigned = assigned;
    }
}
