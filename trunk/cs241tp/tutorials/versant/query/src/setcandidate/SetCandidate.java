package setcandidate;


import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;


public class SetCandidate {

  static String database;
  TransSession session;

  
  public SetCandidate() {
  }

  
  public static void main(String  args[]) {

    if (args.length  < 1) {
      System.out.println("Usage  java SetCandidate <<dbname>>");
      System.exit(0);
    }

    database = args[0];

    final String vqls[] = {
        "select selfoid from model.Person where gender = \"male\" ",
        "select selfoid from model.Person where name like \"Tom*\" "
    };


    SetCandidate setcandidate = new SetCandidate  ();

    // initialize  transaction
    setcandidate.initialize();
    // insert data
    setcandidate.insertData();
    // display  data
    setcandidate.displayAll();
    setcandidate.queryCandidate(vqls[0], vqls[1]);
  }

  
  public void initialize() {
    session = new TransSession(database);
  }

  
  public void insertData() {
    final int MAX = 5;
    final String names[] = {"Clinton", "Tom", "Wilson", "Mono", "Tom1", "Jonson"};  	
    final String genders[] = {"male", "female"}; 


    try  {		
      Random rand = new Random();
      
      for (int i=0; i <= MAX ; i++) {
        int	ssn1 		= rand.nextInt(MAX-1) + 1;
        String transtFromDate	= new String((12+i) + "-" + (i+1) + "-" + 2005); 	
        SimpleDateFormat sdf    = new SimpleDateFormat("dd-MM-yyyy");
        Date 	dtFromDate      = sdf.parse(transtFromDate);
        String  street          = new String("Pivot Driv");
        String  city            = new String("San Jose");
        String  state           = new String("California");
        int	zip		= rand.nextInt(MAX+1);
        int	phone		= 1+i; 
        String	email		= new String("mail1@hotmail.com");
        Address address		= new Address(street, city, state, zip, phone, email );	
        PersonalDetails pdtls	= new PersonalDetails(); 		
        Image	image		= new Image();
        Person	person		= new Person(ssn1, names[i], dtFromDate, genders[i%2], address, pdtls, image);
        session.makePersistent(person);
      }	
      session.commit();
    }
    catch (java.text.ParseException e ) {
      e.printStackTrace();
    }
  } 
  

  public void displayAll()  {
    Query query = new Query(session, "select selfoid from model.Person");
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));
    Object[] objects = result.nextAll();
   
    int counter = 0;
  
    while (counter < objects.length ) {
      Person p = (Person) objects[counter] ;
      System.out.println("Person : " + p.getName()+ " ssn " + p.getSSN());
      ++counter;
    }
    
    System.out.println("Total : " + counter + " results.");
    result.close();
  }
  

  public void queryCandidate(String filter1, String filter2)  {
    System.out.println("====Display :" + filter1 + "======");

    Query query1 = new Query(session, filter1);
    QueryResult result = query1.execute();
    Object[] objects = result.nextAll();

    int counter = 0;
    while (counter < objects.length ) {
      Person p = (Person) objects[counter] ;
      System.out.println("Person : " + p.getName()+ " ssn " + p.getSSN());
      ++counter;
    }

    System.out.println("Total : " + counter + " results.");
    System.out.println ( " Setting the candidate collection with filter : " + filter2);

    Query query2 = new Query(session, filter2);
    query2.setCandidateCollection ("model.Person", objects);
    result = query2.execute();
    objects = result.nextAll();
   
    System.out.println("====Display :"+filter2+"======");
    counter = 0;
    while (counter < objects.length) {
      Person p = (Person) objects[counter];
      System.out.println("Person : " + p.getName()+ " ssn " + p.getSSN());
      ++counter;
    }
    
    System.out.println("Total : " + counter + " results.");

    result.close();
    query1.close();
    query2.close();
  }

}
