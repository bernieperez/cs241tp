
/* In this program we compare the object of class student1 in the two databases.*/
//It is speceified using the -class option.
package DataBaseUtility;

import com.versant.fund.*;
import com.versant.trans.*;
import java.util.*;
import com.versant.util.*;
import DataBaseUtility.model.*;

public class ClassCompDB
{
	public static void main(String[] args)
	{

		 if (args.length < 2)
		 {
           		 System.out.println ("Usage: java ClassCompDB <database1> <database2> ");
            	 System.exit (-1);
         }

		/*The names of the two databases specified in the console is stored in to string variables 'dbname1' and 'dbname2' */

		String dbname1=args[0];
		String dbname2=args[1];


		TransSession s = new TransSession(args[0]);
		Student stud = new Student("Stud1", 1);
		s.commit();
		s.endSession();

		Properties p = new Properties();
  		DBCompareResult result=new DBCompareResult(0);

		/*-value Compare object values as well as object identifiers and display the first logical object identifier for objects
		with non-equal values.*/

  		//p.put("-value", " ");

		/*-classes <class1><class2> ... Compare objects of only the specified classes.
		here we specify only class student1*/

 		 p.put("-classes", "DataBaseUtility.model.Student");

  		java.util.Vector loidsDb ;
  		java.util.Vector loidsDb2 ;
  		try
  		{
    			result = DBUtility.compareDBs(dbname1,dbname2, p);
    	}

  		catch (com.versant.fund.VException vex)
  		{
    			vex.printStackTrace();
  		}

		/*getStatus() method returns the value of the integer variable status in DBCompareResult*/

		/*The objects are stored in a vector and convert into string using toString()*/


  		if(result.getStatus() == com.versant.fund.Constants.DATABASES_ARE_IDENTICAL)
 		{
    			System.out.println("Databases are identical");
  		}
  		else if (result.getStatus() == com.versant.fund.Constants.SOME_OBJECTS_ARE_DIFFERENT)
  		{
    			System.out.println("Databases are different");
  		}
  		else
  		{
    			System.out.println("Error");
  		}

	}
}
