import model.*;
import com.versant.odmg.*;


public class ODMGThread2
extends Thread {

  Transaction threadTxact; // the txact created in this thread
  Transaction mainTxact;   // the txact created in the main program
  Database    mainDb;      // the database opened in the main program

  /**
   * the ctor
   */
  public ODMGThread2 (Transaction handed, Database db) {
    mainTxact = handed;
    mainDb = db;
  }


  public synchronized void run () {

    Person[] threadP = new Person [2];
    Company[] threadC = new Company [2];
    
    threadTxact = new Transaction ();

    threadP[0] = new Person ("Udai", "Hussein", 35, (float)6.1);
    threadC[0] = new Company ("Baghdad Botany", "PA", 16802);
    threadP[0].setJob (threadC[0], 28000.0);

    // begin the transaction
    threadTxact.begin ();
    System.out.println ("\tTHREAD: Began transaction (1)");

    // define database root
    mainDb.bind (threadP[0], "Root-Person-Th1");
    System.out.println ("\tTHREAD: Binding to \"Root-Person-Main1\" Person: " +
			threadP[0]);

    // commit the transaction
    threadTxact.commit ();
    System.out.println ("\tTHREAD: After commit of transaction (1)");

    // join the main transaction
    mainTxact.join ();
    System.out.println ("\tTHREAD: After join main transaction");

    threadP[1] = new Person ("Erwin", "Hansen", 29, (float)6.2);
    threadC[1] = new Company ("Hague Medical Store", "FL", 33325);
    threadP[1].setJob (threadC[1], 100000.0);

    // define database root
    mainDb.bind (threadP[1], "Root-Person-Th2");
    System.out.println ("\tTHREAD:Binding to \"Root-Person-Th2\" Person: " +
			threadP[1]);

    // leave the main transaction
    mainTxact.leave ();
    System.out.println ("\tTHREAD: After leave main transaction");
  }
}
