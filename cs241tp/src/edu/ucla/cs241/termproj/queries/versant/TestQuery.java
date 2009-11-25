package edu.ucla.cs241.termproj.queries.versant;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;

import edu.ucla.cs241.termproj.schema.Course;
import edu.ucla.cs241.termproj.schema.Department;
import edu.ucla.cs241.termproj.schema.Instructor;
import edu.ucla.cs241.termproj.schema.InstructorImpl;
import edu.ucla.cs241.termproj.schema.Person;
import edu.ucla.cs241.termproj.schema.Student;
import edu.ucla.cs241.termproj.schema.StudentImpl;

public class TestQuery {

	private Date date;
	private long start_time = 0;
	private long end_time = 0;

	TransSession session;

	public TestQuery(TransSession session) {
		this.session = session;
		
		query1();
		query2();
		query3();
		query4();
		//query5();
		
	}

	public void initTimer() {
		this.start_time = 0;
		this.end_time = 0;

	}

	public void startTimer() {
		this.start_time = System.currentTimeMillis();
	}

	public void stopTimer() {
		this.end_time = System.currentTimeMillis() - this.start_time;
	}

	/**
	 * Query 1 Simple
	 * 
	 * List all Student taking Course e.g, "CS241A". This Select query will give
	 * us a good baseline on performance our both data population sizes.
	 * 
	 */
	public void query1() {
		System.out.println("Query 1");
		// Get course name to be used in query
		VQLQuery query_name = new VQLQuery(session,
		"select selfoid from edu.ucla.cs241.termproj.schema.Course");
		Enumeration enum_course = query_name.execute();

		// Pick first course for now to act as base comparison
		String course_name = ((Course) enum_course.nextElement()).getName();
		VQLQuery query = new VQLQuery(session,
				"select selfoid from edu.ucla.cs241.termproj.schema.Course where name like '"
				+ course_name + "'");

		// Set time variables and begin query
		initTimer();
		startTimer();
		Enumeration result = query.execute();
		Course c = (Course) result.nextElement();
		System.out.println("Section: " + c.getSection());
		ArrayList<Student> students = c.getEnrolled();

		stopTimer();
		float end_seconds = this.end_time / 1000F;
		System.out.println("Elapse Time in Seconds: " + end_seconds);
		int count = 0;
		for (Student s : students) {
			System.out.println("Student ID: " + s.getStudentID());
			count++;
		}

		

		System.out.println("Total Students: " + count);
		System.out.println("--------------------");
		Object obj = null;
		int counter = 0;

		/*
		 * ArrayList<Person> students = new ArrayList<Person>(); while
		 * (result.hasMoreElements()) { Person currentStudent = (Person)
		 * result.nextElement(); if (currentStudent.getName().split(" ")[1]
		 * .equalsIgnoreCase("perez")) { students.add(currentStudent); } }
		 * System.out.println("Total Students: " + students.size()); for (Person
		 * student : students) { System.out.println(((StudentImpl)
		 * student).getName() + " Courses:"); for (Course course :
		 * ((StudentImpl) student).getCoursesEnrolled()) {
		 * System.out.println(course.getName()); } System.out.println(); }
		 */

		// result.close();
		// query.close();
	}

