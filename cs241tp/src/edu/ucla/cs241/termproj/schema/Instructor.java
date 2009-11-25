package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public interface Instructor {

    public double getSalary();

    public ArrayList<Course> getCoursesTaught();
    
    public Department getAssignedDepartment();
    
    public void setAssigned(Department department);
    
    public void setSalary(double salary);

    public void addTaughtCourse(Course course);

    public String getName();
}