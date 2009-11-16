import model.*;
import com.versant.odmg.*;


public class ODMGExample2 {

  /**
   * the ctor
   */
  public ODMGExample2 () {
  }

  /**
   * The main function: first argument is the database name.
   */
  public static void main (String[] args) {

    Person[] p = new Person [5];
    Company[] cmp = new Company [6];

    if (args.length  < 1) {
      System.err.println ("Usage: java ODMGExample2 <dbname>");
      System.exit (1);
    }

    System.out.println ("ODMGExample2");

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

    // create one person, company and create a relationship
    p[0] = new Person ("Ron", "Richards", 36, (float)5.8);
    cmp[0] = new Company ("Homestead Hurling", "CA", 94187);
    p[0].setJob (cmp[0], 7000.0);

    // define database root
    db.bind (p[0], "Root-Person-Main1");
    System.out.println ("Binding to \"Root-Person-Main1\" Person: " + p[0]);

    // commit the transaction
    txact.commit ();
    System.out.println ("After commit of transaction (1)");

    // begin transaction
    txact.begin ();
    System.out.println ("\nBegan transaction (2)");

    // spawn a new thread
    ODMGThread2 secondThread = new ODMGThread2 (txact, db);
    secondThread.start ();

    p[1] = new Person ("Bar", "Graf", 28, (float)6.2, p[0]);
    p[2] = new Person ("Smart", "Alex", 44, (float)5.9, p[1]);
    p[3] = new Person ("George", "Burdell", 110, (float)7.8, p[2]);
    p[4] = new Person ("Dow", "Jones", 24, (float)5.11, p[3]);

    cmp[0] = new Company ("Read Headed League", "WA", 98052);
    cmp[1] = new Company ("Versant Music Co.", "CA", 94025);
    cmp[2] = new Company ("Yellow Jacket Inc.", "GA", 30332);
    cmp[3] = new Company ("Bears Inc.", "CA", 94709);
    cmp[4] = new Company ("Tibetian Fur Company", "NJ", 7422);

    // set relationships
    p[1].setJob (cmp[1], 6000.0);
    p[2].setJob (cmp[2], 6000.0);
    p[3].setJob (cmp[3], 10000.0);
    p[4].setJob (cmp[4], 6446.5);

    // define database root
    db.bind (p[4], "Root-Person-Main2");
    System.out.println ("Binding to \"Root-Person-Main2\" Person: " + p[4]);

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

    Person[] retrieveP = new Person [4];

    // retrieve roots
    try {
      retrieveP[0] = (Person) db.lookup ("Root-Person-Main1");
      System.out.println ("Retrieved \"Root-Person-Main1\": " + retrieveP[0]);
      retrieveP[1] = (Person) db.lookup ("Root-Person-Main2");
      System.out.println ("Retrieved \"Root-Person-Main2\": " + retrieveP[1]);
      retrieveP[2] = (Person) db.lookup ("Root-Person-Th1");
      System.out.println ("Retrieved \"Root-Person-Th1\": " + retrieveP[2]);
      retrieveP[3] = (Person) db.lookup ("Root-Person-Th2");
      System.out.println ("Retrieved \"Root-Person-Th2\": " + retrieveP[3]);
    }  catch (ODMGException e) {
      System.err.println ("Exception in Database lookup (): " + e);
      e.printStackTrace ();
      System.exit (1);
    }

    // commit the transaction
    txact.commit ();
    System.out.println ("After transaction commit (3)");

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
