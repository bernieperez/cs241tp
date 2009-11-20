package edu.ucla.cs241.termproj.script;

import java.util.ArrayList;
import java.util.Random;

import edu.ucla.cs241.termproj.schema.Course;

public class CourseGenerator {
    
    private final int MAXSECTION = 400;
    private final int MAXROOM = 5000;
    
    public CourseGenerator() {
    }
    
    public ArrayList<Course> getRandomCourses(String department, int size) {
        String departmentPrefix = department.substring(0, 2);
        int section;
        int room;
        Random randomGenerator = new Random();

        // Build Courses
        ArrayList<Course> courses = new ArrayList<Course>();
        for(int x = 0; x <= size; x++){
            section = randomGenerator.nextInt(MAXSECTION);
            room = randomGenerator.nextInt(MAXROOM);
            courses.add(new Course(departmentPrefix+section, section, room));
        }

        return courses;
    }

}
