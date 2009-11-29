package edu.ucla.cs241.termproj.queries.versant;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import com.versant.fund.AttrHandleArray;
import com.versant.fund.AttrString;
import com.versant.fund.ClassHandle;
import com.versant.fund.FundQuery;
import com.versant.fund.FundQueryResult;
import com.versant.fund.FundVQLQuery;
import com.versant.fund.Handle;
import com.versant.fund.HandleEnumeration;
import com.versant.fund.Predicate;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.Query;
import com.versant.trans.QueryResult;
import com.versant.trans.TransSession;
import com.versant.trans.VEnumeration;
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
		query5();

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
		Query query_name = new Query(session,
				"select selfoid from edu.ucla.cs241.termproj.schema.Course");
		QueryResult result = query_name.execute();
		
		String course_name = ((Course) result.next()).getName();
		
//		AttrHandleArray  enrolled_array = session.newAttrHandleArray("enrolled");
//		AttrString course_name_ = session.newAttrString("name");
//		Predicate pred = course_name_.eq(course_name);
//		
//		
//		ClassHandle cls = session.locateClass ("edu.ucla.cs241.termproj.schema.Course");
//		Enumeration class_enum = cls.select (pred);
//		
//		System.out.println("Handle: " + ((Course)class_enum.nextElement()).getEnrolled().size());
//		System.out.println("Enrolled Size2: " + course_name);
		
//		int count = 0;
//		while ((hnd = result.next()) != null) {
//		count++;
//		}
		//System.out.println("Found : " + count);
		query_name.close();
		
		VQLQuery query_course = new VQLQuery(session,
				"select selfoid from edu.ucla.cs241.termproj.schema.Course where name like '"
						+ course_name + "'");
		initTimer();
		startTimer();
		Enumeration<Course> course = query_course.execute();
	
		Course c = course.nextElement();
		ArrayList<Student> students = c.getEnrolled();
		stopTimer();
		
		System.out.println("Course Name: " + course_name);
		System.out.println("Number of Students: " + students.size());
		
		System.out.println("Elapse Time in ms: " + this.end_time );
		System.out.println("--------------------");
		/*
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
*/
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
	 * Show the Instructor who is teaching the most courses in Department e.g.,
	 * "Mathematics" This Multiple Join query will count all Instructor Course
	 * from a Department. It will test how efficient each ODBMS can extract data
	 * from an Object.
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
		Enumeration<Instructor> result = query.execute();
		int max_courses = 0;
		Instructor instructor_max = null;

		while (result.hasMoreElements()) {
			Instructor instructor = result.nextElement();
			if (instructor.getCoursesTaught().size() > max_courses) {
				instructor_max = instructor;
				max_courses = instructor_max.getCoursesTaught().size();
			}
		}
		stopTimer();
		System.out.println("Elapse Time in ms: " + this.end_time );

		System.out.println("Instructor Name: " + instructor_max.getName()
				+ " Dept: " + instructor_max.getAssignedDepartment().getName()
				+ " # Courses: " + max_courses);
		System.out.println("----------------------------");
	}

	/**
	 * Query 3 Medium
	 * 
	 * Show which Department has the highest paid Instructors (avg. as a
	 * department) This will mimic GroupBy test against the ODBMS.
	 */
	public void query3() {
		System.out.println("Query 3");
		// Get example department to be used as base search department
		VQLQuery query_name = new VQLQuery(session,
				"select selfoid from edu.ucla.cs241.termproj.schema.Department");
		
		initTimer();
		startTimer();
		Enumeration<Department> enum_departments = query_name.execute();

		Department dept_max = null;
		double max_avg_salary = 0;

		while (enum_departments.hasMoreElements()) {
			Department current_department = enum_departments.nextElement();
			ArrayList<Instructor> instructors = current_department.getEmployeed();
			
			// Reset variables
			double total_salary = 0;
			int count = 0;
			
			// Iterate through instructors
			Instructor foo;
			for (Iterator i = instructors.iterator(); i.hasNext();) {
				foo = (Instructor) i.next();
				if (!(foo instanceof Student)){
					total_salary += foo.getSalary();
					count += 1;
				}
			}

			// Update max avg salary if necessary
			if (total_salary / (double)count > max_avg_salary) {
				max_avg_salary = total_salary / (double)count;
				dept_max = current_department;
			}
		}
		
		stopTimer();
		
		System.out.println("Elapse Time in ms: " + this.end_time );
		System.out.println("Department: " + dept_max.getName()
				+ " Avg. Salary: " + max_avg_salary);
		System.out.println("----------------------------");

	}

	/**
	 * Query 4 Medium
	 * 
	 * Give every Instructor (NOT TA) in Department "Computer Science" a 10%
	 * raise We will benchmark two items in this query. First is how well the
	 * ODBMS deals with Interfaces. Also how fast it can update information in
	 * the database for an Object.
	 */
	
	public void query4() {
		System.out.println("Query 4");
		// Used variables
		double raise_percentage = 1.10; // %10 percent raise

		// Get example department to be used as base search department
		VQLQuery query_name = new VQLQuery(session,
				"select selfoid from edu.ucla.cs241.termproj.schema.Department");
		Enumeration enum_course = query_name.execute();

		Department department = ((Department) enum_course.nextElement());
		// Pick first department for now to act as base comparison
		String dept_name = department.getName();
		System.out.println("Department: " + dept_name);

		//--------------------------------------------------------------
		
		// These instructors are not TAs since they are derived from
		// InstructorImpl
		VQLQuery theDepartment = new VQLQuery(session,
				"select selfoid from edu.ucla.cs241.termproj.schema.Department where name like '"+dept_name+"'");
		
		initTimer();
		startTimer();
		Enumeration<Department> deptRaise = theDepartment.execute();
		Department dRaise = deptRaise.nextElement();
		double salary = 0.0;
		int count = 0;
		for(Instructor instructor : dRaise.getEmployeed()){
			if (!(instructor instanceof Student)) {
				instructor.setSalary(instructor.getSalary()*raise_percentage);
				salary += instructor.getSalary();
				count += 1;
			}
		}
			
		session.commit();
		stopTimer();
				
		System.out.println("Avg. Salary: "+salary/(double)count);
		System.out.println("Elapse Time in ms: " + this.end_time );

		System.out.println("----------------------------");

	}

	/**
	 * Query 5 Complex
	 * 
	 * Show if any Student is married to any TA in a Course they are currently
	 * teaching. This will benchmark multiple joins with recursive relationships
	 * against the ODBMS.
	 */
	@SuppressWarnings("unchecked")
	public void query5(){
		
		System.out.println("Query 5");
		// Get example department to be used as base search department
		VQLQuery query_name = new VQLQuery(session,
				"select selfoid from edu.ucla.cs241.termproj.schema.Department");
		
		initTimer();
		startTimer();
		Enumeration<Department> enum_departments = query_name.execute();
		
		ArrayList<Instructor> TAs = new ArrayList<Instructor>();
		// Loop through every department
		while( enum_departments.hasMoreElements()){
			Department department = enum_departments.nextElement();
			// Find TA in that department
			ArrayList<Instructor> instructors = department.getEmployeed();
			for(Instructor ta : instructors) {
				if (ta instanceof Student) {
					if (((Person)ta).isMarried()){
						Course teaches = ta.getCoursesTaught().get(0);
						Person spouse = ((Person)ta).getSpouse();
						if (teaches.isEnrolled((Student) spouse)) {
							TAs.add(ta);
						}
					}
				}// TA Loop
			}// Instructor loop
		} // Department Loop
		
		stopTimer();
		
		for ( Instructor inst : TAs ){
			System.out.println("Name: " + inst.getName() + " Course TAing: " + inst.getCoursesTaught().get(0).getName() + " Spouse: " + 
					((Person)inst).getSpouse().getName());
		}
		
		System.out.println("Elapse Time in ms: " + this.end_time );
		
	}
}


