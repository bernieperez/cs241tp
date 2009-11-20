package edu.ucla.cs241.termproj.script;

import java.util.ArrayList;

import de.beimax.janag.Namegenerator;
import edu.ucla.cs241.termproj.schema.Course;

public class DataGenerator {

    /**
     * Creates data to populate our schema.
     */
    public static void main(String[] args) {
        // Testing Name Generator
        Namegenerator ng = new Namegenerator("languages.txt", "semantics.txt");
        String[] males = ng.getRandomName("U.S. Human (1990)", "Male - Top 500+", 100);
        String[] females = ng.getRandomName("U.S. Human (1990)", "Female - Top 500+", 100);
        for (String name : males) {
            System.out.println(name);
        }
        for (String name : females) {
            System.out.println(name);
        }
        System.out.println();

        // Testing Department Generator
        DepartmentGenerator dg = new DepartmentGenerator();
        ArrayList<String> departments = dg.getAllDepartments();
        for (String department : departments) {
            System.out.println(department);
        }
        System.out.println("\nSome random Departments");
        for (int x = 0; x <= 4; x++) {
            System.out.println(dg.getRandomDepartment());
        }

        // Generate Random Courses for a Department
        CourseGenerator cg = new CourseGenerator();
        for (String department : departments) {
            System.out.println("\nGenerating course for department: " + department);
            for (Course course : cg.getRandomCourses(department, 10)) {
                System.out.println(course.getName());
            }
        }
    }

}
