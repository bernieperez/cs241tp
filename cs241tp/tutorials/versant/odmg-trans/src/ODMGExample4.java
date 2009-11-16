import model.*;
import java.util.*;
import com.versant.odmg.*;
import com.versant.trans.VQLQuery;


public class ODMGExample4 {

  /**
   * the ctor
   */
  public ODMGExample4 () {
  }

  /**
   * The main function: arguments are the database names.
   */
  public static void main (String[] args) {

    Person[] p = new Person [5];
    Company[] cmp = new Company [6];

    if (args.length  < 2) {
      System.err.println ("Usage: java ODMGExample4 <dbname1> <dbname2>");
      System.exit (1);
    }

    System.out.println ("ODMGExample4");

    // create a database with the given argument as name
    Database db = null;
    try {
      db = Database.open (args[0], Database.openReadWrite);
      System.out.println ("Opened database " + args[0]);
    } catch (ODMGException e) {
      System.err.println ("Exception in Database open (): " + e);
      e.printStackTrace ();
      System.exit (1);
    }

    // create a transaction
    Transaction txact = new Transaction ();

    // begin the transaction
    txact.begin ();
    System.out.println ("\nBegan transaction (1)");

    // create one person, company and create a relationship
    p[0] = new Person ("Ron", "Richards", 36, (float)5.8);
    cmp[0] = new Company ("Homestead Hurling", "CA", 94187);
    p[0].setJob (cmp[0], 7000.0);

    // make Person object persistent
    txact.session ().makePersistent (p[0]);
    System.out.println ("Made persistent Person: " + p[0]);

    // commit the transaction
    txact.commit ();
    System.out.println ("After commit of transaction (1)");

    // begin the transaction
    txact.begin ();
    System.out.println ("\nBegan transaction (2)");

    // spawn a new thread
    ODMGThread4 secondThread = new ODMGThread4 (txact, args[1], db);
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

    // set relationships
    p[1].setJob (cmp[1], 6000.0);
    p[2].setJob (cmp[2], 6000.0);
    p[3].setJob (cmp[3], 10000.0);
    p[4].setJob (cmp[4], 6446.5);

    // make Person object persistent ---
    txact.session ().makePersistent (p[4]);
    System.out.println ("Made persistent Person: " + p[0]);

    // synchronize by waiting for spawned thread to complete
    try {
      secondThread.join ();
    } catch (InterruptedException e) {
      System.err.println ("Exception in Thread join (): " + e);
      e.printStackTrace ();
      System.exit (1);
    }

    // commit the transaction
    txact.commit ();
    System.out.println ("After transaction commit (2)");

    // begin a new transaction
    txact.begin ();
    System.out.println ("\nBegan transaction (3)");

    VQLQuery vql;
    Enumeration resultEnum;

    // do a query to retrieve all Person objects
    try {
      vql = new VQLQuery (txact.session (),
			  "select selfoid from Person");
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
      txact.session ().connectDatabase (secondThread.threadDb);
    }  catch (ODMGException e) {
      System.err.println ("Exception in Session connectDatabase (): " + e);
      e.printStackTrace ();
      System.exit (1);
    }

    try {
      vql = new VQLQuery (txact.session (), args[1],
			  "select selfoid from Person");
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

    // commit the transaction
    txact.commit ();
    System.out.println ("After transaction commit (3)");

    // close the databases
    try {
      db.close ();
      System.out.println ("\nClosed database " + args[0]);
      secondThread.threadDb.close ();
      System.out.println ("Closed database " + args[1]);
    } catch (ODMGException e) {
      System.err.println ("Exception in Database close (): " + e);
      e.printStackTrace ();
      System.exit (1);
    }
  }
}
