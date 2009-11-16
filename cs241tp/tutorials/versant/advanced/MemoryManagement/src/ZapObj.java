
/*This program demonstrates the usage of zapObjects() function */
import com.versant.trans.*;
import com.versant.fund.*;
import com.versant.fund.HandleVector.*;
import java.util.*;
public class ZapObj
{

	public static void main (String[] args)
	{
    	if (args.length < 1)
    	{
       		System.out.println ("Usage: ZapObj <db> ");
        	System.exit (-1);
   	 	}


		int free;
		int max;
		int percent;

		/* the database specified in the console is coping to
		string variable db*/

    	String db= args[0];

		//'capability' is used for starting a secure session.

    	Capability capability= new NewSessionCapability();
    	Properties properties = new Properties();
    	properties.put("database", db);

    	// begin session by instantiating FundSession

    	FundSession session = new FundSession(properties, capability);


    	// drop Human class if exists

    	try
    	{
        	session.locateClass("Human").dropClass();
    	}
    	catch (VException vex) {}

    	// define class Human

    	AttrString name= session.newAttrString("name");
    	AttrFloat weight= session.newAttrFloat("weight");

    	AttrBuilder[] attrs=
		{
        	session.newAttrBuilder(name).withBTreeIndex(),
        	session.newAttrBuilder(weight)
    	};

    	ClassHandle Human= session.withAttrBuilders(attrs).defineClass("Human");

    	// create two Human instances

    	Handle h1= Human.makeObject();
    	h1.put( name, "chanthu" );
    	h1.put( weight, (float)64.0 );

    	Handle h2= Human.makeObject();
    	h2.put( name, "Sankar" );
    	h2.put( weight, (float)62.0 );

    	session.commit();

    	HandleVector hv = session.newHandleVector();
    	hv.addHandle (h1);
    	hv.addHandle (h2);


		/* zapObjects() Releases the objects in this vector from memory and makes their cached object descriptors available for re-use.	Releasing cached object descriptors invalidates handles to the corresponding objects.Handles in this vector should not be re-used after invocation of this method Present in the class HandleVector */

		free=session.cacheFreeKb();
		max = session.cacheFreeMaxKb ();
		percent = session.cacheFreePercent ();

		System.out.println("\nThe current Memory Status is\n");
		System.out.println("FreeSpace:  "+free);
		System.out.println("MaximumSpace: "+max);
		System.out.println("Percentage Of FreeSpace: "+percent);
		System.out.println("\n");

		Handle hnd;
		FundQuery query =new FundQuery(session, "select selfoid from Human");
		FundQueryResult result = query.execute();

		while ((hnd = result.next()) != null)
		{

			hv.addHandle (hnd);
		}


		hv.zapObjects();

		max = session.cacheFreeMaxKb ();
		percent = session.cacheFreePercent ();
		free=session.cacheFreeKb();

		System.out.println("After using zapObjects()\n");
		System.out.println("FreeSpace:  "+free);
		System.out.println("MaximumSpace: "+max);
		System.out.println("Percentage Of FreeSpace: "+percent);
	}
}