/*
FundQuery query_enrolled = new FundQuery(session,
"select enrolled from edu.ucla.cs241.termproj.schema.Course");
FundQueryResult res_enrolled = query_enrolled.execute();

Object enr = res_enrolled.nextRow();

System.out.println("Enrolled: " + enr);

//--------------------------------------------------------------

System.out.println("Query 5");

Query query_name1 = new Query(session,
		"select name from edu.ucla.cs241.termproj.schema.Course");

QueryResult res3 = query_name1.execute();

String name_str = (String)res3.next();



//ArrayList<Student> stud = c.getEnrolled();
stopTimer();
float end_seconds1 = this.end_time / 1000F;
System.out.println("Elapse Time in Seconds: " + end_seconds1);
System.out.println("Course Name: " + name_str);
System.out.println("----------------------------");

///------------------------------------------------------

// Get course name to be used in query
FundVQLQuery query_name = new FundVQLQuery(session,
		"select selfoid from edu.ucla.cs241.termproj.schema.Course where name like '"+name_str+"'");
initTimer();
startTimer();

HandleEnumeration result =  query_name.execute();

Handle hnd = result.nextHandle();

AttrString desc = session.newAttrString ("name");
AttrHandleArray all = session.newAttrHandleArray ("enrolled");

System.out.println("Course C1: "+ hnd.get(all).length);
System.out.println("Course C1 Name: "+ hnd.get(desc));
stopTimer();
float end_seconds11 = this.end_time / 1000F;
System.out.println("Elapse Time in Seconds: " + end_seconds11);
//int section_number = (Integer)result.nextRow();

//System.out.println("Section Number: " + section_number);*/