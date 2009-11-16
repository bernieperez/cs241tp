package numeric_expression;


import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;


public class NumericDemo {

  static String database;
  TransSession session;

  public NumericDemo() {
  }

  public static void main(String  args[]) {

    if (args.length  < 1) {
      System.out.println("Usage  java NumericDemo <<dbname>>");
      System.exit(0);
    }

    database = args[0];

    final String vqls[]={
        "select selfoid from model.CD where copiesSold - 100 > copiesSupplied + 500",
        "select selfoid from model.CD where (copiesSold * 2) <=  (copiesSupplied / 2)",
        "select selfoid from model.CD where copiesSold % 5 = 0",
        "select selfoid from model.CD where ((copiesSold + 3) * 2 - (copiesSold / 5 )) < (copiesSupplied + 1000)/2",
        "select selfoid from model.CD where 2 * (model.Singer)singer->ssn > 1000 + (model.Singer)singer->ssn",
        "select selfoid from model.CD where !  (model.Singer)singer->ssn  > 1000 ",
        "select selfoid from model.CD where ((model.Singer)singer->ssn > abs(-1000) )",
        "select selfoid from model.CD where  - (model.Singer)singer->ssn > -1000 ",
        "select selfoid from model.CD where copiesSupplied >  5000"
    };

    NumericDemo numeric = new NumericDemo  ();

    // initialize  PersistenceManager,  transaction
    numeric.initialize();
    // insert data
    numeric.insertData();
    // display  data
    numeric.displayAll();
    numeric.displayFilterBy(vqls[0]);
    numeric.displayFilterBy(vqls[1]);
    numeric.displayFilterBy(vqls[2]);
    numeric.displayFilterBy(vqls[3]);
    //numeric.displayFilterBy(vqls[4]);
    numeric.displayFilterBy(vqls[5]);
    numeric.displayFilterBy(vqls[6]);
    numeric.displayFilterBy(vqls[7]);
    numeric.displayFilterBy(vqls[8]);
  }

  
  public void initialize() {
    session = new TransSession(database);
  }

  
  public void insertData() {
    final int MAX = 5;
    final String names[] = {"Clinton", "Tom", "Wilson", "Mono", "Rock", "Jonson"};  	
    final String genders[] = {"male", "female"}; 
    final String titles[] = {"CD1", "CD2", "CD3","CD4","CD5","CD6"};

    try  {		
      Random rand = new Random();
      for (int i=0; i <= MAX ; i++) {
        String id = new String("" + (i+1));
        String title = titles[i];
        String transtFromDate = new  String((12+i) + "-" + (i+1) + "-" + 2005);
        int price = rand.nextInt(1000);
        Image image = new Image();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dtFromDate = sdf.parse(transtFromDate);
        Singer singer = new Singer(i+998, names[i], dtFromDate, genders[i%2]);
        CD cd = new CD(id, title, price, image);

        cd.setSinger(singer);
        cd.setCopiesSold(200*(i%MAX));
        cd.addToStock(10*(rand.nextInt(10000)));

        session.makePersistent(cd);
      }	
      session.commit();
    }
    catch  (java.text.ParseException pe ) {
      pe.printStackTrace();
    }

  } 
  

  public void displayAll()  {
    Query query = new Query(session, "select selfoid from model.CD");
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));

    Object obj;
    int counter = 0;
    System.out.println("===============Display All ========================");
    if (result != null) {
      while ((obj = result.next()) != null) {
        System.out.println("retrieved : " + (CD)obj);
        ++counter;
      }
    }
    System.out.println("Total : " + counter + " results.\n");

    result.close();
  }
  
  
  public void displayFilterBy(String filter) {
    Query query = new Query(session, filter);
    QueryResult result = query.execute(new QueryExecutionOptions().setFetchObjects(true));
    Object obj;
    int counter = 0;
    System.out.println("===Query :"+filter +"===");
    while ((obj = result.next()) != null) {
      CD cd = (CD)obj;
      System.out.println("retrieved : " + cd);
      ++counter;
    }
    System.out.println("Total : " + counter + " results.\n");

    result.close();

  }
} 
