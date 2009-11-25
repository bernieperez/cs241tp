package edu.ucla.cs241.termproj.script;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import edu.ucla.cs241.termproj.schema.Course;
import edu.ucla.cs241.termproj.schema.Department;
import edu.ucla.cs241.termproj.schema.Instructor;
import edu.ucla.cs241.termproj.schema.InstructorImpl;
import edu.ucla.cs241.termproj.schema.Person;
import edu.ucla.cs241.termproj.schema.Student;
import edu.ucla.cs241.termproj.schema.StudentImpl;
import edu.ucla.cs241.termproj.schema.TAImpl;

public class DataGenerator {
    // Large Population or Small
    public enum PopulationSize {
        TINY, SMALL, LARGE
    }
    private final int SMALL = 40;
    private final int LARGE = 400;
    private final int NUMINSTRUCCOURSE = 3;
    private final int STUDENTSINCOURSE = 20;
    private final int NUMSTUDENTSCOURSE = 4;
    private final int STUDENTID = 1000000;
    private final int TAPERINSTRUCTOR = 10;
    
    private int studentIDcount = 0;
    int size = 0;
    
    // Only allow 1 TA's per 10 instructors
    private Hashtable<Department, Integer> taCount = new Hashtable<Department, Integer>();

    /**
     * Creates data to populate our schema.
     */
    public ArrayList<Department> generate (PopulationSize populationSize) {
        if (populationSize.equals(PopulationSize.SMALL)) {
            size = SMALL;
        } else {
            size = LARGE;
        }
        if (populationSize.equals(PopulationSize.TINY)) {
            size = 2;
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
            for(int x = 0; x < size; x++){
                Instructor instructor = (Instructor) pg.createPerson((Person) new InstructorImpl());
                // Instructor <-> Department
                department.addInstructorToDepartment(instructor);
                ((InstructorImpl) instructor).setAssigned(department);
                ((InstructorImpl) instructor).setSalary((new Random().nextDouble()+0.5)*80);
                // Added Courses to this instructor
                for(int i = 0; i < NUMINSTRUCCOURSE; i++) {
                    Course course = cg.createCourse(department.getName());
                    // Setup Relationship Course <-> Department
                    course.setDepartment(department);
                    department.addCourseToDepartment(course);
                    // Setup Course <-> Instructor
                    course.setInstructor(instructor);
                    ((InstructorImpl) instructor).addTaughtCourse(course);
                }
            }
            
            // Create Students and add them to Random Courses
            int numberStudents = size * NUMINSTRUCCOURSE * STUDENTSINCOURSE / NUMSTUDENTSCOURSE;
            for(int j = 0; j < numberStudents; j++) {
                Student student;
                if (assignAsTA(department)) {
                    // Student is actually a TA and will take over a professors class.
                    student = (Student) pg.createPerson((Person) new TAImpl());
                } else {
                    // Just a regular student.
                    student = (Student) pg.createPerson((Person) new StudentImpl());                    
                }
                ((Student) student).setStudentID(STUDENTID+studentIDcount++); // Student ID 1000000 - 1999999 
                while (student.getCoursesEnrolled().size() < NUMSTUDENTSCOURSE) {
                    Course course = department.getRandomCourse();
                    if (!student.getCoursesEnrolled().contains(course)) { // Not already enrolled
                        if (course.enrollStudent(student)) {
                            // Student was enrolled
                            ((Student) student).addCourse(course);
                        }
                    }
                }
                // If Student is a TA then teach a class that he is not enrolled in
                if (student instanceof Instructor) {
                    ((Instructor) student).setAssigned(department);
                    department.addInstructorToDepartment((Instructor) student);
                    ((Instructor) student).setSalary((new Random().nextDouble()+0.5)*20);
                    // Find a course that this student is NOT enrolled in
                    Course course = department.getRandomCourse();
                    while(student.getCoursesEnrolled().contains(course)) {
                        course = department.getRandomCourse();
                    }
                    // Remove current Instructor
                    ((InstructorImpl)course.getInstructor()).removeCourse(course);
                    // Add TA as Instructor
                    ((Instructor)student).addTaughtCourse(course);
                    course.setInstructor((Instructor) student);
                }
            }
        }
        
        return departments;
    }

    private boolean assignAsTA(Department department) {
        int yes = new Random().nextInt(100);
        // 20% Chance of being a TA
        if (yes < 20) {
            if (taCount.get(department) == null) {
                // No TA's in this department
                taCount.put(department, 0);
                return true;
            } else {
                int count = taCount.get(department);
                count = count / TAPERINSTRUCTOR;
                if (count < taCount.get(department)) {
                    // Still room for more TA's
                    return true;
                } else {
                    // All TA Slots filled
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
