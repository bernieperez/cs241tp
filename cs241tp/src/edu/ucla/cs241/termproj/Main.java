package edu.ucla.cs241.termproj;

import java.util.ArrayList;
import java.util.Enumeration;

import com.versant.trans.Query;
import com.versant.trans.QueryResult;
import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;

import edu.ucla.cs241.termproj.queries.versant.TestQuery;
import edu.ucla.cs241.termproj.schema.Course;
import edu.ucla.cs241.termproj.schema.Department;
import edu.ucla.cs241.termproj.schema.Instructor;
import edu.ucla.cs241.termproj.schema.Person;
import edu.ucla.cs241.termproj.schema.Student;
import edu.ucla.cs241.termproj.schema.StudentImpl;
import edu.ucla.cs241.termproj.script.DataGenerator;
import edu.ucla.cs241.termproj.script.DataGenerator.PopulationSize;


/**
 * Dummy Class to test check in.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run();
    }

    public void query(TransSession session) {
        VQLQuery query = new VQLQuery(session, "select * from edu.ucla.cs241.termproj.schema.StudentImpl");
        Enumeration result = query.execute();
        
        
        Object obj= null;
        int counter = 0;
        System.out.println("====Display All======");

        ArrayList<Person> students = new ArrayList<Person>();
        while (result.hasMoreElements()) {
        	Person currentStudent = (Person)result.nextElement();
        	if (currentStudent.getName().split(" ")[1].equalsIgnoreCase("perez")) {
        		students.add(currentStudent);
        	}        	
        }
        System.out.println("Total Students: " + students.size());
        for(Person student : students) {
        	System.out.println(((StudentImpl)student).getName() + " Courses:");
        	for(Course course : ((StudentImpl) student).getCoursesEnrolled()) {
        		System.out.println(course.getName());
        	}
        	System.out.println();
        }
        //result.close();
        //query.close();		
      }
    
    private void run() {
    	TransSession session = new TransSession("mydb");
    	
        System.out.println("Hello Team Members of CS 241A Term Project!\nLets generate some data.");
        //DataGenerator dg = new DataGenerator();
        
//
//        ArrayList<Department> smallDepartments = dg.generate(PopulationSize.SMALL);
//        System.out.println("\nDone!\nNow lets print out some information about it.\n");
//        printInfo(smallDepartments);
//
//        System.out.println("\nNow printing information about a Large Department\n");        
//        ArrayList<Department> largeDepartments = dg.generate(PopulationSize.LARGE);
//        printInfo(largeDepartments);
        
        System.out.println("\nTINY!!!!!\n");
       //ArrayList<Department> tinyDepartments = dg.generate(PopulationSize.TINY);
        //for ( int i = 0; i < tinyDepartments.size(); ++i){
        //	session.makePersistent(tinyDepartments.get(i));
       // }

        TestQuery query1 = new TestQuery(session);
        //session.commit();
        //printInfo(tinyDepartments);
        //session.endSession();
       
    }

    private void printInfo(ArrayList<Department> departments) {
        // List all Departments
        for(Department department : departments){
            System.out.println("Department: " + department.getName());
            // List all Courses in that departments
            System.out.println("Courses\tSection\tRoom\tInstructor");
            for(Course course : department.getCourses()) {
                String line = course.getName() + "\t" + course.getSection() + "\t" + course.getRoom() + "\t" + ((Instructor)course.getInstructor()).getName();
                if (course.getInstructor() instanceof Student) {
                    line += " - TA";
                }
                System.out.println(line);
            }
            System.out.println();
        }
        
        // List all Enrolled in the courses for each department
        for(Department department : departments){
            for(Course course : department.getCourses()) {
                System.out.println(department.getName() + "\\" + course.getName() + " - " + ((Person)course.getInstructor()).getName());
                System.out.println("Name\t\tSex\tStudentID");
                for(Student student : course.getEnrolled()) {
                    String line = ((Person)student).getName() + "\t" + ((Person)student).getSex() + "\t" + student.getStudentID();
                    if (student instanceof Instructor) {
                        line += " - TA";
                    }
                    System.out.println(line);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

}
