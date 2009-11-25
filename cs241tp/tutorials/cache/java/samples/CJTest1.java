/*
 * CJTest1.java -- test Cache Java
 *
 */
import com.intersys.objects.*;
import com.intersys.VersionInfo;


/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest1 {
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

        String           url="jdbc:Cache://" + host + ":1972/SAMPLES";

        try {
            /* Connect to this machine, in the SAMPLES namespace */
            if (isQuick)
                dbconnection = CacheDatabase.getLightDatabase (url, username, password);
            else if (isReadOnly)
                dbconnection = CacheDatabase.getReadOnlyDatabase (url, username, password);
            else
                dbconnection = CacheDatabase.getDatabase (url, username, password);


           
            /* Print out server info */
           info = dbconnection.getServerInfo();
            System.out.println( "   Connection URL:   " + info.connectionInfo );
            System.out.println( "   Web Server URL:   " + info.webURL );
            System.out.println( "   Cache' version:   " + info.cacheSystemVersion );
            System.out.println( "   Object version:   " + info.cacheObjectVersion );
            System.out.println( "   \tmajor:   " + info.getMajorObjectVersion());
            System.out.println( "   \tminor:   " + info.getMinorObjectVersion());
            System.out.println( "   \tbuild major:   " + 
                                info.getMajorObjectBuildNumber());
            System.out.println( "   \tbuild minor:   " + 
                                info.getMinorObjectBuildNumber());
            System.out.println( "   Protocol Client version: " +
                                info.protocolClientVersion);
            System.out.println( "   Protocol Server version: " +
                                info.protocolServerVersion);
            System.out.println( "   Namepspace:       " + info.namespace);
            System.out.println( "   Is Unicode?       " + info.isUnicode);
            System.out.println( "   Server locale:    " + info.locale);
            System.out.println( "   System orefs?     " + info.systemOrefsSupported);
            System.out.println( "   Process #         " + info.processNumberString + " (" + info.processNumber + ")");

             Get information from MANIFEST.MF via java.lang.Package class 
            System.out.println( "   CacheDB.jar Specification version  " + Package.getPackage("com.intersys.cache").getSpecificationVersion());
            System.out.println( "   CacheDB.jar Implementation version  " + Package.getPackage("com.intersys.cache").getImplementationVersion());

             Use com.intersys.VersionInfo class for build information 
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

