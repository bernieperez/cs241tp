package edu.ucla.cs241.termproj;

import java.util.ArrayList;

import edu.ucla.cs241.termproj.schema.Course;
import edu.ucla.cs241.termproj.schema.Department;
import edu.ucla.cs241.termproj.schema.Instructor;
import edu.ucla.cs241.termproj.schema.Person;
import edu.ucla.cs241.termproj.schema.Student;
import edu.ucla.cs241.termproj.script.DataGenerator;
import edu.ucla.cs241.termproj.script.DataGenerator.PopulationSize;

/**
 * Test to make sure the Generator is producing valid data.
 */
public class GeneratorTest {

    public static void main(String[] args) {
        GeneratorTest main = new GeneratorTest();
        main.run();
    }

    private void run() {
        DataGenerator dg = new DataGenerator();

//        ArrayList<Department> smallDepartments = dg.generate(PopulationSize.SMALL);
//        System.out.println("\nDone!\nNow lets print out some information about it.\n");
//        printInfo(smallDepartments);
//
//        System.out.println("\nNow printing information about a Large Department\n");
//        ArrayList<Department> largeDepartments = dg.generate(PopulationSize.LARGE);
//        printInfo(largeDepartments);

        System.out.println("\nTINY!!!!!\n");
        ArrayList<Department> tinyDepartments = dg.generate(PopulationSize.TINY);
        printInfo(tinyDepartments);
    }

    private void printInfo(ArrayList<Department> departments) {
        // List all Departments
        for (Department department : departments) {
            System.out.println("Department: " + department.getName());
            // List all Courses in that departments
            System.out.println("Courses\tSection\tRoom\tInstructor");
            for (Course course : department.getCourses()) {
                String line = course.getName() + "\t" + course.getSection() + "\t" + course.getRoom() + "\t"
                        + ((Instructor) course.getInstructor()).getName();
                if (course.getInstructor() instanceof Student) {
                    line += " - TA";
                }
                System.out.println(line);
            }
            System.out.println();
        }

        // List all Enrolled in the courses for each department
        for (Department department : departments) {
            for (Course course : department.getCourses()) {
                System.out.println(department.getName() + "\\" + course.getName() + " - "
                        + ((Person) course.getInstructor()).getName());
                System.out.println("Name\t\tSex\tStudentID");
                for (Student student : course.getEnrolled()) {
                    String line = ((Person) student).getName() + "\t" + ((Person) student).getSex() + "\t"
                            + student.getStudentID();
                    if (student instanceof Instructor) {
                        line += " - TA";
                    }
                    if (((Person) student).isMarried()) {
                        line += " - Married to: " + ((Person) student).getSpouse().getName();
                    }
                    System.out.println(line);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

}
