/*
 * CJTest6.java -- Class to test the CacheQuery
 *
 */

import com.intersys.objects.*;
import java.sql.SQLException;

/**
 * This class tests the CacheQuery class
 *
 */
public class CJTest6 {

    public static final String DEFAULT_URL = "jdbc:Cache://localhost:1972/SAMPLES";


    /**
     * The main entry point for the test program
     *
     * @param args Array of parameters passed to the application
     * via the command line.
     */
    public static void main (String[] args) {
        Database                server = null;
        String                  url = null;
        String                  username="_SYSTEM";
        String                  password="SYS";
        CacheQuery              cq = null;
        java.sql.ResultSet      rs = null;
        String                  query = "";

        boolean isQuick = false;
        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-quick"))
                isQuick = true;
            else if (args[i].equals("-url"))
                url = args[++i];
            else if (args[i].startsWith("jdbc:Cache:"))
                url = args[i];
            else if (args[i].equals("-query"))
                query = args[++i];
            else {
                System.out.println ("Unknown option: " + args[i]);
                return;
            }

        if (url == null)
            url = DEFAULT_URL;
    

        System.out.println( "Connecting to: " + url );
        
        /* Connect the ObjectServer to the url */
        try {
            if (isQuick)
                server = CacheDatabase.getLightDatabase (url, username, password);
            else
                server = CacheDatabase.getDatabase (url, username, password);


            /* Create a ResultSet */
            System.out.println( "Creating a ResultSet" );
            /* Create a CacheQuery */
            cq = new CacheQuery( server, "Sample.Person", "ByName" );
            /* Execute the query and loop across the returned rows */
            rs = cq.execute(query);
            while (rs.next()) {
                /* Dump the columns in each row */
                String s = "";
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    if (s.length() > 0) {
                        s += ": ";
                    }
                    
                    s += rs.getString( i );
                }
                
                System.out.println( s );
            }

            /* Close the ResultSet object */
            System.out.println("Closing ResultSet");
            rs.close();


            /* Close the object factory */
            System.out.println( "Closing ObjectFactory" );
            server.close();
        } catch (CacheException ex) {
            System.out.println( "Caught exception: " + ex.getClass().getName() + 
                                ": " + ex.getMessage() );
            ex.printFullTrace(System.out);
        } catch (SQLException ex) {
            for (SQLException x = ex; x != null; x=x.getNextException()){
                System.out.println("Caught SQL Exception. [Message: <" + 
                                   x.getMessage() +
                                   "> Error code: <" + x.getErrorCode() +
                                   "> SQL state: <" + x.getSQLState() + 
                                   ">]");
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.println( "Caught exception: " + ex.getClass().getName() + 
                                ": " + ex.getMessage() );
            ex.printStackTrace();
        }

    }
}

/*
 * End-of-file
 *
 */

