/*
 * Dump an object
 *
 */
import com.intersys.objects.*;
import com.intersys.objects.reflect.CacheClass;
import com.intersys.classes.ObjectHandle;


/**
 * This is a console program to test out Cache' Java 2.0
 *
 */

public class DumpObject {
    /**
     * The main entry point for the test program
     *
     * @param args Array of parameters passed to the application
     * via the command line.
     */
    public static void main( String[] args ) {
        Database         dbconnection = null;
        String           url="jdbc:Cache://localhost:1972/SAMPLES";
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for default
        ObjectServerInfo info;

        boolean isQuick = false;
        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-quick"))
                isQuick = true;

        try {
            /* Connect to this machine, in the SAMPLES namespace */
            if (isQuick)
                dbconnection = CacheDatabase.getLightDatabase (url, username, password);
            else
                dbconnection = CacheDatabase.getDatabase (url, username, password);


        String clsName = "Sample.Person";
        String id = "1";
        if (args.length > 0)
            {
                id = args[0];
            }
        if (args.length > 1)
            {
                clsName = args[1];
            }

        CacheClass cl = dbconnection.getCacheClass (clsName);

        ObjectHandle oh = (ObjectHandle)cl.openObject(new Id(id));

        ObjectDump ow = new ObjectDump (oh);

        ow.dump (System.out);

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

