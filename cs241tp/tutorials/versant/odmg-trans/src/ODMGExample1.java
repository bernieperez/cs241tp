import model.*;
import com.versant.odmg.*;


public class ODMGExample1 {

  /**
   * the ctor
   */
  public ODMGExample1 () {
  }

  /**
   * The main function: first argument is the database name.
   */
  public static void main (String[] args) {

    if (args.length  < 1) {
      System.err.println ("Usage: java ODMGExample1 <dbname>");
      System.exit (1);
    }

    System.out.println ("ODMGExample1");

    // open a database with the given argument as name
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

    // create various objects
    System.out.println("Creating persistent objects");

    Person[] p = new Person [4];
    Company[] cmp = new Company [5];

    p[0] = new Person ("Bar", "Graf", 28, (float)6.2);
    p[1] = new Person ("Smart", "Alex", 44, (float)5.9, p[0]);
    p[2] = new Person ("George", "Burdell", 110, (float)7.8, p[1]);
    p[3] = new Person ("Dow", "Jones", 24, (float)5.11, p[2]);

    cmp[0] = new Company ("Read Headed League", "WA", 98052);
    cmp[1] = new Company ("Versant Music Co.", "CA", 94025);
    cmp[2] = new Company ("Yellow Jacket Inc.", "GA", 30332);
    cmp[3] = new Company ("Bears Inc.", "CA", 94709);
    cmp[4] = new Company ("Tibetian Fur Company", "NJ", 7422);

    // set relationships
    p[0].setJob (cmp[0], 6000.0);
    p[1].setJob (cmp[1], 6000.0);
    p[2].setJob (cmp[2], 10000.0);
    p[3].setJob (cmp[3], 6446.5);

    // verify that objects are set correctly
    System.out.println ("Last Person Created: " + p[3]);

    // define database root
    db.bind (p[3], "Root-Person");
    System.out.println ("Binding to \"Root-Person\" Person: " + p[3]);

    // commit the transaction
    txact.commit();
    System.out.println ("After commit of transaction (1)");

    // begin a new transaction
    txact.begin ();
    System.out.println ("\nBegan transaction (2)");

    // retrieve the database root
    try {
      Person retrieveP = (Person) db.lookup ("Root-Person");
      System.out.println ("Retrieved \"Root-Person\" Person: " + retrieveP);
    }  catch (ODMGException e) {
      System.err.println ("Exception in Database lookup (): " + e);
      e.printStackTrace ();
      System.exit (1);
    }

    // commit the transaction
    txact.commit ();
    System.out.println ("After commit of transaction (2)");

    // close the database
    try {
      db.close ();
      System.out.println ("\nClosed database " + args[0]);
    } catch (ODMGException e) {
      System.err.println ("Exception in Database close (): " + e);
      e.printStackTrace ();
      System.exit (1);
    }
  }
}
