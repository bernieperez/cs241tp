//Copyright(c) 1997  Versant Object Technology.
import model.*;

import com.versant.odmg.*;
import com.versant.odmg3.DList;
import com.versant.odmg3.ListOfObject;
import java.util.Iterator;

/**
 * This example demonstrates usage of odmg.List objects as 
 * as an attribute of a Persistent Capable (c) category class.
 */
public class ListExample1
{
  public static void
  main (String[] args)
  {
    Department toys ;

    if (args.length  < 1) {
      System.err.println("Usage: java ListExample1 <dbname>");
      System.exit (1);
    }

    // create a database with the given argument as name
    Database db = null;
    try {
      db = Database.open(args[0], Database.openReadWrite);
    }
    catch (ODMGException ex) {
      System.err.println("Exception in Database open (): " + ex);
      ex.printStackTrace();
      System.exit (1);
    }     

    // create a transaction
    Transaction txact = new Transaction();

    // start 1st transaction
    txact.begin();
    System.out.println("Transaction began");

    // create a Department object with one staff
    toys = new Department("toys");
    toys.staff().add(new Person("Bar", "Graf", 28, (float)6.2));
    db.bind(toys, "ToysDepartment");
    txact.commit();
    System.out.println("Ater commit of toys deparment with one staff");

    // start 2nd transaction
    txact.begin();
    System.out.println("\nNext transaction began");

    // select
    try {
      System.out.println("select staff named 'Bar' found: ");
      Iterator resultEnum = toys.staff().select("firstName = 'Bar'");
      Person p;
      while (resultEnum.hasNext()) {
        p = (Person) (resultEnum.next()); 
        System.out.println (p);
      }

      // existsElement
      if (toys.staff().existsElement("age > 50")) {
        System.out.println
        ("Toys department does have anyone older than 50 yrs"); 
      }
    }
    catch (QueryInvalidException qie) {}

    // add two more staff to toys department
    DList newStaff = new ListOfObject();
    newStaff.add (new Person ("Joe", "Smith", 28, (float)4.5));
    newStaff.add (new Person ("Amy", "Johnson", 53, (float)5.2));
    toys.staff().concat(newStaff);
    System.out.println("Added two more staff to the toys department");

    txact.commit();
    System.out.println("After transaction Commit");

    System.out.println("Toys department: " + toys.toString());
  }
}
