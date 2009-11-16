package string_match;


import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;


public class StringMatch {

  static String database;
  TransSession session;

  
  public StringMatch() {
  }

  
  public static void main(String  args[]) {
    if (args.length  < 1) {
      System.out.println("Usage  java StringMatch <<dbname>>");
      System.exit(0);
    }

    database = args[0];

    final String vqls[] = {
        "select selfoid from model.Person where name like \"Clinton*\"",
        "select selfoid from model.Person where name not_like \"Clinton0\"",
        "select selfoid from model.Person where gender = \"male\"",
        "select selfoid from model.Person where gender != \"male\"",
        "select selfoid from model.Person where name >=  \"Clinton4\"",
        "select selfoid from model.Person where name = \"Clinton\" and (model.Person)bestFriend->name= \"Tom\"",
        "select selfoid from model.Person where tolower(name) like \"tom?\"",
        "select selfoid from model.Person where toupper(name) like \"TOM*\"" 
    };

    StringMatch stringmatch = new StringMatch();

    // initialize  PersistenceManager,  transaction
    stringmatch.initialize();
    // insert data
    stringmatch.insertData();
    // display  data
    stringmatch.displayAll();
    stringmatch.displayFilterBy(vqls[0]);
    stringmatch.displayFilterBy(vqls[1]);
    stringmatch.displayFilterBy(vqls[2]);
    stringmatch.displayFilterBy(vqls[3]);
    stringmatch.displayFilterBy(vqls[4]);
    stringmatch.displayFilterBy(vqls[5]);
    stringmatch.displayFilterBy(vqls[6]);
    stringmatch.displayFilterBy(vqls[7]);
  }

  
  public void initialize() {
    session = new TransSession(database);
  }

  
  public void insertData() {
    final int MAX = 10;
    final String names[] = {"Tom", "Clinton", "Wilson", "Mono", "Rock", "Jonson",
                            "tom", "Clinton4", "Clinton0", "Tom1", "tom2"};  	
    final String genders[] = {"male","female"}; 
    Person tom = null;

    //   Populate   Data
    try  {		
      Random rand = new Random();
      for (int i=0; i <= MAX; i++) {
        int	ssn1 		= rand.nextInt(MAX+1);
        String transtFromDate	= new  String((12+i) + "-" + (i+1) + "-" + 2005); 	
        SimpleDateFormat sdf    = new SimpleDateFormat("dd-MM-yyyy");
        Date 	dtFromDate      = sdf.parse(transtFromDate);
        String  street		= new String("pivot Drive");
        String	city		= new String("San Jose");
        String	state		= new String("California");
        int	zip		= 1+i;
        int	phone		= 1+i; 
        String	email		= new String("mail1@hotmail.com");
        Address address		= new Address(street, city, state, zip, phone, email );	
        PersonalDetails pdtls	= new PersonalDetails(); 		
        Image	image		= new Image();
        Person	person		= new Person(ssn1, names[i], dtFromDate, genders[i%2], address, pdtls, image);
        
        if (names[i].equals("Tom"))
          tom = person;
        
        if (names[i].equals("Clinton")) 
          person.setBestFriend(tom);
        
        session.makePersistent(person);
      }	
      session.commit();
    }
    catch (java.text.ParseException e) {
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
  

  public void displayFilterBy(String filter) {
    Query query = new Query(session, filter);
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));
    
    Object obj;
    int counter = 0;
    System.out.println("====Display:" +  filter + "=====");
    while ((obj = result.next()) != null) {
      Person p = (Person)obj;
      System.out.println("retrieved : " + p.getName()+ " ssn " + p.getSSN());
      ++counter;
    }
    System.out.println("Total : " + counter + " results.\n");
    result.close();
  }
} 
