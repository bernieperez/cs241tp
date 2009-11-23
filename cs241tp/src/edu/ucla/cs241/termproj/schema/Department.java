package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public class Department {
    private String name;

    private ArrayList<Course> courses;
    
    private ArrayList<Instructor> employeed;
    
    public Department(String name) {
        this.name = name;
    }

    public ArrayList<Instructor> getEmployeed() {
        return employeed;
    }
    
    public void addCourseToDepartment(Course course) {
        courses.add(course);
    }
    
    public void addInstructorToDepartment(Instructor instructor) {
        employeed.add(instructor);
    }
    
    /**
     * Getters and Setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
    
    public void setEmployeed(ArrayList<Instructor> employeed) {
        this.employeed = employeed;
    }

}
