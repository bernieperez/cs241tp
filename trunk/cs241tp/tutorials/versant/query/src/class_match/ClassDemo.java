package class_match;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;

public class ClassDemo {

  static String database;
  TransSession session;

  public ClassDemo() {
  }

  public static void main(String  args[]) {

    if (args.length  < 1) {
      System.out.println("Usage: java Main <<dbname>>");
      System.exit(0);
    }

    database = args[0];
    final String vqls[] = {
        "select selfoid from model.Singer where bestFriend isa model.Person",
        "select selfoid from model.Singer where bestFriend isa model.Singer",
        "select selfoid from model.Singer where bestFriend not_isa model.Singer",
    };

    ClassDemo cls = new ClassDemo();

    // initialize  PersistenceManager,  transaction
    cls.initialize();
    cls.insertData();
    cls.displayAll();
    cls.displayFilterBy(vqls[0]);
    cls.displayFilterBy(vqls[1]);
    cls.displayFilterBy(vqls[2]);
  }


  public void  initialize () {
    session = new TransSession(database);
  }


  public void insertData() {
    final int MAX = 5;
    final String names[] = {"Clinton", "Tom", "Wilson", "Mono", "Rock", "Jonson"};  	
    final String names1[] = {"Nilesh", "Santosh", "Mick", "Sunil", "Vicky", "Sunny"};  	
    final String genders[]= {"male", "female"}; 
    final String titles[] = {"CD1", "CD2", "CD3", "CD4", "CD5", "CD6"};

    //   Populate   Data
    try  {		
      Random rand = new Random();
      for (int i=0; i <= MAX ; i++) {
        String id = new String("" + (i+1));
        String title = titles[i];
        int price = rand.nextInt(1000);
        Image image = new Image();
        String transtFromDate = new String((12+i) + "-" + (i+1) + "-" + 2005);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dtFromDate = sdf.parse(transtFromDate);
        Singer singer = new Singer(i+1 ,names[i], dtFromDate, genders[i%2]);
        CD cd = new CD(id, title, price, image);

        singer.addCD(cd);
        cd.setSinger(singer);
        Singer singer1 = new Singer(i+10, names1[i%MAX], dtFromDate, genders[i%2]);

        if (i == 2) {
          Person person = new Person(i+100, names1[2], dtFromDate, genders[i%2]);
          singer.setBestFriend(person);	
        }
        else
          singer.setBestFriend(singer1);
        
        session.makePersistent(singer);
      }	
      session.commit();

    }
    catch (java.text.ParseException pe ) {
      pe.printStackTrace();
    }

  } 
  

  public void displayAll()  {
    System.out.println("===============Display All ========================");
    
    Query query = new Query(session, "select selfoid from model.Singer");
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));
    
    int counter = 0;
    
    if (result != null) {
      Object obj;
      while ((obj = result.next()) != null) {
        System.out.println("retrieved : " + (Singer)obj);
        ++counter;
      }
    }
    
    result.close();
    System.out.println("Total : " + counter + " results.\n");   
  }

  
  public void displayFilterBy(String filter)  {
    Query query = new Query(session, filter);
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));
  
    System.out.println("======Display :"+ filter +"=====");
    
    int counter = 0;
    
    if (result != null) {
      Object obj;
      while ((obj = result.next()) != null) {
        System.out.println("retrieved : " + (Singer)obj);
        ++counter;
      }
    }

    result.close();
    System.out.println("Total : " + counter + " results.\n");
  }
} 
