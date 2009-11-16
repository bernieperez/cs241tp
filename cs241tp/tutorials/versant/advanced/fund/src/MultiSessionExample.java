
import com.versant.fund.*;
import java.util.Properties;
import java.util.Random;

/**
 * This example demonstrates the usage of multiple concurrent sessions.
 *
 * The main thread creates 5 FundSession instances, each with unique
 * session name.  It then creates 100 Student objects in the database,
 * and spawns 5 WorkerThreads to work on these Student objects.  
 * The main thread then waits for these threads to finish.  
 *
 * Each WorkerThread, upon starting, attaches to a session it was assigned
 * to.  Each WorkerThread has its own session.  It does a VQL query
 * using FundVQLQuery, finds the Student objects it has been assigned to
 * work on, then sums up the total GPA values of these Student objects.
 */

public class MultiSessionExample
{
    
    public static void main (String[] args)
    {
        if (args.length < 1) {
            System.out.println ("Usage: MultiSession <db>");
            System.exit (-1);
        }
       
        // database name is the first argument
        String db = args[0];

        // create an array of FundSession
        int threadCount = 5;
        FundSession[] sessionArray = new FundSession [threadCount];       
       
        // create the Capability and Property necessary to
        // instantiate a session object
        Capability capability= new NewSessionCapability ();
        Properties properties = new Properties ();
        properties.put ("database", db);       
        // set option for this thread not to join the session
        properties.put ("options", Constants.DONT_JOIN+"");
       
        // create FundSession objects
        System.out.println ("Creating " + threadCount + " sessions...");
        for (int i = 0; i < threadCount; i++) {
            // session name needs to be unique
            properties.put ("sessionName", "session "+i);     
            sessionArray[i] = new FundSession (properties, capability);
        }

        // create some Student objects in database
        int studentCount = 100;
        populate (sessionArray[0], studentCount);

        // spawn a number of worker threads
        System.out.println ("Creating " + threadCount + " threads...");
        Thread[] threadArray = new Thread[threadCount];
        int step = studentCount / threadCount;
        for (int i = 0; i < threadCount; i++) {
           threadArray[i] = new WorkerThread("thread " + i,
                                          sessionArray[i], 
	                                      step * i,
                                          step);
           System.out.println ("Assigning Students with id from " +
                               (step*i) + " to " + (step * i + step) +
                               " to thread " + i); 
           threadArray[i].start();
        }

        // wait for these threads to finish, collect their results
        float total = 0.0f;
        for (int i = 0; i < threadCount; i++) {
            try { 
                 threadArray[i].join();
                 total = total + ((WorkerThread)threadArray[i]).getResult();
                 System.out.println ("Total gpa's returned from " +
                            threadArray[i].getName() + " is: " +
                            ((WorkerThread)threadArray[i]).getResult());
            }
            catch (InterruptedException e) {
                System.err.println ("Exception in Thread.join (): " + e);
                e.printStackTrace ();
                System.exit (1);
            }            
        } //for       

        System.out.println ("Average of all " + studentCount +" students is: " +
                            total/studentCount);
    } //main

    /** 
     * helper method for main, to create specified number of Student 
     * objects in database
     */
    private static void populate (FundSession sess, int count)
    {
        // attach to the session
        sess.setSession ();

        // drop existing Student class
        try {
            sess.locateClass ("Student").dropClass();
            System.out.println ("Dropping existing Student class from database");
        }
        catch (VException vex) {
            if (vex.getErrno() != 6002) //class undefined
                throw vex;
        }

        // define Student class
        AttrInt    id   = sess.newAttrInt   ("id");
        AttrFloat  gpa  = sess.newAttrFloat ("gpa");
        AttrBuilder attrs[] = { sess.newAttrBuilder (id),
                                sess.newAttrBuilder (gpa) };
        ClassHandle cls
            = sess.withAttrBuilders (attrs).defineClass ("Student");
        System.out.println ("Defining Student class");

        // populate
        Random random = new Random ();
        for (int i = 0; i < count; i++) {
            Handle h = cls.makeObject ();
            h.put (id, i);
            float randomGpa = Math.abs (random.nextFloat()*10000%4);
            h.put (gpa, randomGpa);
        }

        // commit the transaction and detach from the session
        System.out.println ("Creating Student instances");
        sess.commit ();
        System.out.println ("Detaching from the session");
        sess.leaveSession ();
        
    } //populate
        
} //class MultiSessionExample


/**
 * This class extends thread.  It joins a session, carries out a VQL query
 * to find Student objects that it needs to work on, then calculate the
 * total gpa values of these students, and stores the totol in one of
 * its instance variable.
 */
class WorkerThread extends Thread
{
    private float       result;
    private FundSession sess;
    private int         start;
    private int         step;

    public WorkerThread (String name, FundSession s, int i, int j)
    {
        super (name);
        result = 0.0f;
        sess = s;
        start = i;
        step = j;
    }

    public float getResult ()
    {
        return result;
    }

    public void run ()
    {
        // join the session
        sess.setSession (); 

        // query all Student objects whose id's are in the range of
        // [start, start+step), put them in an enumeration
        String vqlString = "select selfoid from Student where " + 
	                   "id >= $1 and id < $2";
        FundVQLQuery vql = new FundVQLQuery (sess, vqlString);
        vql.bind (new Integer(start));
        vql.bind (new Integer (start + step));
        HandleEnumeration enumeration = vql.execute ();

        // calculate the total gpa of all these students
        AttrFloat gpa = sess.newAttrFloat ("gpa");
        result = 0.0f;
        while (enumeration.hasMoreHandles ()) {
            Handle h = enumeration.nextHandle();
            result = result + h.get (gpa);
        }

        // end the database session
        sess.endSession();

    } //run

} //class WorkerThread        

