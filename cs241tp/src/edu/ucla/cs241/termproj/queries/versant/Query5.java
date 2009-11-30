package edu.ucla.cs241.termproj.queries.versant;

import java.util.ArrayList;
import java.util.Enumeration;

import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;

import edu.ucla.cs241.termproj.queries.util.QueryAbs;
import edu.ucla.cs241.termproj.schema.Course;
import edu.ucla.cs241.termproj.schema.Department;
import edu.ucla.cs241.termproj.schema.Instructor;
import edu.ucla.cs241.termproj.schema.Person;
import edu.ucla.cs241.termproj.schema.Student;

/**
 * Query 5 Complex
 * 
 * Show if any Student is married to any TA in a Course they are currently
 * teaching. This will benchmark multiple joins with recursive relationships
 * against the ODBMS.
 */
public class Query5 extends QueryAbs {
    VQLQuery query_name;

    ArrayList<Instructor> TAs;

    public Query5(String name, TransSession session) {
        super(name, session);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void query() {
        Enumeration<Department> enum_departments = query_name.execute();

        TAs = new ArrayList<Instructor>();
        // Loop through every department
        while (enum_departments.hasMoreElements()) {
            Department department = enum_departments.nextElement();
            // Find TA in that department
            ArrayList<Instructor> instructors = department.getEmployeed();
            for (Instructor ta : instructors) {
                if (ta instanceof Student) {
                    if (((Person) ta).isMarried()) {
                        Course teaches = ta.getCoursesTaught().get(0);
                        Person spouse = ((Person) ta).getSpouse();
                        if (teaches.isEnrolled((Student) spouse)) {
                            TAs.add(ta);
                        }
                    }
                }// TA Loop
            }// Instructor loop
        } // Department Loop
    }

    @Override
    public void init() {
        // Get example department to be used as base search department
        query_name = new VQLQuery(session, "select selfoid from edu.ucla.cs241.termproj.schema.Department");
    }

    @Override
    public void tearDown() {
        results = "";
        for (Instructor inst : TAs) {
            results += "Name: " + inst.getName() + " Course TAing: " + inst.getCoursesTaught().get(0).getName()
                    + " Spouse: " + ((Person) inst).getSpouse().getName() + newline;
        }
        results += "Elapse Time in ms: " + getResults();
    }
}
