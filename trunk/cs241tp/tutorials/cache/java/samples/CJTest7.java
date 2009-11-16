/*
 * CJTest7.java -- Class to test the ResultSet with Dynamic SQL
 *
 */

import com.intersys.objects.*;
import java.sql.SQLException;

/**
 * This class tests the ResultSet class with Dynamic SQL
 *
 */
public class CJTest7 {

    public static final String DEFAULT_URL = "jdbc:Cache://localhost:1972/SAMPLES";


    /**
     * The main entry point for the test program
     *
     * @param args Array of parameters passed to the application
     * via the command line.
     */
    public static void main (String[] args) {
		Database		server = null;
        String        username="_SYSTEM";
        String        password="SYS";
        String        url = null;
        CacheQuery    cq = null;
        java.sql.ResultSet	rs = null;
        String		sql;
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

        if (url == null)
            url = DEFAULT_URL;
    

        System.out.println( "Connecting to: " + url );
        
        /* Connect the ObjectServer to the url */
        try {
            if (isQuick)
                server = CacheDatabase.getLightDatabase (url, username, password);
            else
                server = CacheDatabase.getDatabase (url, username, password);


            /* Create the SQL statement */
            sql = "SELECT ID, Name, DOB, SSN FROM Sample.Person WHERE Name %STARTSWITH ?";
            System.out.println( "SQL: " + sql );
            
            /* Create a ResultSet */
            System.out.println( "Creating a ResultSet with Dynamic SQL" );

			/* Create a CacheQuery */
			cq = new CacheQuery( server, sql );
            
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

