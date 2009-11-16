
/*Create and delete users of database*/
package DataBaseUtility;

import com.versant.fund.*;
import com.versant.trans.*;
import com.versant.util.*;
import java.util.*;
import java.io.*;

public class CreateUser
{
	public static void main(String[] args)
	{
		if (args.length != 4)
		{
			System.out.println ("Usage: CreateUser [ -create | -remove ] <db> <user> <password> ");
			System.exit(-1);
		}
		/* The name of the database, username and password are accepted from the console  */

		String option=args[0];
		String database=args[1];
		String user=args[2];
		String password=args[3];
		/* invoking the constructor*/
		new CreateUser(option,database,user,password);
	}

	public CreateUser(String opt,String db,String user,String pwd)
	{
		DBUserInfo[] dbInfo1;
		if(opt.equals("-create")) {
			/* add the users to the user list */
			try
			{
				DBUtility.addDBUser(db,user,pwd,"rw");
			}
			catch(VException ve)
			{
				ve.printStackTrace();
			}

			/* get the usernames to a DBUserInfo array */

			dbInfo1 = DBUtility.getDBUsers(db);
			System.out.println("\nDetails of Database Users in the database "+db+"\n");

			/*display the user details */

			for(int i=0;i<dbInfo1.length;i++)
			{
				String user1=dbInfo1[i].getUserName();
				System.out.println("Database Users:"+user1);

			}
		} else {
			/*Remove the specified user from the user list */

			System.out.println("\nDelete User "+user);
			DBUtility.removeDBUser(db,user);

			/*display the user details */
			dbInfo1 = DBUtility.getDBUsers(db);
			System.out.println("\nDetails of Database Users in the database "+db+"\n");

			for(int i=0;i<dbInfo1.length;i++)
			{
				String user1=dbInfo1[i].getUserName();
				System.out.println("Database Users:"+user1);

			}
		}
	}
}

