package set_operators;


import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;


public class SetDemo {

  static String database;
  TransSession session;

  public SetDemo() {
  }

  public static void main(String args[]) {

    if (args.length  < 1) {
      System.out.println("Usage  java SetDemo <<dbname>>");
      System.exit(0);
    }

    database = args[0];
    
    final String vqls[]={
        "select selfoid from model.Person where ssn intersect {5, 6, 8}",
        "select selfoid from model.Person where (model.Address)address->phoneNumbers intersect {1002, 1000}",
        "select selfoid from model.Person where name intersect {\"Tom4\", \"Tom6\", \"Tom7\" }",
        "select selfoid from model.Person where {1002, 1001} intersect (model.Address)address->phoneNumbers",
        "select selfoid from model.Person where {\"mail1@versant.com\"} intersect (model.Address)address->emails",
        "select selfoid from model.Person where {1002, 1001} superset_of (model.Address)address->phoneNumbers",
        "select selfoid from model.Person where {1002} subset_of (model.Address)address->phoneNumbers",
        "select selfoid from model.Person where {1002, 1001} equivalent_to (model.Address)address->phoneNumbers",
        "select selfoid from model.Person where {1002, 1001} not_intersect (model.Address)address->phoneNumbers"
    };

    SetDemo set	= new SetDemo();

    // initialize  PersistenceManager,  transaction
    set.initialize();
    // insert data
    set.insertData();
    // display  data
    set.displayAll();
    set.displayFilterBy(vqls[0]);
    set.displayFilterBy(vqls[1]);
    //set.displayFilterBy(vqls[2]);
    set.displayFilterBy(vqls[3]);
    //set.displayFilterBy(vqls[4]);
    set.displayFilterBy(vqls[5]);
    //set.displayFilterBy(vqls[6]);
    set.displayFilterBy(vqls[7]);
    set.displayFilterBy(vqls[8]);
  }

  
  public void  initialize() {
    session = new TransSession(database);
  }

  
  public void insertData() {

    final int MAX = 5;
    final String names[] = { "Clinton", "Tom", "Wilson", "Tom4", "Tom6", "Tom7"};  	
    final String genders[] = {"male", "female"}; 
    final int phones[] = {111, 222, 333};
    final int phones1[]	= {1000, 1002, 333};
    final int phones2[]	= {1001, 1002, 333};
    final int phones3[] =  {1002, 1001};
    final String emails1[] = {"abc@versant.com", "xyz@ver.com"};
    final String emails2[] = {"wilson@versant.com", "sun@versant.com"};
    final String emails3[] = {"mail1@versant.com", "rock@cbdf.com"};
    final String emails4[] = {"mail1@yahoo.com", "mail1@cbdf.com"};

    //   Populate   Data
    try  {		
      int     ssn1            = 5;
      String transtFromDate   = new  String(12 + "-" + 1 + "-" + 2005);
      SimpleDateFormat sdf    = new SimpleDateFormat("dd-MM-yyyy");
      Date    dtFromDate      = sdf.parse(transtFromDate);
      String  street          = new String("Pivot Driv");
      String  city            = new String("San Jose");
      String  state           = new String("California");
      int     zip             = 1;
      int     phone           = 1;
      String  email           = new String("mail1@hotmail.com");
      Address address         = new Address(street,city, state, zip, phone, email );
      PersonalDetails pdtls   = new PersonalDetails();
      Image   image           = new Image();
      address.addEmails(emails1);
      address.addPhoneNumbers(phones);
      Person  person          = new Person(ssn1, names[0], dtFromDate, genders[0], address, pdtls, image);
      session.makePersistent(person);

      ssn1 = 6;
      Address address1        = new Address(street, city, state, zip, phone, email );
      address1.addPhoneNumbers(phones1);
      address1.addEmails(emails2);
      Person  person1          = new Person(ssn1, names[1], dtFromDate, genders[0], address1, pdtls, image);
      session.makePersistent(person1);

      ssn1 = 7;
      Address address2        = new Address(street, city, state, zip, phone, email );
      address2.addPhoneNumbers(phones2);
      address2.addEmails(emails3);
      Person  person2         = new Person(ssn1, names[2], dtFromDate, genders[1], address2, pdtls, image);
      session.makePersistent(person2);

      ssn1 = 8;
      Address address3        = new Address(street, city, state, zip, phone, email );
      address3.addPhoneNumbers(phones3);
      address2.addEmails(emails4);
      Person  person3         = new Person(ssn1, names[2], dtFromDate, genders[1], address3, pdtls, image);
      session.makePersistent(person3);
      session.commit();
    }
    catch  (java.text.ParseException e) {
      e.printStackTrace();
    }
  }
  

  public void displayAll()  {
    Query query = new Query(session, "select selfoid from model.Person");
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));

    int counter = 0;
    System.out.println("===============Display All ========================");

    if (result != null) {
      Object obj;
      while ((obj = result.next()) != null) {
        System.out.println("retrived : " + (Person)obj);
        ++counter;
      }
    }
    
    System.out.println("Total : " + counter + " results.");
    result.close();
  }

  
  public void displayFilterBy(String filter)  {
    System.out.println("===Query :" + filter + "===");
    Query query = new Query(session, filter);
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));
  
    Object obj;
    int counter = 0;
    
    while ((obj = result.next()) != null) {
      Person person = (Person)obj;
      System.out.println("Person: " + person);
      ++counter;
    }
    
    System.out.println("Total : " + counter + " results.");
    result.close();
  }
}
