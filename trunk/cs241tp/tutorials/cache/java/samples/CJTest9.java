/*
 * CJTest.java -- test Cache Java
 *
 */

import java.util.Iterator;
import com.intersys.objects.*;

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
    public static void main( String[] args )
        throws Exception
    {
        Database         dbconnection = null;
        String           host = "localhost";
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for default
        String                  query = null;

        boolean isQuick = false;
        boolean isReadOnly = false;
        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-quick"))
                isQuick = true;
            else if (args[i].equals("-ro"))
                isReadOnly = true;
            else if (args[i].equals("-user"))
                username = args[++i];
            else if (args[i].equals("-password"))
                password = args[++i];
            else if (args[i].equals("-host"))
                host = args[++i];
            else if (args[i].equals("-query"))
                query = args[++i];
            else {
                System.out.println ("Unknown option: " + args[i]);
                return;
            }

        String           url="jdbc:Cache://" + host + ":1972/SAMPLES";

        try {
            /* Connect to this machine, in the SAMPLES namespace */
            if (isQuick)
                dbconnection = CacheDatabase.getLightDatabase (url, username, password);
            else if (isReadOnly)
                dbconnection = CacheDatabase.getReadOnlyDatabase (url, username, password);
            else
                dbconnection = CacheDatabase.getDatabase (url, username, password);


            System.out.println( "Connected." );

            //Sample.Person.checkAllFieldsValid(dbconnection);

            /* Open instances of the Sample.Person object */
            
            String sql = "Name %startsWith ?";
            String[] qargs = {query};
            Iterator people = dbconnection.openByQuery ("Sample.Person", sql, qargs);

            while (people.hasNext()){
                Sample.Person person = (Sample.Person) people.next();

                /* Fetch some properties */
                System.out.print ("ID: " + person.getId() );
                System.out.print ("\tName: " + person.getName() );
                System.out.print ("\tSSN:  " + person.getSSN() );
                System.out.println("\tDOB:  " + person.getDOB() );
            }

            /* Now let us do a more complex query with join of two tables 
               and particular order.
             */
            System.out.println ();
            System.out.println ("Now let us do a more complex query with join of two tables ordered by company name:");
            System.out.println ();
            
            sql = "SELECT Sample.Employee.%ID, Sample.Company.Name from Sample.Employee, Sample.Company WHERE Sample.Employee.Company = Sample.Company.ID and Sample.Company.Name %StartsWith ? order by Sample.Company.Name";

            people = dbconnection.openByQuery (sql, qargs);

            while (people.hasNext()){
                Sample.Employee person = (Sample.Employee) people.next();

                /* Fetch some properties */
                System.out.print ("ID: " + person.getId() );
                System.out.print ("\tName: " + person.getName() );
                System.out.println("\tCompany:  " + person.getCompany().getName());
            }

            /* Close the connection */
            dbconnection.close();

        } catch (CacheException ex) {
            System.out.println( "Caught exception: " + ex.getClass().getName() + ": " + ex.getMessage() );
            ex.printFullTrace(System.out);
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

