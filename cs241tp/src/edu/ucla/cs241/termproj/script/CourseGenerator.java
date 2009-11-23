package edu.ucla.cs241.termproj.script;

import java.util.ArrayList;
import java.util.Random;

import edu.ucla.cs241.termproj.schema.Course;

public class CourseGenerator {
    
    private final int MAXSECTION = 400;
    private final int MAXROOM = 5000;
    
    Random randomGenerator = new Random();
    
    public CourseGenerator() {
    }
    
    public Course createCourse(String departmentName) {
        String departmentPrefix = departmentName.substring(0, 2);
        int section = randomGenerator.nextInt(MAXSECTION);
        int room = randomGenerator.nextInt(MAXROOM);
        Course course = new Course(departmentPrefix+section, section, room);
        return course;
    }
    
    public ArrayList<Course> createCourses(String departmentName, int count) {
        // Build Courses
        ArrayList<Course> courses = new ArrayList<Course>();
        for(int x = 0; x <= count; x++){
            courses.add(createCourse(departmentName));
        }
        return courses;
    }

}
