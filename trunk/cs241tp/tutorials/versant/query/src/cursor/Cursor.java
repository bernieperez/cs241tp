
package cursor;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;

public class Cursor {

  static String database;
  TransSession session;

  public Cursor ( ) {
  }

  public static void main(String  args [ ]) {

    if (args.length  < 1) {
      System.out.println( "Usage  java Cursor <<dbname>>" );
      System.exit(0);
    }

    database = args[0];

    final String  vqls[] = {
        "select selfoid from model.Person order by ssn ASC",
        "select selfoid from model.Person order by ssn DESC",
    };

    Cursor cursor = new Cursor();

    // initialize  PersistenceManager,  transaction
    cursor.initialize();
    cursor.insertData();
    cursor.displayAll();

    cursor.displayFilterBy(vqls[0]);
    cursor.displayFilterBy(vqls[1]);
  }
  
  
  public void  initialize() {
    session = new TransSession(database);
  }

  
  public void insertData() {
    final int MAX = 50;
    final String names[] = {"Clinton", "Tom", "Wilson", "Mono", "Tom", "Jonson", "Nil",
        "Atul", "Pawan", "Sun"};  	
    final String genders[] = {"male","female"}; 

    try  {		
      Random rand = new Random();
      for (int i=0; i < MAX; i++) {
        int	ssn1 		= i + 1;
        String transtFromDate	= new String(((12+i) % 30) + "-" + ((i+1) % 12) + "-" + 2005); 	
        SimpleDateFormat sdf    = new SimpleDateFormat( "dd-MM-yyyy" );
        Date 	dtFromDate      = sdf.parse(transtFromDate );
        String  street          = new String("Pivot Driv");
        String  city            = new String("San Jose");
        String  state           = new String("California");
        int	zip		= rand.nextInt(MAX + 1);
        int	phone		= 1 + i; 
        String	email		= new String("mail" + i + "@hotmail.com" );
        Address address		= new Address(street, city, state, zip, phone, email );	
        PersonalDetails pdtls	= new PersonalDetails(); 		
        Image	image		= new Image();
        Person	person		= new Person(ssn1, "Person" + i, dtFromDate,
                                             genders[i%2], address, pdtls, image);
        session.makePersistent(person);
      }	
      session.commit ( );

    }
    catch(java.text.ParseException e ) {
      e.printStackTrace();
    }

  }
  

  public void displayAll()  {
    Query query = new Query(session, "select selfoid from model.Person");
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));

    Object obj;
    int counter = 0;
    while ((obj = result.next()) != null) {
      System.out.println("retrived : " + (Person)obj);
      ++counter;
    }
    System.out.println("Total : " + counter + " results.");

    result.close();
    query.close();		
  }

  
  public void displayFilterBy(String filter)  {
    Query query = new Query(session, filter);
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));
    
    int counter = 0;
    System.out.println( "====Display :" + filter + "======" );
    
    Object obj;
    while ((obj = result.next()) != null)
      ++counter;
    
    System.out.println("Total Persons : " + counter);

    // set for fetch 10 times objects blocks
    query.setFetchSize(10);
    result = query.execute(new QueryExecutionOptions().setFetchObjects(true));

    // fetch 5 objects  
    System.out.println ("========== Fetch first 5 ============");
    Object[] objs = result.next(5);

    Person p;
    for (int i = 0 ; i < objs.length; i ++) {
      p = (Person) objs[i];
      System.out.println(" retrived : Name :  " + p.getName() + " ssn " + p.getSSN());
    }

    // get one record .
    System.out.println("===========fetch next single record============= " );
    obj = result.next ();
    p = (Person) obj ;
    System.out.println(" retrived : Name :  " + p.getName() + " ssn " + p.getSSN());

    // check query result empty
    System.out.println(" check query result empty :" + result.isEmpty( ) );

    // get all remaining records 
    System.out.println("============ fetch remaining records =============");
    objs = result.nextAll();

    for (int i = 0 ; i < objs.length ;  i ++) {
      p = (Person) objs[i];
      System.out.println(" retrived : Name :  " + p.getName() + " ssn " + p.getSSN());
    }

    result.close ();
    // close all QueryResult instances created by this Query
    query.close ();
  }

}
