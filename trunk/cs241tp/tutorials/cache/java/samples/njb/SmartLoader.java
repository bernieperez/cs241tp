package njb;

import com.intersys.classes.Persistent;
import com.intersys.classes.ObjectHandle;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.Id;
import com.intersys.objects.CandidateKey;
import com.intersys.objects.Oid;
import com.intersys.objects.reflect.CacheClass;
import com.intersys.objects.reflect.CacheField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


/**
 * This is a sample console program to copy data from Cache Database to a thrid
 * party database. Before running this program you must run Exporter program
 * to export database schema definition.
 *
 * <p>
 *
 * This program uses the following algorithm to copy referenced data:
 *
 * <ul>
 * <li> For each non-NULL reference the key (primary or other) of the
 * referenced object is obtained from source database.
 * <li> An object with the given key is looked up in the target database.
 * <li> If lookup is successful, the id of the found object is associated with
 * the object being copied.
 * <li> If lookup failed then the reference object is copied to the target
 * database and its newly assigned ID is used.
 * <li> The procedure is repeated recursively if necessary.
 * <li> Circular dependencies <b> are not handled</b>. 
 * </ul>
 *
 */
public class SmartLoader {
    private Map inMemory;

    public SmartLoader ()
    {
        inMemory = new HashMap ();
    }

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
        targetConnection = CacheDatabase.getLightDatabase (connection);

        sourceConnection = CacheDatabase.getLightDatabase (url, username, password);


        if (id.equalsIgnoreCase ("all"))
            {
                query = "ID > 0";
            }

        SmartLoader smartLoader = new SmartLoader ();

        if (query != null)
            {
                Iterator it = sourceConnection.openByQuery (className, query);
                Object cur = null;
                for (int i = 0; it.hasNext(); i++)
                    {
                        try
                            {
                                cur = it.next();
                                smartLoader.copyObject (targetConnection,
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
                smartLoader.copyObject (targetConnection, source, true);
            }
    }

    private void copyObject (Database targetDB,
                                    Object source, boolean verbose)
        throws CacheException
    {
        createTarget (targetDB, source, verbose);
        inMemory.clear ();
    }

    private Persistent createTarget (Database targetDB,
                                     Object source, boolean verbose)
        throws CacheException
    {
        CacheClass sourceClass = ((Persistent) source).getCacheClass ();
        CacheClass targetClass = targetDB.getCacheClass (sourceClass.getName ());
        Persistent target = (Persistent) targetClass.newInstance ("");
        inMemory.put (getKey ((Persistent) source), target);

        CacheField[] cFields = sourceClass.getFields ();

        for (int i = 0; i < cFields.length; i++)
            {
                CacheField cFld = cFields[i];
                if (refersToPersistent (cFld))
                    continue;

                String fName = cFld.getName ();
                CacheField dFld = targetClass.getField (fName);
                Object value = cFld.get (source);
                if (verbose)
                    System.out.println ("Setting: " + fName + " = " + value);
                dFld.set (target, value);
            }

        for (int i = 0; i < cFields.length; i++)
            {
                CacheField cFld = cFields[i];
                if (!refersToPersistent (cFld))
                    continue;

                String fName = cFld.getName ();
                CacheField dFld = targetClass.getField (fName);
                Object value = cFld.get (source);
                if (verbose)
                    System.out.println ("Setting: " + fName + " = " + value);

                if (value instanceof Persistent)
                    setPersistent (dFld, target, (Persistent) value, targetDB, verbose);
                else if(cFld.isCollection () && cFld.isElementPersistent ())
                    setPersistentCollection (dFld, target, value, targetDB, verbose);
                else
                    dFld.set (target, value);
            }

        target.save();
        System.out.println ("Target saved with id = " + target.getId());

        return target;
    }

    private boolean refersToPersistent (CacheField fld)
        throws CacheException
    {
        if (fld.isPersistent ())
            return true;

        if(fld.isCollection () && fld.isElementPersistent ())
            return true;

        return false;
    }

    private void setPersistentCollection (CacheField dFld,
                                                 Object target,
                                                 Object sourceObj,
                                                 Database targetDB,
                                                 boolean verbose)
        throws CacheException
    {
        if (sourceObj instanceof List)
            {
                List l = new ArrayList (((List)sourceObj).size());
                Iterator it = ((List)sourceObj).iterator ();
                while (it.hasNext ())
                    {
                        Persistent p = (Persistent) it.next ();
                        CacheClass targetClass = targetClassFor (p, targetDB);
                        Persistent destObj = findPersistent (p, targetDB,
                            targetClass, verbose);
                        l.add(destObj);
                    }
                dFld.set (target, l);
            }
        else if (sourceObj instanceof Map)
            {
                Map m = new HashMap (((Map)sourceObj).size());
                Iterator it = ((Map)sourceObj).entrySet().iterator ();
                while (it.hasNext ())
                    {
                        Map.Entry e = (Map.Entry) it.next ();
                        Persistent p = (Persistent) e.getValue ();
                        CacheClass targetClass = targetClassFor (p, targetDB);
                        Persistent destObj = findPersistent (p, targetDB,
                            targetClass, verbose);
                        m.put(e.getKey (), destObj);
                    }
                if (dFld.isRelationship ())
                    {
                        Map targetRel = (Map) dFld.get(target);
                        targetRel.clear ();
                        targetRel.putAll (m);
                    }
                else
                    dFld.set (target, m);
            }
    }

    private void setPersistent (CacheField destinationField,
                                       Object target, Persistent sourceObj,
                                       Database targetDB, boolean verbose)
        throws CacheException
    {
        CacheClass targetClass = targetClassFor (sourceObj, targetDB);
        Persistent destObj = findPersistent (sourceObj, targetDB, targetClass,
            verbose);
        destinationField.set (target, destObj);
    }

    private Persistent findPersistent (Persistent sourceObj,
                                   Database targetDB,
                                   CacheClass targetClass, boolean verbose)
        throws CacheException
    {
        Object key = getKey (sourceObj);
        Persistent destObj = (Persistent) inMemory.get (key);
        if (destObj != null)
            return destObj;

        try
            {
                if (key instanceof Oid)
                    {
                        if (verbose)
                            {
                                System.out.println ("No candidate keys available," +
                                                    "using ID");
                            }
                       destObj = (Persistent)targetClass.openObject ((Oid) key);
                    }
                else
                    {
                        destObj = (Persistent)
                            targetClass.openByKey ((CandidateKey) key);
                    }

                if (verbose)
                    {
                        System.out.println ("Mapping " +
                                            sourceObj.getCacheClass().getName() +
                                            " with id " + sourceObj.getId() +
                                            " to " +
                                            destObj.getCacheClass().getName() +
                                            " with id " + destObj.getId());
                    }
            }
        catch (CacheException x)
            {
                if (x.getCode () == 5809)
                    {
                        System.out.println ("Creating target for instance of " +
                                            sourceObj.getCacheClass().getName () +
                                            " with id " + sourceObj.getId());

                        destObj = createTarget (targetDB, sourceObj, verbose);
                    }
                else
                    throw x;
            }

        return destObj;
    }

    private Object getKey (Persistent p)
        throws CacheException
    {
        Object key = p.getBestCandidateKey ();
        if (key == null)
            key = p.getOid ();

        return key;
    }

    private CacheClass targetClassFor (ObjectHandle source, Database targetDB)
        throws CacheException
    {
        CacheClass sourceClass = ((Persistent) source).getCacheClass ();
        CacheClass targetClass = targetDB.getCacheClass (sourceClass.getName ());

        return targetClass;
    }
}

/*
 * End-of-file
 *
 */

