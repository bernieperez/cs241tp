
/*program which migrates the object of a class 'Human' from one database to another*/

import com.versant.trans.*;
import com.versant.fund.*;
import com.versant.fund.HandleVector.*;
import java.util.*;
public class Migrate
{
	public static void main (String[] args)
	{
   		 if (args.length < 2)
   		 {
        	System.out.println ("Usage: Migrate <source db> <dest db>");
        	System.exit (-1);
   		 }



		/* the source and destination databases specified in the console are coping to string variables sourcedb and destbd*/

    	String sourcedb= args[0];
    	String destdb=args[1];

    	Properties properties = new Properties();
    	properties.put("database", sourcedb);

		/* begin session by instantiating FundSession using sourcedb as the database */

    	FundSession session = new FundSession(properties);

    	// drop Human class if exists

    	try
    	{
        	session.locateClass("Human").dropClass();
    	}
    	catch (VException vex) {}

   		// define class Human

    	AttrString name= session.newAttrString("name");
    	AttrFloat weight= session.newAttrFloat("weight");


		//Now the class human has two attributes name and weight

    	AttrBuilder[] attrs=
		{
        	session.newAttrBuilder(name).withBTreeIndex(),
        	session.newAttrBuilder(weight)
    	};

   		 ClassHandle Human= session.withAttrBuilders(attrs).defineClass("Human");


		//connecting to destination database

		session.connectDatabase(destdb);

   		// create two instances of class Human using ClassHandle Human

    	Handle h1= Human.makeObject();
    	h1.put( name,"meena" );
   		h1.put( weight, (float)54.0 );

   		Handle h2= Human.makeObject();
    	h2.put( name,"seetha" );
    	h2.put( weight,(float)72.0 );

    	session.commit();//transaction completes

		HandleVector hv = session.newHandleVector();
		hv.addHandle (h1);
		hv.addHandle (h2);

		try
		{

			/* migrateObject() method Physically moves the objects in this vector from database sourcedb
		 	to database destdb. */

			hv.migrateObjects(sourcedb,destdb);

			session.commit();//commiting the changes

			System.out.println("Object is migrated from "+sourcedb+" to "+ destdb);
    	}
		catch (VException vex)
		{

			/*An exception will occur if the class Human is not existing in the
			destination database*/

			System.out.println (vex);

			Handle[] failed = vex.getFailedHandles ();
			for(int i=0;i<failed.length;i++)
			{
				//objects which are not copied are dispalyed
				System.out.println (failed[i].get(name));
			}
		}
     }
}
