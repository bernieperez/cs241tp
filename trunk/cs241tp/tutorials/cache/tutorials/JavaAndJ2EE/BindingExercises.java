/* This class  contains solutions to the exercises contained in the Java Binding section of
the Caché with Java and J2EE QuickStart Tutorial.

Preconditions: All methods assume that the JavaTutorial.Contact and JavaTutorial.PhoneNumber
classes have been compiled in the User namespace.
PostCondition: The methods will create data in the namespace.

*/
import java.util.*;
import java.sql.*;
import com.intersys.objects.*;
import com.intersys.classes.*;
import JavaTutorial.Contact;
import JavaTutorial.PhoneNumber;

public class BindingExercises {

	public static Database createConnection() throws CacheException {
		String url="jdbc:Cache://localhost:1972/USER";
		String username="_SYSTEM";
		String pwd="SYS";
		Database db = CacheDatabase.getDatabase(url, username, pwd);
		return db;
	}

	/*  Stub for exercise 1. Method creates new Contact instance and persists it to the database,
	Displays instance's object id if the persistence is successful.
	*/

	public static void createContact(Database db, String name, String type) throws CacheException{
		System.out.println("Method Not Yet Implemented");


	}

	/* Stub for exercise 2. Method displays values of Number and PhoneNumberType for all PhoneNumber instances
	associated with the specified Contact Instance
	*/

	public static void displayPhoneNumbers(Database db, Contact contact) throws CacheException, SQLException{
		System.out.println("Method Not Yet Implemented");


	}

	/* Stub for exercise 3. Method removes all PhoneNumber instances with the specified value for PhoneNumberType
	from the Contact instance.
	*/

	public static void removePhoneNumbers(Database db, Contact contact, String type) throws CacheException{

		System.out.println("Method Not Yet Implemented");

	}
	/* Main method initializes database by first deleting all Contact and PhoneNumber instances and then repopulating both fields.
	Method then tests solutions for exercises 1, 2, and 3.
	*/
	public static void main (String args[]){

		try{
			Database db = BindingExercisesSolutions.createConnection();

			//Removes existing Contact and PhoneNumber instances from database.
			Contact.sys_KillExtent(db);
			PhoneNumber.sys_KillExtent(db);

			//Populates the Contact and PhoneNumber tables in the database
			Contact.Populate(db,new Integer(5), new Integer(1));
			PhoneNumber.Populate(db,new Integer(15), new Integer(1));

			//Creates a contact. Tests createContact().
			/*Note student should try executing the method using an invalid ContactType -
			a value other than "Business" or "Personal"
			*/
			BindingExercises.createContact(db, "Public, John Q.", "Business");

			//Opens existing Contact to use for the following methods
			Contact contact=(Contact)(Contact._open(db,new Id(1)));

			System.out.println("Before");
			//Displays phone numbers for Contact instance - tests displayPhoneNumbers()
			BindingExercises.displayPhoneNumbers(db, contact);

			//Removes phone numbers for Contact instance - tests removePhoneNumbers()
			BindingExercises.removePhoneNumbers(db,contact,"Fax");
			System.out.println("After");

			//Redisplays phonenumbers for Contact instance  - tests removePhoneNumbers
			BindingExercises.displayPhoneNumbers(db, contact);

			//Closes connection to database.
			db.close();


		}

		catch (CacheException e){System.out.println(e.getMessage());}
		catch(SQLException e){System.out.println(e.getMessage());}

	}

}