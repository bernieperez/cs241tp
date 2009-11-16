
/*Copy the contents of one database to an empty database that is created*/
package DataBaseUtility;

import com.versant.fund.*;
import com.versant.trans.*;
import java.util.*;
import com.versant.util.*;

public class CopyDB
{
	public static void main(String[] a)
	{
		if (a.length < 2)
		{
           		 System.out.println ("Usage: java CopyDB <sourcedatabase> <destinationdatabase>");
            	 System.exit (-1);
        }

		/*The names of the two databases specified in the console is stored in to string variables 'dbname1' and 'dbname2' */

		String dbname1=a[0];
		String dbname2=a[1];
		Properties p= new Properties();

		/*-nocreate Copy a database into an existing database without creating a new database for the copy.*/

		p.put("-nocreate", " ");

		/*copyDB() method in DBUtility class is used for copying*/

		DBUtility.copyDB(a[0],a[1],p);

		System.out.println("Contents of "+dbname1+" is copied to "+dbname2);


	}
}


