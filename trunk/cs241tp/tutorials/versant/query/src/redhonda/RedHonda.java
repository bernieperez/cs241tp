package redhonda;

import model.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;


public class RedHonda {

  static String database;
  TransSession session;

  
  public RedHonda() {
  }

  
  public static void main(String args[]) {

    if (args.length  < 1) {
      System.out.println( "Usage  java model <<dbname>>" );
      System.exit(0);
    }

    database = args [0];

    final String vqls[] = {
        "select selfoid from model.Broker where for all x in dealers as model.Dealer : " + 
        " ( for all y in x->vehicles as model.Vehicle : ( y->color like \"red\" and y->brand like \"Honda\"))",
        "select selfoid from model.Broker where exists x in dealers as model.Dealer : " + 
        "( exists y in x->vehicles as model.Vehicle : ( y->color like \"red\" and y->brand like \"Honda\"))"
    };

    RedHonda RedHonda = new RedHonda();

    // initialize  PersistenceManager,  transaction
    RedHonda.initialize();
    // insert data
    RedHonda.insertData();
    // display  data
    RedHonda.displayAll();

    for (int cnt = 0 ; cnt < vqls.length; cnt++)
      RedHonda.displayFilterBy(vqls[cnt]) ;
  }

  
  public void  initialize ( ) {
    session = new TransSession(database);
  }

  
  public void insertData(){
    final int MAX = 5;
    final String colors[] = {"red", "blue", "black"};
    final String brands[] = {"Honda", "Toyota", "Bens"};

    Random rand	= new Random ();
  
    for (int i=0; i < MAX; i++) {
      Broker broker = new Broker("broker" + i);

      if (i + 1 == MAX) {
        // All dealers with red honda vehicle
        Dealer dealer1 = new Dealer("dealer1" );
        Vehicle vehicle1 = new Vehicle("red", "Honda");
        dealer1.addVehicle(vehicle1);
        broker.addDealer(dealer1);

        Dealer dealer2 = new Dealer("dealer2");
        Vehicle vehicle2= new Vehicle("red", "Honda");
        dealer2.addVehicle(vehicle2);
        broker.addDealer(dealer2);
      }
      else {
        for (int dealer_cnt = 0 ; dealer_cnt < 3; dealer_cnt++) {
          Dealer dealer	= new Dealer("dealer" + dealer_cnt);

          int vehicle_max = rand.nextInt(3) + 1;
          for (int vehicle_cnt = 0 ; vehicle_cnt < vehicle_max; vehicle_cnt++) {
            int rand_col = rand.nextInt(3) ;
            int rand_brd = rand.nextInt(3) ;
            Vehicle vehicle = new Vehicle(colors[rand_col], brands[rand_brd]); 	
            dealer.addVehicle(vehicle);
          }
          broker.addDealer(dealer);
        }
      }
      session.makePersistent(broker);
    }	
    session.commit();
  } 
  
  
  public void displayAll() {
    Query query = new Query(session, "select selfoid from model.Broker");
    QueryResult result = query.execute();
    
    
    Object obj= null;
    int counter = 0;
    System.out.println("====Display All======");

    while ((obj = result.next()) != null) {
      System.out.println("retrived : " + (Broker)obj);
      ++counter;
    }
    System.out.println("Total : " + counter + " results.");

    result.close();
    query.close();		
  }
  

  public void displayFilterBy(String filter)  {
    Query query = new Query(session, filter);
    QueryResult result = query.execute();
    
    Object obj = null;
    int counter = 0;
    
    System.out.println( "====Display :" + filter + "======" );

    while ((obj = result.next()) != null) { 
      Broker b  = (Broker)obj;
      System.out.println("Broker: " + b );
      ++counter;
    }
    System.out.println("Total Brokers: " + counter);

    result.close ();
    query.close ();
  }
} 
