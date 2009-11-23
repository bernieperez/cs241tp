package edu.ucla.cs241.termproj.script;

import java.util.ArrayList;

import edu.ucla.cs241.termproj.schema.Course;
import edu.ucla.cs241.termproj.schema.Department;
import edu.ucla.cs241.termproj.schema.Instructor;
import edu.ucla.cs241.termproj.schema.InstructorImpl;
import edu.ucla.cs241.termproj.schema.Person;

public class DataGenerator {
    // Large Population or Small
    private final static int SMALL = 400;
    private final static int LARGE = 4000;
    private final static int NUMINSTRUCCOURSE = 3;

    /**
     * Creates data to populate our schema.
     */
    public static void main(String[] args) {
        int size = 0;
        if (args.length > 0) {
            size = Integer.parseInt(args[0]);
        }
        if (size == 0) {
            size = SMALL;
        } else {
            size = LARGE;
        }
        
        // Generate Departments
        ArrayList<Department> departments = new ArrayList<Department>();
        DepartmentGenerator dg = new DepartmentGenerator();
        for (String name : dg.getAllDepartments()) {
            departments.add(new Department(name));
        }
        
        // Generate Instructors and give them Courses
        PersonGenerator pg = new PersonGenerator();
        CourseGenerator cg = new CourseGenerator();
        for (Department department : departments) {
            // Generate an Instructor and give him 3
            for(int x = 0; x <= size; x++){
                Instructor instructor = (Instructor) pg.createPerson((Person) new InstructorImpl());
                // Instructor <-> Department
                ((InstructorImpl) instructor).setAssigned(department);
                department.addInstructorToDepartment(instructor);
                
                // Added Courses to this instructor
                for(int i = 0; i <= NUMINSTRUCCOURSE; i++) {
                    Course course = cg.createCourse(department.getName());
                    // Setup Relationship Course <-> Department
                    course.setDepartment(department);
                    department.addCourseToDepartment(course);
                    // Setup Course <-> Instructor
                    course.setInstructor(instructor);
                    ((InstructorImpl) instructor).addTaughtCourse(course);
                }
            }
        }
                
    }

}
