package edu.ucla.cs241.termproj.queries.versant;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;

import edu.ucla.cs241.termproj.queries.util.QueryAbs;
import edu.ucla.cs241.termproj.schema.Department;
import edu.ucla.cs241.termproj.schema.Instructor;
import edu.ucla.cs241.termproj.schema.Student;

/**
 * Query 3 Medium
 * 
 * Show which Department has the highest paid Instructors (avg. as a
 * department) This will mimic GroupBy test against the ODBMS.
 */
public class Query3 extends QueryAbs {
    VQLQuery query_name;
    Department dept_max = null;
    double max_avg_salary;

    public Query3(String name, TransSession session) {
        super(name, session);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void query() {
        Enumeration<Department> enum_departments = query_name.execute();

        dept_max = null;
        max_avg_salary = 0;

        while (enum_departments.hasMoreElements()) {
                Department current_department = enum_departments.nextElement();
                ArrayList<Instructor> instructors = current_department.getEmployeed();
                
                // Reset variables
                double total_salary = 0;
                int count = 0;
                
                // Iterate through instructors
                Instructor foo;
                for (Iterator<Instructor> i = instructors.iterator(); i.hasNext();) {
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
        

    }

    @Override
    public void init() {
        // Get example department to be used as base search department
        query_name = new VQLQuery(session,
                        "select selfoid from edu.ucla.cs241.termproj.schema.Department");
    }

    @Override
    public void tearDown() {
        results = "Elapse Time in ms: " + getResults();
        results += "Department: " + dept_max.getName() + " Avg. Salary: " + max_avg_salary;
        results += "----------------------------";
    }
}
