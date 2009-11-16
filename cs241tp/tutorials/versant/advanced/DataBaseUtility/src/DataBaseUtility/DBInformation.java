
/*Accessing Database information*/
package DataBaseUtility;

import com.versant.fund.*;
import com.versant.trans.*;
import com.versant.util.*;
import java.util.*;

/*Accessing Database information*/

public class DBInformation
{
	String dbn;
	String dct;
	String dbown;
	String dbtype;
	String dbver;
	String user;
	int did;
	int i;
	int f=1;
	DBListInfo[] dbInfo;

	public static void main(String[] args)
	{
		if (args.length < 3)
		{
        		System.out.println ("Usage: DBInformation <machine> <owner> <db>");
        		System.exit(-1);
   		}

		/* The   machinename, owner and the database are accepted from the console  */

		String machine=args[0];
		String owner=args[1];
		String database=args[2];

		/* invoking the constructor*/

		new DBInformation(machine,owner,database);
	}

	public DBInformation(String mach,String own,String db)
	{

		dbInfo=new DBListInfo[100];
		System.out.println("Details of Databases at "+mach+"\n");

		/*collect the details of database information of the specified machine*/
		dbInfo = DBUtility.dbList(mach);

		/* display the details of database*/

		for( i=0;i<dbInfo.length;i++)
		{

			dbn=dbInfo[i].getDBName();
			System.out.println("database: "+dbn);

			dct=dbInfo[i].getDBCreateTime();
			System.out.println("database Create Time: "+dct);

			did=dbInfo[i].getDBID();
			System.out.println("database ID: "+did);

		 	dbown=dbInfo[i].getDBOwner();
			System.out.println("database Owner: "+dbown);

		 	dbtype=dbInfo[i].getDBType();
			System.out.println("database Type: "+dbtype);

			dbver=dbInfo[i].getDBVersion();
			System.out.println("database version: "+dbver+"\n");

		}

		/*collect the details of database owned by a the specified user */

		dbInfo = DBUtility.dbListOwnedBy(own);

		/* display the details of database*/

		System.out.println("\n\nDetails of Databases owned by "+own+"\n");

		for( i=0;i<dbInfo.length;i++)
		{

		 	dbn=dbInfo[i].getDBName();
			System.out.println("database: "+dbn);

			dct=dbInfo[i].getDBCreateTime();
			System.out.println("database Create Time: "+dct);

			did=dbInfo[i].getDBID();
			System.out.println("database ID: "+did);

			dbown=dbInfo[i].getDBOwner();
			System.out.println("database Owner: "+dbown);

		 	dbtype=dbInfo[i].getDBType();
			System.out.println("database Type: "+dbtype);

		 	dbver=dbInfo[i].getDBVersion();
			System.out.println("database version: "+dbver+"\n");

		}

		/*collect the details of specified database */

		dbInfo = DBUtility.namedDBInfo(db);

		/* display the details of database*/

		System.out.println("\n\nDetails of Database "+db+"\n");

		dbn=dbInfo[0].getDBName();
		System.out.println("database: "+dbn);

		dct=dbInfo[0].getDBCreateTime();
		System.out.println("database Create Time: "+dct);

		did=dbInfo[0].getDBID();
		System.out.println("database ID: "+did);

		dbown=dbInfo[0].getDBOwner();
		System.out.println("database Owner: "+dbown);

		dbtype=dbInfo[0].getDBType();
		System.out.println("database Type: "+dbtype);

		dbver=dbInfo[0].getDBVersion();
		System.out.println("database version: "+dbver);

		/*collect the details of database information in the Versant DB Directory*/

		dbInfo = DBUtility.dbListInVersantDBDirectory();
		System.out.println("\n\nDetails of Databases in VersantDBDirectory"+"\n");

		/* display the details of database*/

		for( i=0;i<dbInfo.length;i++)
		{

			dbn=dbInfo[i].getDBName();
			System.out.println("database: "+dbn);

			did=dbInfo[i].getDBID();
			System.out.println("database ID: "+did);

			dct=dbInfo[i].getDBCreateTime();
			System.out.println("database Create Time: "+dct);

		 	dbown=dbInfo[i].getDBOwner();
			System.out.println("database Owner: "+dbown);

		 	dbtype=dbInfo[i].getDBType();
			System.out.println("database Type: "+dbtype);

		 	dbver=dbInfo[i].getDBVersion();
			System.out.println("database version: "+dbver+"\n");

		}

		/*collect the user details specified database */

		DBUserInfo[] dbInfo1 = DBUtility.getDBUsers(db);

		/* display the details of database users*/

		System.out.println("\n\nDetails of  Users of the database "+db+"\n");

		for( i=0;i<dbInfo1.length;i++)
		{
			user=dbInfo1[i].getUserName();
			System.out.println("database Users: "+user);

		}

		/*The DBEnvInfo class methods provide information about the VERSANT environment*/

		DBEnvInfo dbenvinfo= DBUtility.dbEnvInfo(mach);
		System.out.println("\n\nDetails of  Environment information in "+ mach+"\n");
		String dbidpath=dbenvinfo.getDbidPath();
		System.out.println("database id Path: "+dbidpath+"\n");

		String dbpath=dbenvinfo.getDBPath();
		System.out.println("database Path: "+dbpath+"\n");

		String rootpath=dbenvinfo.getRootPath();
		System.out.println("database Root Path: "+rootpath+"\n");

		String dbrtpath=dbenvinfo.getRuntimePath();
		System.out.println("database RunTime Path: "+dbrtpath+"\n");

		String dbver=dbenvinfo.getVersion();
		System.out.println("database Version: "+dbver +"\n");

		/*To get the mode of the database*/

		String dbmode=DBUtility.namedDBMode(db);
		System.out.println("database Mode: "+dbmode +"\n");
	}
}
