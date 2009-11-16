/*
 * Created on Oct 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author denelson
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.util.*;
import java.sql.*;
import com.intersys.objects.*;
import com.intersys.classes.*;
import JavaTutorial.Contact;
import JavaTutorial.PhoneNumber;

public class BindingExamples {

	public static Database createConnection() throws CacheException {
		String url="jdbc:Cache://localhost:1972/USER";
		String username="_SYSTEM";
		String pwd="SYS";
		Database db = CacheDatabase.getDatabase(url, username, pwd);
		//Contact contact = new Contact(db);
		return db;
	}


	public static PhoneNumber createPhoneNumber(Database db, String number, String type) throws CacheException{

		PhoneNumber pn = new PhoneNumber(db);
		pn.setNumber(number);
		pn.setPhoneNumberType(type);
		return pn;

	}

	public static void addNumberToContact(Database db, String number, String type, Contact contact) throws CacheException {

		PhoneNumber phoneNumber = new PhoneNumber(db);
		phoneNumber.setNumber(number);
		phoneNumber.setPhoneNumberType(type);
		phoneNumber.setContact(contact);
		phoneNumber.save();

	}

	public static void displayNumbers(int id, Database db) throws CacheException{
		Contact contact=(Contact)(Contact._open(db,new Id(id)));
		Map phoneNumbers=(Map)contact.getPhoneNumbers();
		Iterator iter=phoneNumbers.keySet().iterator();
		while(iter.hasNext()){
			PhoneNumber pn = (PhoneNumber)(phoneNumbers.get(iter.next()));

			System.out.println("Type: " +
					pn.getPhoneNumberType() + " Number: " +
													pn.getNumber());
		}
	}

	public static void displayContacts(Database db) throws CacheException{

		List listOfContacts = (List)(Contact.ListOfContacts(db));

		Iterator iter = listOfContacts.iterator();

		while(iter.hasNext()){

			String id = (String) (iter.next());
			Contact contact = (Contact)(Contact._open(db, new Id(id)));
			System.out.println ("Name " + contact.getName() + " ID " + id);


		}

	}

	public static void displayContactsByType(Database db, String type) throws CacheException, SQLException{

		CacheQuery query = Contact.query_RetrieveByContactType(db);
		java.sql.ResultSet rs=query.execute(type);
		System.out.println(type + " Contacts");
		while (rs.next()){
			System.out.println(rs.getString(1));

		}
	}

	public static void displayPhoneNumbersByType(Database db, String id, String type) throws CacheException, SQLException {

		Id ID= new Id(id);
		String SQL = "SELECT Number FROM JavaTutorial.PhoneNumber" +
		 " WHERE Contact='"+id +"' AND PhoneNumberType='" + type +"'";
		CacheQuery query = new CacheQuery(db, SQL);
		java.sql.ResultSet rs= query.execute();
		while (rs.next()){

			System.out.println(rs.getString(1));
		}
	}

	public static void displayPhoneNumbersByTypeOBQ(Database db, String id, String type) throws CacheException{

		Object[] args = {id, type};
		String sql = "SELECT JavaTutorial.PhoneNumber.%ID FROM " +
		"JavaTutorial.PhoneNumber WHERE Contact = ? AND PhoneNumberType = ?";
		Iterator iter=db.openByQuery(sql, args);

		while (iter.hasNext()){

			PhoneNumber pn=(PhoneNumber)iter.next();
			System.out.println("Type: " + pn.getPhoneNumberType() + " Number: " + pn.getNumber());
		}


	}
	public static void main (String args[]){

		try{
			System.out.println("Testing Create Connection");
			Database db=BindingExamples.createConnection();

			//Removes existing Contact and PhoneNumber instances from database.
			Contact.sys_KillExtent(db);
			PhoneNumber.sys_KillExtent(db);


			//Populates the Contact and PhoneNumber tables in the database
			System.out.println("Populating Contact");
			Contact.Populate(db,new Integer(5), new Integer(1));
			System.out.println("Populating PhoneNumber");
			PhoneNumber.Populate(db,new Integer(15), new Integer(1));


			System.out.println("Testing Display Numbers");
			BindingExamples.displayNumbers(1,db);
			System.out.println("Testing Display Contacts");
			BindingExamples.displayContacts(db);
			System.out.println("Testing Create New Phone Number");

			Contact contact = (Contact)(Contact._open(db, new Id(1)));

			BindingExamples.addNumberToContact(db,"111-222-3333", "Business", contact);

			System.out.println("Testing displayContactsByType");

			BindingExamples.displayContactsByType(db, "Business");

			System.out.println("Testing displayContactsByType Again");

			BindingExamples.displayContactsByType(db, "Personal");

			System.out.println("Testing displayPhoneNumbersByType");

			BindingExamples.displayPhoneNumbersByType(db, "1" , "Business");

			System.out.println("Testing displayPhoneNumbersByType Again");

			BindingExamples.displayPhoneNumbersByType(db, "3" , "Fax");

			System.out.println("Testing displayPhoneNumbersByTypeOBQ");

			BindingExamples.displayPhoneNumbersByTypeOBQ(db, "3", "Business");

		}

		catch (CacheException e){System.out.println(e.getMessage());}
		catch(SQLException e){System.out.println(e.getMessage());}
	}
}








