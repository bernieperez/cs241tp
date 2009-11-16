
/*Create Database*/
package DataBaseUtility;

import com.versant.fund.*;
import com.versant.trans.*;
import com.versant.util.*;
import java.util.*;
import java.io.*;

/*Create Database*/

public class CreateDBs
{
	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			System.out.println ("Usage: CreateDBs [ -create | -remove ] <db>");
			System.exit(-1);
		}
		/* The names of the  database is accepted from the console  */
		String option=args[0];
		String database=args[1];

		/* invoking the constructor*/

		new CreateDBs(option,database);
	}

	public CreateDBs(String opt,String db)
	{
		if(opt.equals("-create")) {
			Properties p=new Properties();

			/*-g -> group database */

			p.put ("-g","");

			/* create a database directory */

			try
			{
				DBUtility.makeDB(db,p);

				/*Create,format and initialize a new database */

				DBUtility.createDB(db);
				System.out.println("Database " + db + " created\n");
			}
			catch(Exception e)
			{

				System.out.println("Error\n");
				e.printStackTrace();
			}

			Properties p2=new Properties();
			p2.put ("-n","vol");
			p2.put ("-p","versant");
			DBUtility.addVol(db,p2);
			System.out.println("Volume added\n");
		} else {
			Properties p1=new Properties();
			p1.put ("-f","");
			DBUtility.stopDB(db,p1);
			p1.put("-rmdir","");
			DBUtility.removeDB(db,p1);
			System.out.println("Database " + db + " removed");
		}
		System.exit(0);

	}

}
