
/*This program demonstrates the usage of  various memory management functions */
import com.versant.trans.*;
import com.versant.fund.*;
import com.versant.fund.HandleVector.*;
import java.util.*;
public class CommitAndCleanCod
{

	public static void main (String[] args)
	{
    	if (args.length < 1)
    	{
        	System.out.println ("Usage: commitAndCleanCod <db>");
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

    	HandleVector hv = session.newHandleVector();
    	hv.addHandle (h1);
    	hv.addHandle (h2);


		/*cacheFreeMaxKb() Returns the size in kilobytes of the largest free contiguous space of object memory cache.

		cacheFreeKb() Returns the size of object memory cache that is free (0 to 100).

		cacheFreePercent() Returns the percentage of object memory cache that is free (0 to 100).

		All present in the class FundSession */

        free=session.cacheFreeKb();
		max = session.cacheFreeMaxKb ();
		percent = session.cacheFreePercent ();

		System.out.println("\nThe current Memory Status is\n");
		System.out.println("FreeSpace:  "+free);
		System.out.println("MaximumSpace: "+max);
		System.out.println("Percentage Of FreeSpace: "+percent);
		System.out.println("\n");

		/*commitAndCleanCod():-	Commits transactions and clears cache.
		This method commits all persistent object modifications made within the session,
		releases locks held on all objects, releases all COD entries from the VERSANT
		front-end object memory cache*/
		Handle hnd;
		FundQuery query =new FundQuery(session, "select selfoid from Human");
		FundQueryResult result = query.execute();

		while ((hnd = result.next()) != null)
		{
			hv.addHandle (hnd);
		}

		session.commitAndCleanCod();

		max = session.cacheFreeMaxKb ();
		percent = session.cacheFreePercent ();
		free=session.cacheFreeKb();

		System.out.println("After using commitAndCleanCod() \n");
 		System.out.println("FreeSpace:  "+free);
		System.out.println("MaximumSpace: "+max);
		System.out.println("Percentage Of FreeSpace: "+percent);
		System.out.println("\n");


	}
}
