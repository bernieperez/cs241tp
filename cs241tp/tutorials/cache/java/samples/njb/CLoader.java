package njb;

/*
 * CJTest.java -- test Cache Java
 *
 */

import com.intersys.classes.Persistent;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.Id;
import com.intersys.objects.reflect.CacheClass;
import com.intersys.objects.reflect.CacheField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;


/**
 * This is a sample console program to copy data from Cache Database to a thrid
 * party database. Before running this program you must run Exporter program
 * to export database schema definition.
 *
 * <p>
 *
 * This is simple data copier that presumes that:
 *
 * <ul>
 * <li> Tables in third party database are fresh new tables that were never
 * used before.
 * <li> Any referenced data is copied completely using the same method, i.e.
 * ID of referenced objects is (or will be) the same in target database as
 * in the source.
 * </ul>
 *
 * <p>
 * For more sofisticated data copying including partial copying and recursive
 * copying of referenced data please look at <code>SmartLoader</code> class.
 *
 * @see Exporter
 * @see SmartLoader
 *
 */
public class CLoader {
    /**
     * The main entry point for the test program
     *
     * @param args Array of parameters passed to the application
     * via the command line.
     */
    public static void main( String[] args )
        throws Exception
    {
        Database         sourceConnection = null;
        Database         targetConnection = null;
        String           host = "localhost";
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for default
        String port = "1972";
        String namespace = "SAMPLES";
        String           className = "Sample.Person";
        String           id = "1";
        String query = null;


        String type = "mysql";
        int max = 0;
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
            else if (args[i].equals("-query"))
                query = args[++i];
            else if (args[i].equals("-max"))
                max = Integer.parseInt (args[++i]);
            else if (args[i].equals("-type"))
                type = args[++i];
            else {
                System.out.println ("Unknown option: " + args[i]);
                return;
            }

        String  url="jdbc:Cache://" + host + ":" + port +"/" + namespace;

        Connection connection;
        if (type.equalsIgnoreCase ("mysql"))
            {
                Class.forName ("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection
                    ("jdbc:mysql://" + host + "/Sample",
                        "_SYSTEM","SYS");
            }
        else if (type.equalsIgnoreCase ("mssql"))
            {
                Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
                connection = DriverManager.getConnection
                    ("jdbc:microsoft:sqlserver://localhost:1433","_SYSTEM","SYS");
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
        targetConnection = CacheDatabase.getLightDatabase (connection);

        sourceConnection = CacheDatabase.getLightDatabase (url, username, password);


        if (id.equalsIgnoreCase ("all"))
            {
                query = "ID > 0";
            }
        
        if (query != null)
            {
                Iterator it = sourceConnection.openByQuery (className, query);
                Object cur = null;
                for (int i = 0; it.hasNext(); i++)
                    {
                        try
                            {
                                cur = it.next();
                                copyObject (targetConnection,
                                    cur, false);
                            }
                        catch (CacheException x)
                            {
                                System.out.println
                                    ("Failed to copy object with id " +
                                     ((Persistent)cur).getId()) ;
                                //x.printFullTrace (System.out);
                            }
                        if (max > 0 && i >= max)
                            break;
                    }
            }
        else
            {
                CacheClass cacheClass = sourceConnection.getCacheClass (className);
                Object source = cacheClass.openObject (new Id(id));
                copyObject (targetConnection, source, true);
            }
    }

    private static void copyObject (Database targetDB,
                                    Object source, boolean verbose)
        throws CacheException
    {
        CacheClass sourceClass = ((Persistent) source).getCacheClass ();
        CacheClass targetClass = targetDB.getCacheClass (sourceClass.getName ());
        Object target = targetClass.newInstance ("");

        CacheField[] cFields = sourceClass.getFields ();

        for (int i = 0; i < cFields.length; i++)
            {
                CacheField cFld = cFields[i];
                if (cFld.isRelationship ())
                    continue;
                String fName = cFld.getName ();
                CacheField dFld = targetClass.getField (fName);
                Object value = cFld.get (source);
                if (value instanceof Persistent)
                    value = ((Persistent)value).getId ();
                if (verbose)
                    System.out.println ("Setting: " + fName + " = " + value);
                dFld.set (target, value);
            }

        ((Persistent)target).save();
        System.out.println ("Target saved with id = " +
                            ((Persistent)target).getId());

    }

}

/*
 * End-of-file
 *
 */

