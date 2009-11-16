package njb;

/*
 * CJTest1.java -- test Cache Java
 *
 */
import com.intersys.objects.*;
import com.intersys.VersionInfo;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest1m {
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
        ObjectServerInfo info;

        //The following line seems to be loading all but com.intersys classes

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
                        ("jdbc:mysql://" + host + "/Sample",
                            username, password);
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

            /* Print out server info */
            info = dbconnection.getServerInfo();
            System.out.println( "   Connect info:     " + info.connectionInfo );
            System.out.println( "   Cache' version:   " + info.cacheSystemVersion );
            System.out.println( "   Object version:   " + info.cacheObjectVersion );
            System.out.println( "   Protocol Client version: " +
                                info.protocolClientVersion);
            System.out.println( "   Protocol Server version: " +
                                info.protocolServerVersion);
            System.out.println( "   Namepspace:       " + info.namespace);
            System.out.println( "   Is Unicode?       " + info.isUnicode);
            System.out.println( "   Server locale:    " + info.locale);
            System.out.println( "   System orefs?     " + info.systemOrefsSupported);
            System.out.println( "   Process #         " + info.processNumberString);

            /* Get information from MANIFEST.MF via java.lang.Package class */
            System.out.println( "   CacheDB.jar Specification version  " + Package.getPackage("com.intersys.cache").getSpecificationVersion());
            System.out.println( "   CacheDB.jar Implementation version  " + Package.getPackage("com.intersys.cache").getImplementationVersion());

            /* Use com.intersys.VersionInfo class for build information */
            System.out.println( "   Java Client build version  " + VersionInfo.getClientVersion());


            /* Close the connection */
            dbconnection.close();

        } catch (Exception ex) {
            System.out.println( "Caught exception: " + ex.getClass().getName()
                                                     + ": " + ex.getMessage() );
            ex.printStackTrace();
        }
    }
}

/*
 * End-of-file
 *
 */

