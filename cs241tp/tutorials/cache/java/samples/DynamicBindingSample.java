
/**
 * ReflectionTest.java
 *
 *
 * Created: Mon Jun 25 20:31:57 2001
 *
 * @author <a href="mailto: "Michael A Bouzinier</a>
 * @version
 */

import com.intersys.objects.*;
import com.intersys.objects.reflect.*;
import com.intersys.classes.RegisteredObject;
import java.io.*;

public class DynamicBindingSample
{
    static CacheClass StringClass;
    static CacheClass IntegerClass;
    static CacheClass StatusClass;
    static CacheClass BooleanClass;

    public  static void setUp (Database db)
        throws CacheException
    {
        if (StringClass == null)
            StringClass = db.getCacheClass("%Library.String");
        if (IntegerClass == null)
            IntegerClass = db.getCacheClass("%Library.Integer");
        if (StatusClass == null)
            StatusClass = db.getCacheClass("%Library.Status");
        if (BooleanClass == null)
            BooleanClass = db.getCacheClass("%Library.Boolean");
    }

    public static void main (String[] argv)
        throws Exception
    {
        String host = "localhost";
        String nspace = "SAMPLES";
        String url = "jdbc:Cache://" + host + ":1972/" + nspace;
        System.out.println(url);

        Database db = CacheDatabase.getDatabase (url, "_SYSTEM", "SYS");

        System.out.println("Connected.");
        setUp (db);
        
        CacheClass personClass = db.getCacheClass ("Sample.Person");

        // Call simple class method with no objects
        System.out.println ("Call simple class method with no objects");
        
        CacheClass[] clss = {StringClass, StringClass};
        Object args[] = {"Aleks", null};

        CacheMethod m = personClass.getMethod ("StoredProcTest", clss);

        System.out.println ("Calling " + m.getName() + "(" + 
                            args[0] + ", " + args[1] + ")");

        Object ret = m.invoke (null, args);

        System.out.println ("Returned: " + ret + ": " + "(" + 
                            args[0] + ", " + args[1] + ")");

        System.out.println();
        // Open an object and call a method on it
        System.out.println ("Open an object and call a method on it:");
        System.out.println();

        Object p = personClass.openObject (new Id(111));
        System.out.println ("Opend person with name " + 
                            personClass.getField("Name").get(p));
        
        clss = new CacheClass[0];
        m = personClass.getMethod ("NinetyNine", clss);
        System.out.println ("Calling " + m.getName() + "()");
        ret = m.invoke ((RegisteredObject)p, null);
        System.out.println ("Returned: " + ret);

        // Call a method that prints to console
        System.out.println();
        System.out.println("Call a method that prints to console:");
        System.out.println();
        OutputStream console = new ByteArrayOutputStream();
        db.setConsoleOutput (new PrintStream (console, true));
        db.addListener (new OutputHandler());

        clss = new CacheClass[0];
        m = personClass.getMethod ("PrintPerson", clss);
        System.out.println ("Calling " + m.getName() + "()");
        args = new Object[0];
        ret = m.invoke ((RegisteredObject)p, args);
        System.out.println ("Returned: " + ret);
        System.out.println ("Here is what we got on console: " + 
                            console.toString());
     
        // Now we will open the same object but using a method call
        System.out.println();
        System.out.println ("Now we will open the same object but using a method call");
        System.out.println();
        CacheClass[] clss2 = {StringClass,IntegerClass,StatusClass};
        Object[] args2 = {"111", new Integer(-1), null};

        m = personClass.getMethod ("%OpenId", clss2);

        System.out.println ("Calling " + m.getName() + "(" + 
                            args2[0] + ", " + args2[1] + ", " + args2[2] + ")");

        ret = m.invoke (null, args2);

        System.out.println ("Returned: " + ret + ": " + "(" + 
                            args2[0] + ", " + args2[1] + ", \"" + args2[2] + "\")");

        System.out.println ("We received an object of class: " + 
                            ret.getClass().getName());

        System.out.println ("The name of the guy is: " + 
                            personClass.getField("Name").get(ret));
        
        System.out.println ("Let us see if he is the same guy: " +
                            p.equals (ret));

        // And now we will try to open a non-exsiting person
        System.out.println();
        System.out.println ("And now we will try to open a non-exsiting person");
        System.out.println();

        args2[0] = "-23";
        args2[2] = null;
        //args2[2] = null;
        
        System.out.println ("Calling " + m.getName() + "(" + 
                            args2[0] + ", " + args2[1] + ", \"" + args2[2] + "\")");

        ret = m.invoke (null, args2);

        System.out.println ("Returned: " + ret + ": " + "(" + 
                            args2[0] + ", " + args2[1] + ", \"" + args2[2] + "\")");


        // Now what will happen if we do the same but pass non-null status code
        // as an argument:
        System.out.println();
        System.out.println ("Now what will happen if we do the same but pass non-null status code as an argument:");
        System.out.println();

        args2[0] = "-23";
        args2[2] = StatusCode.OK;
        
        System.out.println ("Calling " + m.getName() + "(" + 
                            args2[0] + ", " + args2[1] + ", \"" + args2[2] + "\")");

        ret = m.invoke (null, args2);

        System.out.println ("Returned: " + ret + ": " + "(" + 
                            args2[0] + ", " + args2[1] + ", \"" + args2[2] + "\")");

        System.out.println ("Hm. This is a bit ugly. But we wanted to use row status code. Let's deal with it:");

        StatusCode sc = (StatusCode)args2[2];
        try
            {
                db.parseStatus (sc);
            }
        catch (Exception x)
            {
                System.out.println (x.getMessage());
            }
    }

    private static class OutputHandler implements MessageListener
    {
        public void messageReceived (String msg)
        {
            System.out.println ("We got it: " + msg);
        }
    }
    
}// ReflectionTest
