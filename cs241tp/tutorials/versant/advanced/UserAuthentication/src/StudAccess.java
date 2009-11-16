import com.versant.trans.*;
import java.util.*;
import com.versant.util.*;

public class StudAccess
{
	public static void main(String[] a)
	{
		TransSession ss;
		if (a.length < 3)
		{
       			 System.out.println ("Usage: StudAccess <database> <username> <password>");
        		System.exit (-1);
    	}


		/*The name of the database specified in the console is stored in to string variable
'		database' */

		String database=a[0];
		String uname=a[1];
		String pwd=a[2];

		Properties p = new Properties();
		p.put("database",database);

		/* if the username and password is not set for the data base an exception will results*/

		p.put("userName",uname);
		p.put("userPassword",pwd);

		//A new Session begins using 'p'

		try
		{
			ss= new TransSession(p);
			System.out.println("You have successfully accessed the database\n");
			ss.endSession();
		}

		catch(Exception e)
		{
				System.out.println("Error in accessing database\n");
				e.printStackTrace();
		}


	}
}
