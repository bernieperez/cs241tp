package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;
import java.util.Random;

public class Department {
    private String name;

    private ArrayList<Course> courses = new ArrayList<Course>();
    
    private ArrayList<Instructor> employeed = new ArrayList<Instructor>();
    
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
    
    public Course getRandomCourse() {
        return courses.get(new Random().nextInt(courses.size()));
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
