package in_expression;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;


public class CheckInDemo {

  static String database;
  TransSession session;

  public CheckInDemo() {
  }

  public static void main(String  args[]) {

    if (args.length  < 1) {
      System.out.println("Usage  java CheckInDemo <<dbname>>");
      System.exit(0);
    }

    database = args[0];

    final String vqls[]={
        "select selfoid from model.Person where ssn in {5, 6, 8}",
        "select selfoid from model.Person where name in {\"Tom4\", \"Tom6\", \"Tom7\" }",
        "select selfoid from model.Person where 1002 in (model.Address)address->phoneNumbers",
        "select selfoid from model.Person where \"mail1@versant.com\" in (model.Address)address->emails",
        "select selfoid from model.Person",
    };

    CheckInDemo	checkin	= new CheckInDemo();

    // initialize  PersistenceManager,  transaction
    checkin.initialize();

    checkin.insertData();
    
    System.out.println("Display All:");
    checkin.displayFilterBy(vqls[4]);
    
    System.out.println("\nQuery :" + vqls[0]);
    checkin.displayFilterBy(vqls[0]);
 
    
    System.out.println("\nQuery: " + vqls[4]);
    checkin.displayFilterBy(vqls[4]);
  }

  
  public void  initialize () {
    session = new TransSession(database);
  }

  
  public void insertData() {
    final String names[] = {"Clinton", "Tom", "Wilson", "Tom4", "Tom6", "Tom7"};  	
    final String genders[] = {"male", "female"}; 
    final String titles[] = {"CD1", "CD2", "CD3", "CD4", "CD5", "CD6"};
    final String emails1[] = {"abc@versant.com", "xyz@ver.com"};
    final String emails2[] = {"wilson@versant.com", "sun@versant.com"};
    final String emails3[] = {"mail1@versant.com", "rock@cbdf.com"};
    final int MAX = 5;
    final int phones[] = {111,222,333};
    final int phones1[] = {1000,1002,333};
    final int phones2[] = {1001,1002,333};

    try  {		
      Random rand = new Random();
      String transtFromDate = new  String(12 + "-" + 1 + "-" + 2005);
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      Date dtFromDate = sdf.parse(transtFromDate);

      int ssn1 = 5;
      Address address = new Address("turn", "bombay", "California", 1, 123, "");
      address.addEmails(emails1);
      address.addPhoneNumbers(phones);
      PersonalDetails pdtls = new PersonalDetails();
      Image image = new Image();
      Person person = new Person(ssn1, names[3], dtFromDate, genders[0], address, pdtls, image);
      session.makePersistent(person);

      ssn1 = 6;
      Address address1 = new Address("turn", "bombay", "California", 1, 123, "");
      address1.addEmails(emails2);
      address1.addPhoneNumbers(phones1);
      Person person1 = new Person(ssn1, names[4], dtFromDate, genders[1], address1, pdtls, image);
      session.makePersistent(person1);

      ssn1 = 7;
      Address address2 = new Address("turn", "bombay", "California", 1, 123, "");
      address2.addEmails(emails3);
      address2.addPhoneNumbers(phones2);
      Person person2 = new Person(ssn1, names[5], dtFromDate, genders[1], address2, pdtls, image);
      session.makePersistent(person2);

      session.commit();
    }
    catch  (java.text.ParseException e) {
      e.printStackTrace();
    }
  } 
  

  public void displayFilterBy(String filter)  {
    Query query = new Query(session, filter);
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));

    int counter = 0;
    if (result != null) {
      Object obj;
      String deviderLine = generateDeviderLine(filter.length());
      System.out.println(deviderLine);
      while ((obj = result.next()) != null) {
        Person person = (Person)obj;
        ++counter;
        System.out.println(person);
        System.out.println(deviderLine);
      }
    }

    System.out.println("Total : " + counter + " results.");
    result.close();
  }
  
  
  private String generateDeviderLine(int length) {
    String dashedLine = "";
    for (int i=0; i < length+7; i++) 
      dashedLine += "-";
    
    return dashedLine;
  }

} 
