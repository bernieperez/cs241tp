package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public class Instructor extends Person {
	private double salary;
    private ArrayList<Course> courses;
    
    public Instructor(){
    	/* do nothing */
    }
    
    public double getSalary(){
    	return this.salary;
    }
    
    public ArrayList<Course> getCourses(){
    	return this.courses;
    }
    
}
