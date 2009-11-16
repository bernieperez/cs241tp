/*program which adds a user to the specified data base*/

import java.util.*;
import com.versant.util.*;

public class AddUser
{
	public static void main(String[] args)

	{
		if(args.length < 4)
		{
			System.out.println ("Usage: java AddUser <database> <username> <pwd> <access>");
			System.out.println ("\nAccess: readonly->r readwrite rw");
            System.exit (-1);
		}

		/*Database name ,username,password and access mode are obtained from the console*/

		String dbname = args[0];
		String uname = args[1];
		String pwd = args[2];
		String acc = args[3];

		/*addDBUser() is the method used for creating a user*/

		try
		{
		 	DBUtility.addDBUser(dbname,uname,pwd,acc);
			System.out.println("User Created");
		}

		catch(Exception e)
		{
			System.out.println("User can't be created");
			e.printStackTrace();
		}

	}
}
