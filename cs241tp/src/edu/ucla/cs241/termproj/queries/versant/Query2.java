package edu.ucla.cs241.termproj.queries.versant;

import java.util.Enumeration;

import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;

import edu.ucla.cs241.termproj.queries.util.QueryAbs;
import edu.ucla.cs241.termproj.schema.Department;
import edu.ucla.cs241.termproj.schema.Instructor;

/**
 * Query 2 Simple
 * 
 * Show the Instructor who is teaching the most courses in Department e.g.,
 * "Mathematics" This Multiple Join query will count all Instructor Course
 * from a Department. It will test how efficient each ODBMS can extract data
 * from an Object.
 * 
 */
public class Query2 extends QueryAbs {
    VQLQuery query;
    Instructor instructor_max;
    int max_courses;

    public Query2(String name, TransSession session) {
        super(name, session);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void query() {
        Enumeration<Instructor> result = query.execute();
        max_courses = 0;
        instructor_max = null;

        while (result.hasMoreElements()) {
            Instructor instructor = result.nextElement();
            if (instructor.getCoursesTaught().size() > max_courses) {
                instructor_max = instructor;
                max_courses = instructor_max.getCoursesTaught().size();
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init() {
        // Get example department to be used as base search department
        VQLQuery query_name = new VQLQuery(session, "select selfoid from edu.ucla.cs241.termproj.schema.Department");
        Enumeration<Department> enum_course = query_name.execute();

        // Pick first department for now to act as base comparison
        String dept_name =  (enum_course.nextElement()).getName();

        query = new VQLQuery(session,
                "select selfoid from edu.ucla.cs241.termproj.schema.InstructorImpl where assigned->name like '"
                        + dept_name + "'");
    }

    @Override
    public void tearDown() {
        results = "Elapse Time in ms: " + getResults();
        results += "Instructor Name: " + instructor_max.getName() + " Dept: "
                + instructor_max.getAssignedDepartment().getName() + " # Courses: " + max_courses;
        results += "----------------------------";
    }
}
