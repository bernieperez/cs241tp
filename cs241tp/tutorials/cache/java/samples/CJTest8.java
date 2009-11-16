/*
 * CJTest.java -- test Cache Java
 *
 */

import com.intersys.objects.*;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.Date;

/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest8 {
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
        String           namespace = "SAMPLES";
        Sample.Employee  employee = null;
        Sample.IAddress   addr = null;
        Id               id = null;

        boolean isQuick = false;
        boolean isReadOnly = false;
        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-quick"))
                isQuick = true;
            else if (args[i].equals("-ro"))
                isReadOnly = true;
            else if (args[i].equals("-id"))
                id = new Id (args[++i]);
            else if (args[i].equals("-user"))
                username = args[++i];
            else if (args[i].equals("-password"))
                password = args[++i];
            else if (args[i].equals("-host"))
                host = args[++i];
            else if (args[i].equals("-namespace"))
                namespace = args[++i];
            else {
                System.out.println ("Unknown option: " + args[i]);
                return;
            }

        String           url="jdbc:Cache://" + host + ":1972/" + namespace;

        try {
            /* Connect to this machine, in the SAMPLES namespace */
            if (isQuick)
                dbconnection = CacheDatabase.getLightDatabase (url, username, password);
            else if (isReadOnly)
                dbconnection = CacheDatabase.getReadOnlyDatabase (url, username, password);
            else
                dbconnection = CacheDatabase.getDatabase (url, username, password);


            System.out.println( "Connected." );

            /* Open an instance of the Sample.Person object */
            if (id == null)
                id = new Id( 101 );

            if (!(Sample.Employee.exists (dbconnection, id)))
                {
                    System.out.println ("There is no Employee with id " +
                                        id + " in the database.");
                    dbconnection.close();
                    return;
                }

            // Note that though we use class Sample.Person for open we are 
            // getting an instance of Sample.Employee:

            employee = (Sample.Employee) Sample.Person._open( dbconnection, id);

            /* Fetch some properties */
            System.out.println( "ID:     " + employee.getId() );
            System.out.println( "Name:   " + employee.getName() );
            System.out.println( "SSN:    " + employee.getSSN() );
            System.out.println( "DOB:    " + employee.getDOB() );

            /* Attempt to bring in an embedded object */
            addr = employee.getHome();
            System.out.println( "Street: " + addr.getStreet() );
            System.out.println( "City:   " + addr.getCity() );
            System.out.println( "State:  " + addr.getState() );
            System.out.println( "Zip:    " + addr.getZip() );

            Sample.IPerson spouse = employee.getSpouse();
            if (spouse != null)
                System.out.println( "Spouse Name: " + spouse.getName() );

            Sample.ICompany company = employee.getCompany ();

            System.out.println("Works at: " + company.getName ());
            System.out.println("Title:  " + employee.getTitle());
            System.out.println("Salary: " + employee.getSalary());

            java.util.List colleagues = company.getEmployees ();
            System.out.println("Colleagues: ");
            for (java.util.Iterator it = colleagues.iterator ();
                 it.hasNext();)
                System.out.println("\t" + ((Sample.Person)it.next()).getName());

            System.out.println("Notes: ");
            if (employee.getNotesIn() != null){
                BufferedReader notesReader = new BufferedReader (employee.getNotesIn());
                while (notesReader.ready ()){
                    String line = notesReader.readLine ();
                    if (line == null)
                        break;
                    System.out.println (line);
                }
            }

            if (!isReadOnly){
                new PrintWriter (employee.getNotesOut (), true).println ("Reviewed on " + new Date ());
                employee.setName(employee.getName());
                employee.save();
            }


            /* un-assign the person object */
            dbconnection.closeObject(employee.getOref());
            employee = null;

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

