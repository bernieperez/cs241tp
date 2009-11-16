package njb;

/*
 * CJTest.java -- test Cache Java
 *
 */

import java.util.Iterator;
import java.sql.Connection;
import java.sql.DriverManager;

import com.intersys.objects.*;
import com.intersys.cache.quick.ThirdPartyDatabase;

/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest9m {
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

        String type = "mysql";
        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-user"))
                username = args[++i];
            else if (args[i].equals("-password"))
                password = args[++i];
            else if (args[i].equals("-host"))
                host = args[++i];
            else if (args[i].equals("-query"))
                query = args[++i];
            else if (args[i].equals("-type"))
                type = args[++i];
            else {
                System.out.println ("Unknown option: " + args[i]);
                return;
            }

        try {
            Connection connection;
            if (type.equalsIgnoreCase ("mysql"))
                {
                    Class.forName ("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection
                        ("jdbc:mysql://" + host + "/Sample", username, password);
                }
            else if (type.equalsIgnoreCase ("mssql"))
                {
                    Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
                    connection = DriverManager.getConnection
                        ("jdbc:microsoft:sqlserver://" + host + ":1433",
                            username, password);
                }
            else if (type.equalsIgnoreCase ("oracle"))
                {
                    Class.forName ("oracle.jdbc.driver.OracleDriver");
                    connection = DriverManager.getConnection
                        ("jdbc:oracle:thin:@barbados.iscinternal.com:1521:barbados", "CACHE_SYS", "SYS");
                }
            else
                {
                    throw new Exception ("Unknown connection type: " + type);
                }
            dbconnection = CacheDatabase.getLightDatabase (connection);
            System.out.println( "Connected to: " + dbconnection.getServerInfo ().connectionInfo);

            //Sample.Person.checkAllFieldsValid(dbconnection);

            /* Open instances of the Sample.Person object */
            
            String sql = "Name LIKE ?";
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
            
            sql = "SELECT Sample.Employee.%ID, Sample.Company.Name from Sample.Employee, Sample.Company WHERE Sample.Employee.Company = Sample.Company.ID and Sample.Company.Name LIKE ? order by Sample.Company.Name";

            people = dbconnection.openByQuery (sql, qargs);

            while (people.hasNext()){
                Object next = people.next();
                Sample.Employee person = (Sample.Employee) next;

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

