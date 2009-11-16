package pojo.detached;
/*
 * CJTest.java -- test Cache Java
 *
 */

import Sample.IEmployee;
import Sample.IPerson;
import Sample.PPerson;
import Sample.PEmployee;

import java.util.Iterator;

import com.jalapeno.ObjectManager;
import com.jalapeno.ApplicationContext;

/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest9 {
    /**
     * The main entry point for the test program
     *
     * @param args Array of parameters passed to the application
     * via the command line.
     */
    public static void main( String[] args ) {
        String           host = "localhost";
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for default
        String           namespace = "SAMPLES";
        String                  query = "x";

        String connectiontype = null;

        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-user"))
                username = args[++i];
            else if (args[i].equals("-password"))
                password = args[++i];
            else if (args[i].equals("-host"))
                host = args[++i];
            else if (args[i].equals("-query"))
                query = args[++i];
            else if (args[i].equals("-namespace"))
                namespace = args[++i];
            else if (args[i].equalsIgnoreCase("-connectiontype"))
                connectiontype = args[++i];
            else {
                System.out.println ("Unknown option: " + args[i]);
                return;
            }

        try {
            /* Connect to this machine, in the SAMPLES namespace */

            ObjectManager objectManager;

            if (connectiontype == null || connectiontype.equalsIgnoreCase ("cache")){
                String           url="jdbc:Cache://" + host + ":1972/" + namespace;
                objectManager = ApplicationContext.createObjectManager (url,
                    username, password);
            } else
                objectManager = CJTest2.connect (connectiontype, host, username, password);

            String sql = "Name %startsWith ?";
            if ("null".equalsIgnoreCase (query))
                query = null;
            String[] qargs = {query};
            Iterator people = objectManager.openByQuery (PPerson.class, sql, qargs);

            while (people.hasNext()){
                long start = System.currentTimeMillis ();
                IPerson person = (PPerson) people.next();
                long end = System.currentTimeMillis ();

                /* Fetch some properties */
                System.out.print ( "ID: " + objectManager.getId(person) );
                System.out.print ( "Time: " + (end -start) + "ms" );
                System.out.print ("\tName: " + person.getName() );
                System.out.print ("\tSSN:  " + person.getSSN() );
                System.out.println("\tDOB:  " + person.getDOB() );

                //objectManager.purgeFromMemory (person);
                //objectManager.purgeEverything ();
            }

            System.out.println ();
            System.out.println ("Now let us do a more complex query with join of two tables ordered by company name:");
            System.out.println ();

            sql = "SELECT Sample.Employee.%ID, Sample.Company.Name from Sample.Employee, Sample.Company WHERE Sample.Employee.Company = Sample.Company.ID and Sample.Company.Name %StartsWith ? order by Sample.Company.Name";

            people = objectManager.openByQuery (sql, qargs);

            while (people.hasNext()){
                IEmployee employee = (PEmployee) people.next();

                /* Fetch some properties */
                System.out.print ( "ID: " + objectManager.getId(employee) );
                System.out.print ("\tName: " + employee.getName() );
                System.out.println("\tCompany:  " + employee.getCompany().getName());
            }

            objectManager.close ();
        } catch (Exception ex) {
            System.out.println( "Caught exception: " + ex.getClass().getName() + ": " + ex.getMessage() );
            ex.printStackTrace();
        }
    }

}

/*
 * End-of-file
 *
 */

