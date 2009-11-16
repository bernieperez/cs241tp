/**
 * Demo the VERSANT JTA/XA interface.
 * It uses the simple constructor in VXAResource that just takes the database
 * name and lets VXAResource handle all the SessionPool details. 
 * Consequently this demo uses TransSessions.
 */ 

import model.Person;
import java.util.*;
import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;
import javax.transaction.xa.*;
import com.versant.xa.*;

public class XADemo1 {

  /**
   * the constructor.
   */
  public XADemo1() {
  }

  /**
   * The main function: argument needed is the database name.
   */
  public static void main( String[] argv ) throws Throwable
  {
    Person person;

    if (argv.length  < 1) {
      System.err.println("Usage: java XADemo1 <dbname1>");
      System.exit(1);
    }

    System.out.println("VERSANT JTA/XA Demo using TransSessions");

    // Create a couple of xids with fixed value, just for testing. 
    // Assigned values are totally random.
    byte[] b = new byte[2];
    b[0] = 1;
    b[1] = 2;
    VXid xid = new VXid(1,1,1,b);

    byte[] b1 = new byte[2];
    b[0] = 3;
    b[1] = 4;
    VXid xid2 = new VXid(1,1,1,b1);
    
    // the database name is the first argument. During Construction of a new 
    // instance of VXAResource,  xa_open will be automatically called.
    VXAResource xa = new VXAResource(argv[0]);
   
    // Start the first Transaction.
    xa.start(xid, TMFlags.TMNOFLAGS);
    System.out.println("Started Transaction 1");

    // Get the Session object associated with the VXid xid in the Sessionpool.
    TransSession transSession = (TransSession)xa.getSessionPool().get(xid);

    // Create a new instance of Person.
    person = new Person("Ron", "Richards", 36, (float)5.8);
    
    // Make Person object persistent.
    transSession.makePersistent(person);
    System.out.println("Made persistent Person: " + person);
 
    // End the association of the current thread with transaction 1.
    xa.end(xid, TMFlags.TMSUCCESS);
    
    // Commit Transaction 1.
    int i = xa.prepare(xid);
    xa.commit(xid, false);
    System.out.println("After Transaction 1 is commited. \n");
    
    // Start the second Transaction. Use the Other VXid object.
    xa.start(xid2, TMFlags.TMNOFLAGS);
    
    // Get the Session Object associated with VXid xid2
    transSession = (TransSession)xa.getSessionPool().get(xid2);

    // Create another Person.
    person = new Person("Bar", "Graf", 28, (float)6.2);
    transSession.makePersistent(person);
    
    System.out.println("Made persistent Person: " + person);
   
    // Going to call xa_end with a TMFAIL flag. Getting ready to rollback.
    xa.end(xid2, TMFlags.TMFAIL);

    // rollback the transaction.
    xa.rollback(xid2);
    System.out.println("After rolling back the second transaction");

    // Start a new transaction. Will query the database for Person objects.
    xa.start(xid, TMFlags.TMNOFLAGS);
    transSession = (TransSession)xa.getSessionPool().get(xid);

    VQLQuery		vql;
    Enumeration		resultEnum;

    // do a query to retrieve all Person objects
    vql = new VQLQuery(transSession, "select selfoid from model.Person");
    resultEnum = vql.execute();
    
    System.out.println("Finished Querying Database for Person Objects");
    while (resultEnum.hasMoreElements()) {
	System.out.println("Person: " + (Person) resultEnum.nextElement());
    }	

    xa.end(xid, TMFlags.TMSUCCESS);

    xa.commit(xid, true);

    // close the connection to the XAResource.
    xa.close();

    // Clear up the session pool. endSession will be called on all the
    // sessions in the pool.
    VSessionPool sessionPool = (VSessionPool)xa.getSessionPool();
    sessionPool.clearSessionPool();
  }
}
