package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public class InstructorImpl extends Person implements Instructor {

    private double salary;

    private ArrayList<Course> taught = new ArrayList<Course>();
    
    private Department assigned;

    public InstructorImpl() {
    }
    
    public void addTaughtCourse(Course course) {
        taught.add(course);
    }
    
    public double getSalary() {
        return salary;
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

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ArrayList<Course> getTaught() {
        return taught;
    }

    public void setTaught(ArrayList<Course> taught) {
        this.taught = taught;
    }

    public Department getAssigned() {
        return assigned;
    }

    public void setAssigned(Department assigned) {
        this.assigned = assigned;
    }
    
}
