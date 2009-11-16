package forallexists_value;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;

public class ExistValueDemo {

       	static String database;
	TransSession session;

	public ExistValueDemo() {
	}

	public static void main(String  args[]) {
		
		if ( args.length  < 1 ) {
			System.out.println("Usage  java ExistValueDemo <<dbname>>");
			System.exit(0);
		}

                database = args[0];

		final String vqls[]={
		"select selfoid from model.Address where for all x in phoneNumbers : ( x = 111 )", 
		"select selfoid from model.Address where exists x in phoneNumbers : ( x = 11111 )"
		};

		ExistValueDemo existdemo = new ExistValueDemo  ();

		existdemo.initialize();
		// insert data
		existdemo.insertData();
		// display  data
		existdemo.displayAll();
                existdemo.displayFilterBy(vqls[0]);
                existdemo.displayFilterBy(vqls[1]);
			
	}

	public void  initialize () {
	        session = new TransSession(database);
	}

	public void insertData(){
	
		final	int	MAX		= 5;
		final	String 	names[]		= { "Clinton","Tom","Wilson","Mono","Rock","Jonson"  };  	
		final   String  emails[]        = { "abc@versant.com", "xyz@ver.com", "wilson@versant.com",
							"mono@versant.com","rock@cbdf.com"};
		final   String  emails1[]        = { "email1@cbdf.com","email2@yahoo.com" };
		final	int	phones1[]	= { 111,111,111};
		final	int	phones2[]	= { 11111,2222,333,100800,5555,66666 };

		//   Populate   Data

		Address address	= new Address("turn", "bombay", "California", 1, 123, "");
		address.addEmails(emails);
		address.addPhoneNumbers(phones1);
		session.makePersistent(address);

		Address address1 = new Address("pivot Drive", "San Jose", "California", 1, 123, "");
		address1.addEmails(emails1);
		address1.addPhoneNumbers(phones2);
		session.makePersistent(address1);
		
		session.commit();
		

	} // end of insertdata

	public void displayAll()  {
                Query query 		= new Query(session, "select selfoid from model.Address");
                QueryResult result 	= query.execute(new QueryExecutionOptions().setFetchObjects(true));
		
		Object obj;
		int counter = 0;
		System.out.println("===============Display All ========================");
		if ( result != null ) {
			while ((obj = result.next()) != null) {
                       		System.out.println("retrieved : " + (Address)obj);
				++counter;
			}
		}
		System.out.println("Total : " + counter + " results.\n");
		
		result.close();
	}

	public void displayFilterBy(String filter)  {
               Query query 		= new Query(session, filter);
               QueryResult result 	= query.execute(new QueryExecutionOptions().setFetchObjects(true));
               Object obj;
               int counter = 0;
	       System.out.println("=====Display:"+filter+"=====");
	       while ((obj = result.next()) != null) {
                       Address	address = (Address)obj;
                       System.out.println("retrieved : " + address);
                        ++counter;
               }
               System.out.println("Total : " + counter + " results.\n");

               result.close();

	}

} // end of class
