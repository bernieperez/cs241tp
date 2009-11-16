package orderby;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;

public class Orderby {

  static String database;
  TransSession session;

  public Orderby() {
  }

  public static void main(String  args[]) {

    if (args.length < 1) {
      System.out.println("Usage  java Orderby <<dbname>>");
      System.exit(0);
    }

    database = args[0];

    final  String  vqls[] = {
        "select selfoid from model.Person order by ssn ASC",
        "select selfoid from model.Person order by ssn * 3 + (model.Address)address->zip DESC",
        "select selfoid from model.Person order by ssn ASC , name DESC",
        "select selfoid from model.Person order by (ssn + 10) DESC ,(dateOfBirth * 2) ASC"         
    };

    Orderby orderby = new Orderby();

    orderby.initialize();
    orderby.insertData();
    orderby.displayAll();
    orderby.displayFilterBy(vqls[0]);
    orderby.displayFilterBy(vqls[1]); 
    orderby.displayFilterBy(vqls[2]);
    orderby.displayFilterBy(vqls[3]);
  }

  public void  initialize () {
    session = new TransSession(database);
  }

  public void insertData() {
    final int MAX = 5;
    final String names[] = {"Clinton", "Tom", "Wilson", "Mono", "Tom", "Jonson"};  	
    final String genders[] = {"male", "female"}; 

    try  {		
      Random  rand = new Random();
      for (int i = 0;  i <= MAX ; i++ ) {
        int	ssn1 		= rand.nextInt(MAX - 1) + 1;
        String transtFromDate	= new  String((12 + i) + "-" + (i + 1) + "-" + 2005); 	
        SimpleDateFormat sdf    = new SimpleDateFormat("dd-MM-yyyy");
        Date 	dtFromDate      = sdf.parse(transtFromDate);
        String  street          = new String("Pivot Driv");
        String  city            = new String("San Jose");
        String  state           = new String("California");
        int	zip		= rand.nextInt(MAX + 1);
        int	phone		= 1 + i; 
        String	email		= new String("mail1@hotmail.com");
        Address address		= new Address(street, city, state, zip, phone, email );	
        PersonalDetails pdtls	= new PersonalDetails(); 		
        Image	image		= new Image();
        Person	person		= new Person(ssn1, names[i], dtFromDate, 
                                             genders[i%2], address, pdtls, image);
        session.makePersistent(person);
      }	
      session.commit();

    }
    catch  (java.text.ParseException e ) {
      e.printStackTrace();
    }

  } 

  public void displayAll()  {
    Query query = new Query(session, "select selfoid from model.Person");
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));

    Object obj;
    int counter = 0;
    while ((obj = result.next()) != null) {
      System.out.println("retrieved : " + (Person)obj);
      ++counter;
    }
    
    System.out.println("Total : " + counter + " results.\n");
    result.close();
  }

  public void displayFilterBy(String filter)  {
    Query query = new Query(session, filter);
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));
    Object obj;
    int counter = 0;

    while ((obj = result.next()) != null) {
      Person p = (Person)obj;
      System.out.println("retrieved : " + p.getName()+ " ssn " + p.getSSN());
      ++counter;
    }
    System.out.println("Total : " + counter + " results.\n");

    result.close();
  }
} 
