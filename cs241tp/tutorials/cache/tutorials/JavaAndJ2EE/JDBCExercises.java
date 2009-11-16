/* This class contains the stub methods for the exercises in the JDBC section
of the Caché with Java and J2EE QuickStart Tutorial.
PreConditions: Prior to executing the main method, the classes JavaTutorial.Contact
and JavaTutorial.PhoneNumber must be compiled into the User namespace. Note also
that the main method uses Java projections of Contact and PhoneNumber to initialize
the database. These classes must be available to this class.
PostConditions: Executing the main method changes the state of the database. Existing
data is wiped out, new data is added.

*/

import java.util.*;
import java.sql.*;
import com.intersys.objects.*;
import JavaTutorial.*;


public class JDBCExercises{


	public static Database getDatabase() throws CacheException{
		String url="jdbc:Cache://localhost:1972/USER";
		String username="_SYSTEM";
		String pwd="SYS";
		Database db = CacheDatabase.getDatabase(url, username, pwd);
		return db;
	}

	/* Stub for Exercise 1. Inserts a new Contact into JavaTutorial.Contact.
	*/
	public static void insertContact(Database db, String name, String type ) throws CacheException, SQLException{

		System.out.println("Method not yet implemented");

	}
	/* Solution for Exercise 2. Retrieves phone numbers of a specific type for a contact with a specific name. Uses the "->" implicit join syntax.
	*/
	public static void displayPhoneNumbersByTypeAndName(Database db, String name, String type) throws CacheException, SQLException{

		System.out.println("Method not yet implemented");
	}
	/* Solution for Exercise 3.  Retrieves the object ids for all the PhoneNumber instances associated with the Contact with the specified name. Uses the stored procedure syntax.
	*/
	public static void displayPhoneNumberIdsByName(Database db, String name) throws CacheException, SQLException{

		System.out.println("Method not yet implemented");
	}
	/*Main method. Initializes database by removing all Contact and PhoneNumber instances and then repopulating them. Uses Java Binding syntax for this operation. Then
	tests the different exercise solutions.
	*/

	public static void main(String args[]){

		try {
			Database db = JDBCExerciseSolutions.getDatabase();

			//Removes existing Contact and PhoneNumber instances from database.
			Contact.sys_KillExtent(db);
			PhoneNumber.sys_KillExtent(db);

			//Populates the Contact and PhoneNumber tables in the database
			Contact.Populate(db,new Integer(5), new Integer(1));
			PhoneNumber.Populate(db,new Integer(15), new Integer(1));
			Contact contact=(Contact)(Contact._open(db,new Id(1)));

			//Tests Exercise 1 solution.
			JDBCExercises.insertContact(db, "Public,John Q.", "Business");
			//Tests Exercise 2 solution.
			JDBCExercises.displayPhoneNumbersByTypeAndName(db, contact.getName(), "Home");
			//Tests Exercise 3 solution.
			System.out.println("Object Ids for PhoneNumbers contained in: " + contact.getName());
			JDBCExercises.displayPhoneNumberIdsByName(db, contact.getName());

		}

		catch (CacheException e) {System.out.println(e.getMessage());}
		catch (SQLException e) {System.out.println(e.getMessage());}


	}

}