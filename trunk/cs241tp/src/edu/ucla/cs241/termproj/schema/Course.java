package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public class Course {
    private ArrayList<StudentImpl> enrolled;

    private Instructor instructor;

    private Department department;

    private String name;

    private int section;

    private int room;

    public Course() {
    }
    
    public Course(String courseName, int courseSection, int courseRoom) {
        this.name = courseName;
        this.section = courseSection;
        this.room = courseRoom;
    }

    /**
     * Getters and Setters
     */

    public ArrayList<StudentImpl> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(ArrayList<StudentImpl> enrolled) {
        this.enrolled = enrolled;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

}