	/**
	 * Query 2 Simple 
	 * 
	 * Show the Instructor who is teaching the most courses in
	 * Department e.g., "Mathematics" This Multiple Join query will count all
	 * Instructor Course from a Department. It will test how efficient each
	 * ODBMS can extract data from an Object.
	 * 
	 */
	public void query2() {
		System.out.println("Query 2");
		// Get example department to be used as base search department
		VQLQuery query_name = new VQLQuery(session,
		"select selfoid from edu.ucla.cs241.termproj.schema.Department");
		Enumeration enum_course = query_name.execute();

		// Pick first department for now to act as base comparison
		String dept_name = ((Department) enum_course.nextElement()).getName();

		
		VQLQuery query = new VQLQuery(
				session,
				"select selfoid from edu.ucla.cs241.termproj.schema.InstructorImpl where assigned->name like '"
				+ dept_name + "'");
		initTimer();
		startTimer();
		Enumeration result = query.execute();
		int max_courses = 0;
		InstructorImpl instructor_max = null;

		while (result.hasMoreElements()) {
			InstructorImpl instructor = (InstructorImpl) result.nextElement();
			if (instructor.getCoursesTaught().size() > max_courses) {
				instructor_max = instructor;
				max_courses = instructor_max.getCoursesTaught().size();
			}
		}
		stopTimer();
		float end_seconds = this.end_time / 1000F;
		System.out.println("Elapse Time in Seconds: " + end_seconds);
		

		System.out.println("Instructor Name: " + instructor_max.getName()
				+ " Dept: " + instructor_max.getAssigned().getName()
				+ " # Courses: " + max_courses);
		System.out.println("----------------------------");
	}

	/**
	 * Query 3 Medium 
	 * 
	 * Show which Department has the highest paid Instructors (avg. as a department)
	 * This will mimic GroupBy test against the ODBMS.
	 */
	public void query3() {
		System.out.println("Query 3");
		// Get example department to be used as base search department
		VQLQuery query_name = new VQLQuery(session,
		"select selfoid from edu.ucla.cs241.termproj.schema.Department");
		initTimer();
		startTimer();
		Enumeration enum_departments = query_name.execute();

		Department dept_max = null;
		double max_avg_salary = 0;

		while(enum_departments.hasMoreElements()){
			Department current_department = (Department)enum_departments.nextElement();
			ArrayList<Instructor> instructors = current_department.getEmployeed();
			double count = 0;
			double total_salary = 0;

			for ( Iterator i = instructors.iterator(); i.hasNext();){
				total_salary += ((Instructor)i.next()).getSalary();
				count += 1;
			}

			if ( total_salary/count > max_avg_salary ){
				max_avg_salary = total_salary/count;
				dept_max = current_department;
			}
		}
		stopTimer();
		float end_seconds = this.end_time / 1000F;
		System.out.println("Elapse Time in Seconds: " + end_seconds);
		System.out.println("Department: " + dept_max.getName() + " Avg. Salary: " + max_avg_salary );
		System.out.println("----------------------------");
	
	}
	/**
	 * Query 4 Medium
	 * 
	 * Give every Instructor and NOT TA in Department "Computer Science" a 10% raise
	 * We will benchmark two items in this query. First is how well the ODBMS deals with 
	 * Interfaces. Also how fast it can update information in the database for an Object.
	 */
	public void query4(){
		System.out.println("Query 4");
		// Used variables
		double raise_percentage = 0.10; // %10 percent raise
		
		// Get example department to be used as base search department
		VQLQuery query_name = new VQLQuery(session,
		"select selfoid from edu.ucla.cs241.termproj.schema.Department");
		Enumeration enum_course = query_name.execute();

		Department department = ((Department) enum_course.nextElement());
		// Pick first department for now to act as base comparison
		String dept_name = department.getName();
		System.out.println("Department: " + dept_name);
		
		// These instructors are not TAs since they are derived from InstructorImpl
		VQLQuery instructors_name = new VQLQuery(session,
		"select selfoid from edu.ucla.cs241.termproj.schema.InstructorImpl");
		initTimer();
		startTimer();
		Enumeration enum_instructors = instructors_name.execute();
		
		while(enum_instructors.hasMoreElements()){
			InstructorImpl instructor = (InstructorImpl)enum_instructors.nextElement();
			if ( instructor.getAssigned().getName().equals(dept_name)){
				double salary = instructor.getSalary();
				// Increase salary by raise percentage amount
				instructor.setSalary(salary + salary*raise_percentage); 
			}
		}
		
		stopTimer();
		float end_seconds = this.end_time / 1000F;
		System.out.println("Elapse Time in Seconds: " + end_seconds);

		System.out.println("----------------------------");
		
	}
}
