import model.*;
import com.versant.odmg.*;


public class ODMGThread3
extends Thread {

  Transaction[] threadTxact; // the txacts created in this thread
  Transaction mainTxact;     // the txact created in the main program
  String      threadDbName;  // name of the database opened in this thread
  Database    threadDb;      // the database opened in this thread
  Database    mainDb;        // the database opened in the main program

  /**
   * the ctor
   */
  public ODMGThread3 (Transaction handed, String aDbName, Database aDb) {
    mainTxact = handed;
    threadDbName = aDbName;
    mainDb = aDb;
  }


  public synchronized void run () {

    threadTxact = new Transaction[2];

    Person[] threadP = new Person [3];
    Company[] threadC = new Company [3];

    // open thread database
    try {
      threadDb = Database.open (threadDbName, Database.openReadWrite);
      System.out.println ("\tTHREAD: Opened database " + threadDbName);
    } catch (ODMGException e) {
      System.err.println ("\tException in Database open (): " + e);
      e.printStackTrace ();
      return;
    }

    threadTxact[0] = new Transaction ();

    threadP[0] = new Person ("Udai", "Hussein", 35, (float)6.1);
    threadC[0] = new Company ("Baghdad Botany", "PA", 16802);
    threadP[0].setJob (threadC[0], 28000.0);

    // begin the transaction
    threadTxact[0].begin ();
    System.out.println ("\tTHREAD: Began transaction (1)");

    // define a database root
    threadDb.bind (threadP[0], "Root-Person-Th1");
    System.out.println ("\tTHREAD: Binding to \"Root-Person-Main1\" Person: " +
			threadP[0]);

    // commit the transaction
    threadTxact[0].commit ();
    System.out.println ("\tTHREAD: After commit of transaction (1)");
    // end of first transaction

    // create a new transaction on the main database
    threadTxact[1] = new Transaction (mainDb);

    threadP[1] = new Person ("John", "Barleycorn", 49, (float)5.11);
    threadC[1] = new Company ("Hannover", "NH", 3755);
    threadP[1].setJob (threadC[1], 28000.0);

    // begin the transaction
    threadTxact[1].begin ();
    System.out.println ("\tTHREAD: Began transaction (2)");

    // define a database root
    mainDb.bind (threadP[1], "Root-Person-Th2");
    System.out.println ("\tTHREAD: Binding to \"Root-Person-Th2\" Person: " +
			threadP[1]);

    // commit the transaction
    threadTxact[1].commit ();
    System.out.println ("\tTHREAD: After commit of transaction (2)");

    // join the main transaction
    mainTxact.join ();
    System.out.println ("\tTHREAD: After join main transaction");

    threadP[2] = new Person ("Erwin", "Hansen", 29, (float)6.2);
    threadC[2] = new Company ("Hague Medical Store", "FL", 33325);
    threadP[2].setJob (threadC[2], 100000.0);

    // define a database root
    mainDb.bind (threadP[2], "Root-Person-Th3");
    System.out.println ("\tTHREAD:Binding to \"Root-Person-Th3\" Person: " +
			threadP[2]);
    
    // leave the main transaction
    mainTxact.leave ();
    System.out.println ("\tTHREAD: After leave main transaction");
  }
}
