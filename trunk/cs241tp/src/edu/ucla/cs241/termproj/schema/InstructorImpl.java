package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public class InstructorImpl extends Person implements Instructor {

    private double salary;

    private ArrayList<Course> taught;

    public InstructorImpl() {
    }
    
    public ArrayList<Course> getCoursesTaught() {
        return getCourses();
    }

    /**
     * Getters and Setters
     */

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ArrayList<Course> getCourses() {
        return taught;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.taught = courses;
    }

}
