
/*program to compare two databases and display allobjects which are not identical */
package DataBaseUtility;

import com.versant.fund.*;
import com.versant.trans.*;
import java.util.*;
import com.versant.util.*;

public class CompDB
{
	public static void main(String[] a)
	{
		if(a.length < 2)
		{
			   	 System.out.println ("Usage: java CompDB <database1> <database2>");
            	 System.exit (-1);
        }

		/*The names of the two databases specified in the console is stored in to string variables 'dbname1' and 'dbname2' */

		String dbname1=a[0];
		String dbname2=a[1];
		Properties p = new Properties();

		/*-value Compare object values as well as object identifiers and display the first logical object identifier for objects
		with non-equal values.*/

		p.put("-value", " ");

		/*-fullcompare Display list of loids of objects, which are different in two databases. (This option is incompatible with -
		noprint option).*/

		p.put("-fullcompare"," ");

		/*here we are using the method 'compareDBs' in DBUtility class for comparing the two databases*/


		DBCompareResult result = DBUtility.compareDBs (dbname1, dbname2, p);

		int res=result.getStatus();

		/*getStatus() method returns the value of the integer variable status in DBCompareResult*/

		if (res == com.versant.fund.Constants.DATABASES_ARE_IDENTICAL)
		{
			System.out.println("Database are identical");
		}
		else
		{
			System.out.println(" Databases are not identical");

			/*getLoidsDB1() returns the unidentical objects in first database*/

			Vector loid1 = result.getLoidsDB1();
			String a1;
			a1=loid1.toString();

			System.out.println("\nUnidentical objects in "+dbname1);
			System.out.println(a1);

			Vector loid2 = result.getLoidsDB2();
			String a2;
			a2=loid1.toString();

			System.out.println("\nUnidentical objects in "+dbname2);
			System.out.println(a2);
			/*The objects are stored in a vector and convert into string using toString()*/

			/*Vector loidsVal = result.getLoidsByValue(); // only if "-value" is supplied
			String a2;
			a2=loid1.toString();
			System.out.println(a2);*/
		}

	}
}
