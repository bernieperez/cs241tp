package pojo.detached;
/*
 * CJTest.java -- test Cache Java
 *
 */

import Sample.PPerson;

import java.sql.DriverManager;
import java.sql.Connection;

import com.jalapeno.ObjectManager;
import com.jalapeno.ApplicationContext;

/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest2 {
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
        PPerson    person = null;
        boolean delete = false;

        String connectiontype = null;

        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-user"))
                username = args[++i];
            else if (args[i].equals("-password"))
                password = args[++i];
            else if (args[i].equals("-host"))
                host = args[++i];
            else if (args[i].equals("-delete"))
                delete = true;
            else if (args[i].equalsIgnoreCase("-connectiontype"))
                connectiontype = args[++i];
            else
                {
                    System.out.println ("Unknown option: " + args[i]);
                    return;
                }


        try {
            /* Connect to this machine, in the SAMPLES namespace */
            ObjectManager objectManager;

            if (connectiontype == null || connectiontype.equalsIgnoreCase ("cache")){
                String           url="jdbc:Cache://" + host + ":1972/SAMPLES";
                objectManager = ApplicationContext.createObjectManager (url,
                    username, password);
            } else
                objectManager = connect (connectiontype, host, username, password);

            /* Create a new instance of Sample.Person */
            person = new PPerson ();

            /* Set some properties */
            person.setName("Doe, Joe A");
            person.setSSN(generateSSN ());

            /* Fetch some properties */
            System.out.println( "Name: " + person.getName() );
            System.out.println( "SSN:  " + person.getSSN() );
            // empty field
            System.out.println( "DOB:  " + person.getDOB() );
            // Save instance of Person

            Object id = objectManager.save (person, false);
            System.out.println( "Saved with id:  " + id);
            System.out.println( "Object ID is:  " +  person.getId ());

            if (delete)
                {
                    objectManager.removeFromDatabase (person);
                }

            objectManager.close ();


        } catch (Exception ex) {
            System.out.println( "Caught exception: " + ex.getClass().getName() + ": " + ex.getMessage() );
            ex.printStackTrace();
        }
    }

    public static ObjectManager connect (String type, String host,
                                          String username, String password)
        throws Exception
    {
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
                    ("jdbc:microsoft:sqlserver://" + host + ":1433;DatabaseName=SAMPLES;SelectMethod=cursor",
                        username, password);
            }
        else if (type.equalsIgnoreCase ("oracle"))
            {
                Class.forName ("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection
                    ("jdbc:oracle:thin:@barbados.iscinternal.com:1521:barbados",
                        "CACHE_SYS", "SYS");
            }
        else
            {
                throw new Exception ("Unknown connection type: " + type);
            }

        ObjectManager  objectManager = ApplicationContext.createObjectManager (connection);

        return objectManager;
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

