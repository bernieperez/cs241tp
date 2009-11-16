
import com.versant.fund.*;
import java.util.Properties;

/**
 * This example program demonstrates the usage of multiple databases. 
 */
public class MultiDbExample
{

    static void say (String whatToSay)
    {
        System.out.println (whatToSay);
    }

    public static void main( String[] args ) 
    {
	
        ClassHandle ch;
        ClassHandle stubClsSession;
        ClassHandle stubClsGroup;
        HandleVector stubHv;
        int	     count;

        if (args.length < 2) {
            System.out.println ("Usage: MultiDb <sessionDb> <groupDb> "); 
            System.exit(-1);
        }

        String sessionDb = args[0];
        String groupDb1 = args[1];

        // begin session on the session database         
        Capability capability= new NewSessionCapability();
        Properties properties = new Properties();
        properties.put("database", sessionDb);
        FundSession session = new FundSession(properties, capability);

        //connect to a group database
        session.connectDatabase(groupDb1);
	
        // define PayStub class to groupDb1.	
	stubClsGroup = Paystub.defineClass(session, groupDb1);
	session.commit();
	say ("defined Paystub to " + groupDb1);
	
	/*
	 * create one Paystub object in groupDb1
	 */
	try {
		stubClsGroup = session.withDatabase(groupDb1).locateClass("Paystub");
		stubClsGroup.makeObject();
		session.commit();
		say ("created one Paystub object in " + groupDb1);
	} catch (VException vex) {
		say ("In locateClass or makeObject caught VERSANT error: " + vex);
	}

	/*
	 * select Paystub objects from groupDb1 and put result in a
	 * HandleVector
	 */
	stubHv = session.newHandleVector(stubClsGroup.select());
	count = stubHv.size();
	say("selected " + count + " Paystub(s) from " + groupDb1);
	if (count > 0) {
		ch = stubHv.firstHandle().classObjectOf();
		say ("Class object of Paystub selected from " + groupDb1 + " is:" + ch.toString());
	}
	
	/* 
	 * synchronize Paystub to sessionDb. Since sessionDb does not have
	 * the definition of Paystub yet, synchroizeClass() will create 
	 * Paystub class in sessionDb.
	 */
	try {
		stubClsGroup.synchronizeClass (sessionDb, Constants.SC_ABORT);
		session.commit();
		say("synchronized Paystub class from " + groupDb1 + " to " + sessionDb);
	} catch (VException vex) {
		say ("In synchroizeClass caught VERSANT error: " + vex);
	}

	/*
	 * using withDatabase() on ClassHandle, find the Paystub class
	 * object in sessionDb
	 */
	try {
		stubClsSession = stubClsGroup.withDatabase(sessionDb);
		say("Class object of Paystub in " + sessionDb + " is: " + stubClsSession.toString());
	} catch (VException vex) {
		say ("In ClassHandle.withDatabase(), caught VERSANT error: " + vex);
	}

	/*
	 * create two Paystub objects on sessionDb
	 */
	stubClsSession = session.withDatabase(sessionDb).locateClass("Paystub");
	stubClsSession.makeObject();
	stubClsSession.makeObject();
	say("created two Paystub objects in " + sessionDb);
	session.commit();
	
	/*
	 * select Paystub objects from sessionDb, and place result in
	 * a HandleVector
	 */
	stubHv = session.newHandleVector(stubClsSession.select());
	count = stubHv.size();
	say("selected " + count + " Paystub(s) from " + sessionDb);

	/* 
	 * clean up: drop classes both sessionDb and groupDb1
	 */
	stubClsSession.dropClass();
	stubClsGroup.dropClass();
	say("dropping classes from both databases ...");
	session.commit();

	session.disconnectDatabase (groupDb1);
	session.endSession();
} //main

} // end MultiDb

