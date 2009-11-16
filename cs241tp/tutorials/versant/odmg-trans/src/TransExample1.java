import model.*;
import java.util.*;
import com.versant.fund.Capability;
import com.versant.fund.NewSessionCapability;
import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;


public class TransExample1 {

  /**
   * the ctor
   */
  public TransExample1 () {
  }

  /**
   * The main function: arguments are the database names.
   */
  public static void main (String[] args) {

    Person[] p = new Person [5];
    Company[] cmp = new Company [6];

    if (args.length  < 2) {
      System.err.println ("Usage: java TransExample1 <dbname1> <dbname2>");
      System.exit (1);
    }

    System.out.println ("TransExample1");

    // create a transparent session with the given argument as dbname
    Capability cap = new NewSessionCapability ();
    Properties prop = new Properties ();
    prop.put ("database", args[0]);
    prop.put ("sessionName", "main session");
    TransSession transSession = new TransSession (prop, cap);

    p[0] = new Person ("Ron", "Richards", 36, (float)5.8);
    cmp[0] = new Company ("Homestead Hurling", "CA", 94187);
    p[0].setJob (cmp[0], 7000.0);

    // make Person object persistent
    transSession.makePersistent (p[0]);
    System.out.println ("Made persistent Person: " + p[0]);

    // commit
    transSession.commit ();
    System.out.println ("After commit (1)\n");

    // spawn a new thread ---
    TransThread1 secondThread = new TransThread1 (transSession, args[1],
						  args[0], cap);
    secondThread.start ();

    p[1] = new Person ("Bar", "Graf", 28, (float)6.2);
    p[2] = new Person ("Smart", "Alex", 44, (float)5.9, p[1]);
    p[3] = new Person ("George", "Burdell", 110, (float)7.8, p[2]);
    p[4] = new Person ("Dow", "Jones", 24, (float)5.11, p[3]);

    cmp[1] = new Company ("Read Headed League", "WA", 98052);
    cmp[2] = new Company ("Versant Music Co.", "CA", 94025);
    cmp[3] = new Company ("Yellow Jacket Inc.", "GA", 30332);
    cmp[4] = new Company ("Bears Inc.", "CA", 94709);
    cmp[5] = new Company ("Tibetian Fur Company", "NJ", 7422);

    //  set relationships
    p[1].setJob (cmp[0], 6000.0);
    p[2].setJob (cmp[1], 6000.0);
    p[3].setJob (cmp[2], 10000.0);
    p[4].setJob (cmp[3], 6446.5);

    // make Person object persistent ---
    transSession.makePersistent (p[4]);
    System.out.println ("Made persistent Person: " + p[0]);

    // synchronize by waiting for spawned thread to complete
    try {
      secondThread.join ();
    } catch (InterruptedException e) {
      System.err.println ("Exception in Thread join (): " + e);
      e.printStackTrace ();
      System.exit (1);
    }

    // commit
    transSession.commit ();
    System.out.println ("After commit (2)\n");

    VQLQuery vql;
    Enumeration resultEnum;

    // do a query to retrieve all Person objects
    try {
      vql = new VQLQuery (transSession, "select selfoid from model.Person");
      resultEnum = vql.execute();
      System.out.println ("After VQLQuery on Persons:");
      while (resultEnum.hasMoreElements()) {
	System.out.println ("Person: " + (Person) resultEnum.nextElement());
      }	
    }
    catch (Exception e) {
      System.err.println ("Exception in VQLQuery execute (): " + e);
      e.printStackTrace ();
      System.exit (1);
    }

    try {
      transSession.connectDatabase (args[1]);
    }  catch (Exception e) {
      System.err.println ("Exception in Session connectDb (): " + e);
      e.printStackTrace ();
      System.exit (1);
    }

    try {
      vql = new VQLQuery (transSession, args[1], "select selfoid from model.Person");
      resultEnum = vql.execute();
      System.out.println ("After VQLQuery on Persons:");
      while (resultEnum.hasMoreElements()) {
	System.out.println ("Person: " + (Person) resultEnum.nextElement());
      }	
    }
    catch (Exception e) {
      System.err.println ("Exception in VQLQuery execute (): " + e);
      e.printStackTrace ();
      System.exit (1);
    }

    // commit
    transSession.commit ();

    // Delete all the instances of Class Person that were created.
    // Delete all instances of class Company associated with Person instances
    // using a delete hook on class Person.
    for (int i = 0; i < 5; i++)
       transSession.deleteObject(p[i]);
    transSession.endSession ();
    System.out.println ("After commit and end session (3)");
  }
}
