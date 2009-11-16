import model.*;
import com.versant.trans.*;
import com.versant.fund.Capability;
import com.versant.fund.NewSessionCapability;
import com.versant.fund.Constants;
import com.versant.fund.VException;
import com.versant.fund.Handle;
import com.versant.fund.HandleVector;
import java.util.Properties;
import java.util.Random;

/**
 * Employee class for optimistic locking example, OLExample.
 */
class Employee {
    int        o_ts_timestamp;
    String     name;
    Department department;
    int        number;
    float      salary;
    String     job_description;

    public Employee (String nme, Department dept, int num, float sal, String desc) 
    {
        name = nme;
        department = dept;
        number = num;
        salary = sal;
        job_description = desc;
    }
    public Department get_department ()
    {
        return department;
    }
    public int get_number ()
    {
        return number;
    } 
    public float get_salary () 
    {
        return salary;
    }
    public String get_job_description ()
    {
        return job_description;
    }
    public void set_department (Department d) 
    {
        department = d;
    }
    public void set_number (int n)
    {
        number = n;
    }
    public void set_salary (float s)
    {
        salary = s;
    }
    public void set_job_description (String j)
    {
        job_description = j;
    }
} // Employee class


/**
 * Example to demonstrate usage of optimistic locking in transparent
 * layer.
 */
public class OLExample
{
    // class and instance variables
    static NewSessionCapability capability;
    protected String sessionDb;

    /**
     * default constructor
     */
    public OLExample () {}
 

    /**
     * The main function: argument is the database name.
     */
    public static void main (String args[])
    {
        if (args.length < 1) {
            System.out.println ("Usage: OLExample <db>");
            System.exit (-1);
        }

        // start a standard session in the main thread
        String sessionDb = args[0];
        Properties prop = new Properties();
        prop.put ("database", sessionDb);
        prop.put ("sessionName", "main session");
        capability = new NewSessionCapability();
        TransSession session = new TransSession (prop, capability);     
        System.out.println ("Main thread: created a standard session");

        // create instances of Department and Employee
        Department rd = new Department ("RD");
        Employee john = new Employee 
                        ("John Brown", rd, 100022, 35000.00f, "Engineer");
        Employee lisa = new Employee 
                        ("Lias Dilon", rd, 100023, 45000.00f, "Test Pilot");
        Employee james = new Employee
                         ("James Lee",  rd, 100024, 61500.00f, "Supervisor");
        Employee rudolf = new Employee
                          ("Rudolf Rednose", rd, 100025, 21000.00f, "Nothing");
        session.makePersistent (john);
        session.makePersistent (lisa);
        session.makePersistent (james);

        // note that the Department object is made persistent by reachability 
        session.commit();
        System.out.println 
          ("Main thread: finished creating instances of Department and Employee");
        
        // spawn off two threads
        OL1 ol1 = new OL1(sessionDb);
        Thread thread1 = new Thread (ol1, "OLThread1");
       
        OL2 ol2 = new OL2(sessionDb);
        Thread thread2 = new Thread (ol2, "OLThread2");

        thread2.start();
        thread1.start();

        // wait for the two threads to finish, then end the session
        try {
           thread1.join ();
           thread2.join ();
        } 
        catch (InterruptedException e) {
            System.err.println ("Exception in Thread.join (): " + e);
            e.printStackTrace ();
            System.exit (1);
        }

        session.endSession();

    } //main


    /** select and return all Employee objects */
    public Object[] selectAll (TransSession session)
    {
        // find all employees, group read them into Object cache, 
        // then release locks
        VQLQuery vql = new VQLQuery (session, "select selfoid from Employee"); 
        Object[] staff = vql.execute().nextBatch(100);
        session.groupReadObjects (staff, sessionDb, Constants.PIN_OBJECTS, 
                                  Constants.RLOCK);
        session.downgradeLocksTo (staff, Constants.NOLOCK);

        System.out.println (Thread.currentThread().getName() +  ": " +
                            "Finished selecting all Employee objects," + 
                            " group read, and downgrade lock");        
        return staff; 
     } //selectAll


     /** delete an employee */
     public void delete (Employee emp, TransSession session)
     {   
        int empNumber = emp.get_number();

        try {
            session.deleteObject (emp);
            session.commit();

            System.out.println (Thread.currentThread().getName() + ": " +
                                "Finished deleting " + empNumber);
        }
        catch (VException vex) {
            System.out.println (Thread.currentThread().getName() + ": " +
                                "deleting "+empNumber +
                                " encountered "+vex.getErrno());
            Object[] toRollback = {emp};
            session.rollbackAndRetain(toRollback);
            System.out.println (Thread.currentThread().getName() + ": " +
                                "Finished rollback of deletion of " + empNumber);
        }

     } //delete  


