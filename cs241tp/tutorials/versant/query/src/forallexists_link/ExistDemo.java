package forallexists_link;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;

public class ExistDemo {

  static String database;

  TransSession session;

  public ExistDemo() {
  }

  public static void main(String args[]) {

    if (args.length < 1) {
      System.out.println("Usage  java ExistDemo <<dbname>>");
      System.exit(0);
    }

    database = args[0];

    final String vqls[] = {
        "select selfoid from model.Customer where for all x in orders as model.Order : ( x->totalPrice > 1500)",
        "select selfoid from model.Customer where exists x in orders as model.Order : ( x->totalPrice < 1500)" };

    ExistDemo existdemo = new ExistDemo();

    existdemo.initialize();
    existdemo.insertData();
    existdemo.displayAll();
    existdemo.displayFilterBy(vqls[0]);
    existdemo.displayFilterBy(vqls[1]);
  }

  public void initialize() {
    session = new TransSession(database);
  }

  public void insertData() {

    final int MAX = 5;
    final String names[] = {"Clinton", "Tom", "Wilson", "Mono", "Rock",
        "Jonson"};
    final String genders[] = {"male", "female"};
    final String titles[] = {"CD1", "CD2", "CD3", "CD4", "CD5", "CD6"};

    try {
      Random rand = new Random();
      for (int i = 0; i <= MAX; i++) {
        String id = new String("" + (i + 1));
        Image image = new Image();
        String transtFromDate = new String((12 + i) + "-" + (i + 1) + "-"
            + 2005);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dtFromDate = sdf.parse(transtFromDate);
        Address address = new Address();
        PersonalDetails pd = new PersonalDetails();
        ArrayList orders = new ArrayList(MAX);
        Customer customer = new Customer(i + 1, names[i], dtFromDate,
            genders[i % 2], address, pd, image);

        for (int ordcount = 0; ordcount < MAX; ordcount++) {
          int price = 100 * (ordcount + i);
          ArrayList items = new ArrayList();
          for (int itemcount = 0; itemcount < 2; itemcount++) {
            CD cd = new CD("" + (itemcount + 1), titles[itemcount], price
                + itemcount);
            Item itemcd = new Item(cd, itemcount + 1);
            items.add(itemcd);
          }
          Order order = new Order(customer, items);
          orders.add(order);
        }
        customer.setOrders(orders);
        session.makePersistent(customer);

      }
      session.commit();

    } 
    catch (java.text.ParseException e) {
      e.printStackTrace();
    }
  } 

  
  public void displayAll() {
    Query query = new Query(session, "select selfoid from model.Customer");
    QueryResult result = 
      query.execute(new QueryExecutionOptions().setFetchObjects(true));

    Object obj;
    int counter = 0;
    System.out.println("===============Display All ========================");
    if (result != null) {
      while ((obj = result.next()) != null) {
        System.out.println("retrieved : " + (Customer) obj);
        ++counter;
      }
    }
    System.out.println("Total : " + counter + " results.\n");

    result.close();
  }

  public void displayFilterBy(String filter) {
    Query query = new Query(session, filter);
    QueryResult result = 
      query.execute(new QueryExecutionOptions().setFetchObjects(true));
    
    Object obj;
    int counter = 0;
    System.out.println("=====Display :" + filter + "=======");
    while ((obj = result.next()) != null) {
      Customer customer = (Customer) obj;
      System.out.println("retrieved : " + customer);
      ++counter;
    }
    System.out.println("Total : " + counter + " results.\n");
    result.close();
  }
} 
