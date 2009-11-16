
/*
 * CJTest.java -- test Cache Java
 *
 */

import com.intersys.classes.RegisteredObject;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.Id;
import com.intersys.objects.reflect.CacheClass;
import com.intersys.objects.reflect.CacheField;


/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class LightDynamicBindingSample {
    /**
     * The main entry point for the test program
     *
     * @param args Array of parameters passed to the application
     * via the command line.
     */
    public static void main( String[] args )
        throws Exception
    {
        Database         dbConnection = null;
        String           host = "localhost";
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for default
        String port = "1972";
        String namespace = "SAMPLES";
        String           className = "Sample.Person";
        String           id = "1";


        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-user"))
                username = args[++i];
            else if (args[i].equals("-password"))
                password = args[++i];
            else if (args[i].equals("-host"))
                host = args[++i];
            else if (args[i].equals("-port"))
                port = args[++i];
            else if (args[i].equals("-namespace"))
                namespace = args[++i];
            else if (args[i].equals("-class"))
                className = args[++i];
            else if (args[i].equals("-id"))
                id = args[++i];
            else {
                System.out.println ("Unknown option: " + args[i]);
                return;
            }

        String  url="jdbc:Cache://" + host + ":" + port +"/" + namespace;

        dbConnection = CacheDatabase.getLightDatabase (url, username, password);

        CacheClass cacheClass = dbConnection.getCacheClass (className);
        Object obj = cacheClass.openObject (new Id(id));

        showObject (obj, "");
    }

    private static void showObject (Object obj, String ident)
        throws CacheException
    {
        // Real class may be different. For example an instance of Sample.Person
        // may be in fact an instance of Sample.Employee
        CacheClass realClass = ((RegisteredObject) obj).getCacheClass ();

        CacheField[] cFields = realClass.getFields ();

        for (int i = 0; i < cFields.length; i++)
            {
                CacheField cFld = cFields[i];
                String fName = cFld.getName ();
                Object value = cFld.get(obj);
                if (value instanceof RegisteredObject){
                    System.out.println (ident + fName + ":");
                    showObject (value, ident + "\t");
                } else {
                    System.out.println (ident + fName + " = " + value);
                }
            }
    }
}

/*
 * End-of-file
 *
 */