     /** modify each employee in the array */
     public void modify (Object[] staff, TransSession session)
     {   
        // set each employee's salary to 50000
        String ids = new String();
        for (int i = 0; i < staff.length; i ++) {
             Employee worker = (Employee)staff[i];
             worker.set_salary (50000.00f);
             ids = ids.concat (worker.get_number() + " ");
        }

        try {
            session.checkTimestamps (staff);
            session.commit();
            System.out.println (Thread.currentThread().getName() + 
                            ": " + "Finished modifying " + ids);
        }
        catch (VException vex) {
            System.out.print (Thread.currentThread().getName() + ": " +
                              "checkTimestamps() encountered ");
            int err = vex.getErrno();
            switch (err) {
                case 5006:
                    System.out.print ("<Error 5006>, OB_NO_SUCH_OBJECT.");
                    System.out.println 
                      ("Some objects have been deleted from the database.");
                    break;
                case 1009:
                    System.out.print ("<Error 1009>, SM_KEY_NOT_FOUND.");
                    System.out.println 
                      ("Some objects have been deleted from the database.");
                    break;
                case 4036:
                    System.out.print ("<Error 4036>, OM_TR_INVALID_TS.");
                    System.out.println 
                      ("Some objects have been updated in the database.");
                    break;
                case 2903:
                    System.out.print ("<Error 2903>, SM_LOCK_TIMEOUT.");
                    System.out.println ("Lock wait time out.");
                    break;
                default:
                    System.out.println ("Error: " + err);
                    break;
             } //switch

             Object[][] failedArray = processFailedObjects (vex.getFailedObjects());
             Object[] outdatedArray = failedArray[0];
             Object[] deletedArray = failedArray[1];        

             int i;

             // rollback objects that have already been deleted
             if (deletedArray.length != 0) {
                 session.rollbackAndRetain (deletedArray);

                 System.out.println (Thread.currentThread().getName() + ": " +
                                     "Finished rollback of deleted objects" );
             } //if (deletedArray != null)

             // refresh objects that failed timestamp check, re-apply changes 
             if (outdatedArray.length != 0) {
                 
                 // refresh from database
                 session.refreshObjects (outdatedArray, sessionDb, Constants.RLOCK);

                 // modify and commit again, should succeed since we hold RLOCK
                 ids = new String();
                 for (i=0; i<outdatedArray.length; i++) {
                     ((Employee)outdatedArray[i]).set_salary(50000.00f);
                     ids.concat (((Employee)outdatedArray[i]).get_number() + " ");
                 }
                 session.commit();
                 System.out.println (Thread.currentThread().getName() + ": " + 
                                     "Finished committing for the 2nd time out-of-date objects:" + ids);
              } //if (outdatedArray != null)
         } //catch

     } //delete                


     /** helper method used by modify() */
     Object[][] processFailedObjects (Object[] failedObjects)
     {
         if (failedObjects == null || failedObjects.length == 0)
             return new Object[2][0];

         int i = 0;
         // count number of objects that failed timestamp check
         for (i = 0; i < failedObjects.length; i++)
             if (failedObjects[i] == null) 
                 break;

         Object[][] out = new Object[2][];
         // objects that failed timestamp check
         out[0] = new Object [i]; 
         // objects that have been deleted
         out[1] = new Object [failedObjects.length - i - 2];

         // out-of-date objects come first
         int position = 0;
         Object obj = failedObjects[0];
         while (obj != null && position < failedObjects.length) {
             out[0][position] = obj;
             obj = failedObjects [++ position];
         }

         // followed by a null
         obj = failedObjects [++ position];

         // followed by deleted objects
         int index = 0;
         while (obj != null && position < failedObjects.length) {
             out[1][index ++] = obj;  
             obj = failedObjects [++ position];
         }

         return out;
     } //processFailedObjects

} //class OLExample


class OL1 extends OLExample implements Runnable
{
    public OL1 (String db)
    {
         sessionDb = db;
    }

    /** modify all employee objects */
    public void run ()
    {
        // start an optimistic locking session using session option OPT_LK
        Properties prop = new Properties();
        prop.put ("database", sessionDb);
        prop.put ("sessionName", "OL session 1");
        prop.put ("options", Constants.OPT_LK+"");
        TransSession session = new TransSession (prop, capability);
        System.out.println (Thread.currentThread().getName() + ": " +
                            "Created an optimistic locking session (OL session 1)");

        // modify all employees
        Object[] staff = selectAll (session);
        modify (staff, session);

    } //run

} // class OL1


class OL2 extends OLExample implements Runnable
{
    public OL2 (String db)
    {
         sessionDb = db;
    }

    /** randomly delete one employee */
    public void run() 
    {
        // start an optimistic locking session using session option OPT_LK
        Properties prop = new Properties();
        prop.put ("database", sessionDb);
        prop.put ("sessionName", "OL session 2");
        prop.put ("options", Constants.OPT_LK+"");
        TransSession session = new TransSession (prop, capability);
        System.out.println (Thread.currentThread().getName() + ": " +
                            "Created an optimistic locking session (OL session 2)");

        Object[] staff = selectAll (session);

        int random = java.lang.Math.abs(new Random().nextInt()) % (staff.length-1);

        // modify one employee
        Employee toDelete = (Employee) staff[random];
        delete (toDelete, session);
    } //run

} //class-OL2


