
import model.*;
import com.versant.fund.Capability;
import com.versant.fund.NewSessionCapability;
import java.util.*;
import com.versant.trans.TransSession;


public class TransThread1 extends Thread {

  TransSession[] threadSession; // the sessions created in this thread
  TransSession   mainSession;   // the session created in the main program
  String      threadDbName;  // name of the database opened in the main program
  String      mainDbName;    // name of the database opened in this thread
  Capability  cap;           // the capability created in the main program


  /**
   * the ctor
   */
  public TransThread1(TransSession handed,
      String aDbName, String aMainDbName,
      Capability aCap) {
    mainSession = handed;
    threadDbName = aDbName;
    mainDbName = aMainDbName;
    cap = aCap;
  }


  public synchronized void run() {

    threadSession = new TransSession[2];

    Person[] threadP = new Person [3];
    Company[] threadC = new Company [3];

    // create a transparent session with the thread database as dbname
    Properties prop = new Properties();
    prop.put("database", threadDbName);
    prop.put("sessionName", "spawned session");
    threadSession[0] = new TransSession(prop, cap);

    threadP[0] = new Person("Udai", "Hussein", 35, (float)6.1);
    threadC[0] = new Company("Baghdad Botany", "PA", 16802);
    threadP[0].setJob(threadC[0], 28000.0);

    threadSession[0].makePersistent(threadP[0]);
    System.out.println("\tTHREAD: Made persistent Person: " + threadP[0]);

    // commit and end session
    threadSession[0].commit();
    threadSession[0].endSession();
    System.out.println("\tTHREAD: After commit and end session(1)\n");

    // create a new session on the main database
    prop = new Properties();
    prop.put("database", mainDbName);
    prop.put("sessionName", "spawned 2");
    threadSession[1] = new TransSession(prop, cap);
    threadSession[1].setSession();

    threadP[1] = new Person("John", "Barleycorn", 49, (float)5.11);
    threadC[1] = new Company("Hannover", "NH", 3755);
    threadP[1].setJob(threadC[1], 28000.0);

    threadSession[1].makePersistent(threadP[1]);
    System.out.println("\tTHREAD: Made persistent Person: " + threadP[1]);

    // commit and end session
    threadSession[1].commit();
    threadSession[1].endSession();
    System.out.println("\tTHREAD: After commit and end session(2)\n");

    // join the main session
    mainSession.setSession();
    System.out.println("\tTHREAD: After join main session");

    threadP[2] = new Person("Erwin", "Hansen", 29, (float)6.2);
    threadC[2] = new Company("Hague Medical Store", "FL", 33325);
    threadP[2].setJob(threadC[2], 100000.0);

    mainSession.makePersistent(threadP[2]);
    System.out.println("\tTHREAD: Made persistent Person: " + threadP[2]);

    // leave the main session
    mainSession.leaveSession ();
    System.out.println("\tTHREAD: After leave main session");
  }
}
