package edu.ucla.cs241.termproj.schema;

import java.util.ArrayList;

public class Course {
    private final static int MAXSPACE = 24;
    
    private ArrayList<Student> enrolled = new ArrayList<Student>();

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
    
    public boolean enrollStudent(Student student) {
        if (enrolled.size() < MAXSPACE) {
            // They can enroll
            enrolled.add(student);
            return true;
        } else {
            // Class is full
            return false;
        }
    }

    /**
     * Getters and Setters
     */

    public ArrayList<Student> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(ArrayList<Student> enrolled) {
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
