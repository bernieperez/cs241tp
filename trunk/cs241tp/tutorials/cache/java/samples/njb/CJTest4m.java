package njb;

/*
 * CJTest4.java -- test Cache Java
 *
 */

import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.Id;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest4m {
    /**
     * The main entry point for the test program
     *
     * @param args Array of parameters passed to the application
     * via the command line.
     */
    public static void main( String[] args ) {
        Database         dbconnection = null;
        String           host = "localhost";
        Sample.Person    person = null;
        Sample.IAddress   addr = null;
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for default
        Id              id = null;


        String type = "mysql";
        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-id"))
                id = new Id (args[++i]);
            else if (args[i].equals("-host"))
                host = args[++i];
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

            /* Open an existing instance of Sample.Person */
            if (id == null)
                id = new Id( 1 );
            
            person = (Sample.Person) Sample.Person._open( dbconnection, id);

            /* Fetch some properties */
            System.out.println( "Name: " + person.getName() );
            System.out.println( "SSN:  " + person.getSSN() );

            /* Display existing values */
            System.out.println( "Existing Address:" );
            addr = person.getHome();
            addr = person.getHome();
            System.out.println( "Street: " + addr.getStreet() );
            System.out.println( "City:   " + addr.getCity() );
            System.out.println( "State:  " + addr.getState() );
            System.out.println( "Zip:    " + addr.getZip() );

            /* Modify some values */
            addr.setStreet( "One Memorial Drive" );
            addr.setCity( "Cambridge" );
            addr.setState( "MA" );
            addr.setZip( "02142" );

            /* Display the new values */
            System.out.println( "New Address:" );
            System.out.println( "Street: " + addr.getStreet() );
            System.out.println( "City:   " + addr.getCity() );
            System.out.println( "State:  " + addr.getState() );
            System.out.println( "Zip:    " + addr.getZip() );

            /* Save the changes we made to this object instance.

               If there is a problem with the save, such as a key value not
               being unique, then an exception will be thrown and caught by the
               catch() block.
            */
            person._save();

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
}

/*
 * End-of-file
 *
 */

