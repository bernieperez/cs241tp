package edu.ucla.cs241.termproj.queries.versant;

import java.util.ArrayList;
import java.util.Enumeration;

import com.versant.trans.Query;
import com.versant.trans.QueryResult;
import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;

import edu.ucla.cs241.termproj.queries.util.QueryAbs;
import edu.ucla.cs241.termproj.schema.Course;
import edu.ucla.cs241.termproj.schema.Student;

/**
 * Query 1 Simple
 * 
 * List all Student taking Course e.g, "CS241A". This Select query will give
 * us a good baseline on performance our both data population sizes.
 * 
 */
public class Query1 extends QueryAbs {
    private VQLQuery query_course;

    private ArrayList<Student> students;

    private String course_name;

    public Query1(String name, TransSession session) {
        super(name, session);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void query() {
        Enumeration<Course> course = query_course.execute();

        Course c = course.nextElement();
        students = c.getEnrolled();
    }

    @Override
    public void init() {
        // Get course name to be used in query
        Query query_name = new Query(session, "select selfoid from edu.ucla.cs241.termproj.schema.Course");
        QueryResult result = query_name.execute();

        course_name = ((Course) result.next()).getName();

        query_name.close();

        query_course = new VQLQuery(session,
                "select selfoid from edu.ucla.cs241.termproj.schema.Course where name like '" + course_name + "'");
    }

    @Override
    public void tearDown() {
        // Just save results
        results = "Course Name: " + course_name;
        results += "Number of Students: " + students.size();
        results += "Elapse Time in ms: " + getResults();
        results += "--------------------";
    }
}
