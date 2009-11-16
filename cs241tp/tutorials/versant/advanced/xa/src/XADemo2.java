/**
 * Demo the VERSANT JTA/XA interface.
 * This program demonstrates the usage of VERSANT JTA/XA integration with the
 * VERSANT Fundamental binding.
 *
 * This program demonstrates how the VXAResource(SessionPool ) constructor can
 * be used.
 */ 
import model.Person;
import com.versant.fund.*;
import com.versant.xa.*;
import java.util.Properties;

public class XADemo2
{
  static String dbName;

  public static void main( String[] argv ) throws Throwable
  {
    if (argv.length < 1)
    {
      System.out.println("Usage: java XADemo2 <databaseName>");
      System.exit(1);
    }
      
    // the database name is the first argument
    dbName = argv[0];

    // Create a xid with fixed value, just for testing. Assigned values are
    // totally random
    byte[] b = new byte[2];
    b[0] = 1;
    b[1] = 2;
    VXid xid = new VXid(1,1,1, b);
  
    // Create an inner class that implements the SessionFactory Interface.
    SessionFactory sessionFactory = new SessionFactory() 
    {
      int sessionCount=0;     
      public FundSession create() 
      {
        Properties p = new Properties();
        p.put("database", dbName);

	// These options are a must for any session to be used in a XA
	// Transaction.
        p.put("options", (Constants.DONT_JOIN|Constants.EXTERNAL_XACT)+"");
	
        p.put("sessionName", "session" + sessionCount++);
        return new FundSession(p);
      }
    };
   
    // Create a new sessionPool Object. The initial number of sessions is 1
    // and the maximum number is given to be 5.
    VSessionPool sessionPool = new VSessionPool(sessionFactory,1,5);
    
    // Instantiate a new VXAResource object using this sessionPool object.
    VXAResource   xa = new VXAResource(sessionPool);

    // Start the first XA transaction.
    System.out.println("Starting first Transaction...");
    xa.start(xid, TMFlags.TMNOFLAGS);
    FundSession s = xa.getSessionPool().get(xid); 

  
    // Define database class "Person"
    // use Attr objects to describe the attributes
    AttrString name = s.newAttrString("name");
    AttrFloat weight = s.newAttrFloat("weight");

    // an array of AttrBuilders will be required
    AttrBuilder[] attrs = {
      s.newAttrBuilder(name).withBTreeIndex(),
      s.newAttrBuilder(weight)
    };

    // define the class Person to the database
    System.out.println("Defining Person class to database...");
    ClassHandle  person;

    person = s.withAttrBuilders(attrs).defineClass("Person");

    //  Create instance of "Person"
    System.out.println("Creating Person object...");

    // make a Person, using Attr objects to put attribute values
    Handle janos = person.makeObject();
    janos.put( name, "Janos" );
    janos.put( weight, (float)14.0 );

    // commit the transaction
    System.out.println("Committing to database " + dbName + "...");
    xa.end(xid, TMFlags.TMSUCCESS);
    xa.prepare(xid);
    xa.commit(xid, false);

    // begin a new transaction
    System.out.println("Starting the second XA Transaction...");
    xa.start(xid, TMFlags.TMNOFLAGS);
    s = xa.getSessionPool().get(xid);

    // make a Person, using Attr objects to put attribute values
    System.out.println("Creating one more Person object...");
    Handle John = person.makeObject();
    janos.put( name, "John" );
    janos.put( weight, (float)16.0 );

    // rollback the transaction
    System.out.println("Rolling back the transaction...");
    xa.end(xid, TMFlags.TMSUCCESS);
    xa.rollback(xid);

    // begin a new transaction
    System.out.println("Starting third XA Transaction...");
    xa.start(xid, TMFlags.TMNOFLAGS);
    s = xa.getSessionPool().get(xid);

    
    // perform a query
    // use the Attr object "name" to create a predicate
    Predicate p = name.matches( "J*" );

    // do the select, put result into an array of Handle
    System.out.println("Finding Persons whose names start with 'J'...");
    Handle[] r = person.select(p).asArray();

    // print the results, using Attr objects to get attribute values
    for (int i=0; i<r.length; i++ ) {
      System.out.println("Found: " + r[i].get(name) +
             " with weight " + r[i].get(weight) +
             ",  LOID=" + r[i].toString());
    }

    
    // Drop the Peron class from database
    System.out.println("Dropping Person class and its instances...");
    person.dropClass();
 
    // Calling xa end with the TMSUSPEND flag. Will commit the changes in
    // another transaction.
    System.out.println("Suspending the Current transaction...");
    xa.end(xid,TMFlags.TMSUSPEND);
    

    System.out.println("Resuming XA Transaction three...");
    xa.start(xid,TMFlags.TMRESUME);
    s = xa.getSessionPool().get(xid);
    
    // commit the transaction in one phase
    System.out.println("Committing transaction three in one phase mode...");
    xa.end(xid, TMFlags.TMSUCCESS);
    xa.commit(xid, true);

    // Terminate the sessions
    sessionPool.clearSessionPool();
  } // end main
} // end XADemo2
