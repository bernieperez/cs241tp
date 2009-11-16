
//program to compare two databases using CompareDBResult method
package DataBaseUtility;

import com.versant.fund.*;
import com.versant.trans.*;
import java.util.*;
import com.versant.util.*;

public class DBComp
{
	public static void main(String[] a)
	{
		if (a.length < 2)
		{
           		 System.out.println ("Usage: java DBComp <database1> <database2>");
            	 System.exit (-1);
        }

		/*The names of the two databases specified in the console is stored in to string variables 'dbname1' and 'dbname2' */

		String dbname1=a[0];
		String dbname2=a[1];
		Properties p = new Properties();

		/*here we are using the method 'compareDb' in DBUtility class for comparing the two databases*/

		CompareDBResult[] result = DBUtility.compareDb (dbname1, dbname2,p);

		/*The ID of the first unidentical object is returned by getOid()*/
		String s=result[0].getOid();

		/*The Description of the first unidentical object is returned by getDesc()*/
		String desc=result[0].getDesc();

		System.out.println("The id of the 1st object which is different is "+s);
		System.out.println(" Description: "+desc);

	}
}
