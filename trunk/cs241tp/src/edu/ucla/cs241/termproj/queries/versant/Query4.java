package edu.ucla.cs241.termproj.queries.versant;

import java.util.Enumeration;

import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;

import edu.ucla.cs241.termproj.queries.util.QueryAbs;
import edu.ucla.cs241.termproj.schema.Department;
import edu.ucla.cs241.termproj.schema.Instructor;
import edu.ucla.cs241.termproj.schema.Student;

/**
 * Query 4 Medium
 * 
 * Give every Instructor (NOT TA) in Department "Computer Science" a 10% raise
 * We will benchmark two items in this query. First is how well the ODBMS deals
 * with Interfaces. Also how fast it can update information in the database for
 * an Object.
 */
public class Query4 extends QueryAbs {
    VQLQuery theDepartment;

    double salary = 0.0;

    int count = 0;

    double raise_percentage = 1.10; // %10 percent raise

    public Query4(String name, TransSession session) {
        super(name, session);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void query() {
        Enumeration<Department> deptRaise = theDepartment.execute();
        Department dRaise = deptRaise.nextElement();
        salary = 0.0;
        count = 0;
        for (Instructor instructor : dRaise.getEmployeed()) {
            if (!(instructor instanceof Student)) {
                instructor.setSalary(instructor.getSalary() * raise_percentage);
                salary += instructor.getSalary();
                count += 1;
            }
        }

        session.commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init() {
        // Get example department to be used as base search department
        VQLQuery query_name = new VQLQuery(session, "select selfoid from edu.ucla.cs241.termproj.schema.Department");
        Enumeration enum_course = query_name.execute();

        Department department = ((Department) enum_course.nextElement());
        // Pick first department for now to act as base comparison
        String dept_name = department.getName();

        theDepartment = new VQLQuery(session,
                "select selfoid from edu.ucla.cs241.termproj.schema.Department where name like '" + dept_name + "'");
    }

    @Override
    public void tearDown() {
        results = "Avg. Salary: " + (salary / (double) count);
        results += "Elapse Time in ms: " + getResults();
        results += "----------------------------";
    }
}
