package setparam;


import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;


public class SetParam {

  static String database;
  TransSession session;

  
  public SetParam() {
  }

  
  public static void main(String  args[]) {

    if (args.length  < 1) {
      System.out.println("Usage  java SetParam <<dbname>>");
      System.exit(0);
    }

    database = args[0];

    SetParam setparam= new SetParam();

    // initialize  PersistenceManager,  transaction
    setparam.initialize();
   
    setparam.insertData();

    setparam.displayAll();
    setparam.qrySetParam();
  }

  
  public void  initialize() {
    session = new TransSession(database);
  }

  
  public void insertData() {

    final int MAX = 5;
    final String names[] = {"Clinton", "Tom", "Wilson", "Mono", "Rock", "Jonson"};  	
    final String genders[] = {"male", "female"}; 

    try  {		
      Random rand = new Random();
      for (int i=0; i <= MAX ; i++) {
        int ssn1 = rand.nextInt(MAX+1);
        int zip = 1+i;
        int phone = 1+i; 
        String transtFromDate = new String((12+i) + "-" + (i+1) + "-" + 2005); 	
        SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy");
        Date dtFromDate       = sdf.parse(transtFromDate);
        String street         = new String("Pivot Driv");
        String city           = new String("San Jose");
        String state          = new String("California");
        String email          = new String("mail1@hotmail.com");
        Address address       = new Address(street,city, state, zip, phone, email );	
        PersonalDetails pdtls = new PersonalDetails(); 		
        Image image           = new Image();
        Person person         = new Person(ssn1, names[i], dtFromDate, 
                                           genders[i%2], address, pdtls, image);
        session.makePersistent(person);
      }	
      session.commit();
    }
    catch  (java.text.ParseException e) {
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

  
  public void qrySetParam()  {
    String filter = "select selfoid from model.Person where ssn + $ssn1Param > $ssn2Param and name like $nameParam"; 
    Query query = new Query(session, filter);
    query.bind("ssn1Param", new Integer(1));
    query.bind("ssn2Param", new Integer(2));
    query.bind("nameParam", "Tom"); 
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));
    
    Object obj;
    int counter = 0;
    System.out.println("==Display:" + filter + "===");
    
    while ((obj = result.next()) != null) {
      Person p = (Person)obj;
      System.out.println("retrieved : " + p.getName()+ " ssn " + p.getSSN());
      ++counter;
    }
    
    System.out.println("Total : " + counter + " results.\n");
    result.close();
  }
} 
