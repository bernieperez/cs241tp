package njb;

/*
 * CJTest.java -- test Cache Java
 *
 */

import com.intersys.objects.*;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest2m {
    /**
     * The main entry point for the test program
     *
     * @param args Array of parameters passed to the application
     * via the command line.
     */
    public static void main( String[] args ) {
        Database         dbconnection = null;
        String           host = "localhost";
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for default
        Sample.Person    person = null;


        String type = "mysql";
        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-user"))
                username = args[++i];
            else if (args[i].equals("-password"))
                password = args[++i];
            else if (args[i].equals("-host"))
                host = args[++i];
            else if (args[i].equals("-type"))
                type = args[++i];
            else
                {
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

            /* Create a new instance of Sample.Person */
            person = new Sample.Person( dbconnection );

            /* Set some properties */
            person.setName("Doe, Joe A");
            person.setSSN(generateSSN ());

            /* Fetch some properties */
            System.out.println( "Name: " + person.getName() );
            System.out.println( "SSN:  " + person.getSSN() );
            // empty field
            System.out.println( "DOB:  " + person.getDOB() );

            // Save instance of Person
            person.save();

            System.out.println ("Saved id: " + person.getId());

            /* de-assign the person object */
            dbconnection.closeObject(person.getOref());
            person = null;

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

    private static java.util.Random random;
    private static String generateSSN ()
    {
        if (random == null)
            random = new java.util.Random ();

        StringBuffer sb = new StringBuffer ();
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append('-');
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append('-');
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        
        return sb.toString();
    }
}

/*
 * End-of-file
 *
 */

