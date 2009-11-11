package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public class Department {
    private String name;

    private ArrayList<Course> courses;

    public Department() {
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

}
