
/*This program demonstrates the usage of  various memory management functions */
import com.versant.trans.*;
import com.versant.fund.*;
import com.versant.fund.HandleVector.*;
import java.util.*;
public class GroupWriteObj
{

	public static void main (String[] args)
	{
    	if (args.length < 2)
    	{
        	System.out.println ("Usage: GroupWriteObj <db> <opt (0,1,2)>");
			System.out.println ("\nOptions-0 for 0,1 for Constants.FREE_OBJECTS , 2 for Constants.CLEAN_CODS \n");
        	System.exit (-1);
    	}


		int free;
		int max;
		int percent;
		int opt=0;
		/* the database specified in the console is coping to
		string variable db*/

    	String db= args[0];

		if(args[1].equals("0"))
			opt=0;
		else if(args[1].equals("1"))
			opt=Constants.FREE_OBJECTS;
		else if(args[1].equals("2"))
			opt=Constants.CLEAN_CODS;

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

		/*groupWriteObjects :- Writes objects as a group.
			Alternatives for options are:
			0
			Constants.FREE_OBJECTS
			Constants.CLEAN_CODS */
		Handle hnd;
		FundQuery query =new FundQuery(session, "select selfoid from Human");
		FundQueryResult result = query.execute();

		while ((hnd = result.next()) != null)
		{
				hv.addHandle (hnd);
		}

		hv.groupWriteObjects(opt);

		max = session.cacheFreeMaxKb ();
		percent = session.cacheFreePercent ();
		free=session.cacheFreeKb();

		System.out.println("After using option-"+opt+"\n");
 		System.out.println("FreeSpace:  "+free);
		System.out.println("MaximumSpace: "+max);
		System.out.println("Percentage Of FreeSpace: "+percent);
		System.out.println("\n");

	}
}
