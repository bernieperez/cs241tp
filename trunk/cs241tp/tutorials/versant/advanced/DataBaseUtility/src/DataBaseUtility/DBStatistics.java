
/* This program demonstrate the usage of some of the methods in Statistics class */

package DataBaseUtility;

import com.versant.trans.*;
import com.versant.fund.*;
import com.versant.util.*;
import com.versant.util.DBConnectInfo;
import java.util.*;

public class DBStatistics
{
	public static void main (String[] args)
	{
    	if (args.length < 1)
    	{
        	System.out.println ("Usage: StatEg <db> ");
        	System.exit (-1);
    	}

		/* The names of the  database is accepted from the console  */

   		String database= args[0];
		int i;
		Properties prop = new Properties ();

		/*specify the database */

        prop.put ("database", database);

		/*specify the lock mode */

        prop.put ("lockmode", Constants.NOLOCK + "");

		/*specify the access mode */

        //prop.put ("options",  Constants.READ_ACCESS + "");

 		// begin session by	instantiating TransSession

        TransSession session = new TransSession(prop);

		//collect the Statistics details by invoking getActivityInfo() */

		Object[][] result = VStatistics.getActivityInfo(database,null,null,null);

		/* Store the lock info in the DBLockInfo object */

		DBLockInfo[] lockInfo_result;
		lockInfo_result = (DBLockInfo[])(result[0]);

		/* Store the Transaction info in the DBTransInfo object */

		DBTransactionInfo[] transInfo_result;
		transInfo_result = (DBTransactionInfo[])(result[1]);

		/* Store the connection info in the DBConnectInfo object */

		DBConnectInfo[] connectInfo_result;
		connectInfo_result = (DBConnectInfo[])(result[2]);

		/*convert the lock info in to String format using toString() */

		String lock=lockInfo_result.toString();
		System.out.println("Lock Information: "+lock);

		/*convert the Trans info in to String format using toString() */

		String trans=transInfo_result.toString();
		System.out.println("Transaction Information: "+trans);

		/*convert the connect info in to String format using toString() */

		String connect=connectInfo_result.toString();
		System.out.println("connection Information: "+connect);

		/*display the username and session name */

		for(i=0;i<connectInfo_result.length;i++)
		{
			String usr=connectInfo_result[i].getUserName();
			System.out.println("connection user: "+usr);

			String ss=connectInfo_result[i].getSessionName();
			System.out.println("connection session: "+ss);
		}

		/*display the transaction Id and lock name */

		for(i=0;i<transInfo_result.length;i++)
		{
			String transid=transInfo_result[i].getTransactionId();
			System.out.println("Transaction ID: "+transid);

			String translk=transInfo_result[i].getName();
			System.out.println("Transaction Lockname: "+translk);
		}

	}
}
