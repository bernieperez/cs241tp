/* 
 * Copyright(c) 1997  Versant Object Technology.
 */

import model.*;
import com.versant.fund.VException;
import com.versant.odmg.*;
import com.versant.odmg3.DBag;
import com.versant.odmg3.BagOfObject;
import java.util.Iterator;
import java.util.Properties;


public class VqlCollOdmg
{
  public static void
  main (String[] args)
  {
    if (args.length  < 1) {
      System.err.println ("Usage: java VqlCollOdmg <dbname>");
      System.exit (1);
    }

    // create a database with the given argument as name
    Database db = null;
    try {
      db = Database.open (args[0], Database.openReadWrite);
    }
    catch (ODMGException ex) {
      System.err.println ("Exception in Database open (): " + ex);
      ex.printStackTrace ();
      System.exit (1);
    }     

    // create a transaction
    Transaction txact = new Transaction ();

    // 1st transaction: 
    txact.begin ();
    System.out.println ("Began transaction");

    // populate database with a bag of five Person objects
    DBag personBag = new BagOfObject ();
    Person dow;
    personBag.add (new Person ("Bar", "Graf", 28, (float)6.2));
    personBag.add (new Person ("Smart", "Alex", 44, (float)5.9));
    personBag.add (new Person ("George", "Burdell", 110, (float)7.8));
    personBag.add (dow = new Person ("Dow", "Jones", 24, (float)5.11));
    personBag.add (new Person ("Udai", "Hussein", 40, (float)5.4));
    txact.session ().makePersistent (personBag);
    System.out.println ("Made bag persistent");

    txact.commit ();
    System.out.println ("After commit of a bag of Person objects," +
			  " size of bag = " + personBag.size () + "\n");

    // 2nd transaction:
    txact.begin ();
    System.out.println ("Began transaction");

    // call query() to find persons whose age is less than 25
    try {
    BagOfObject resultBag = (BagOfObject) personBag.query ("age < 25");
    if (resultBag.contains (dow)) {
      System.out.println ("query () finds: " + dow);
    }

    // call select () to find persons whose last name starts with "J"
    Iterator resultEnum = personBag.select ("lastName LIKE 'J*'");
    while (resultEnum.hasNext ()) {
      if (dow == resultEnum.next()) {
	System.out.println ("select() finds: " + dow);
      }
    }

    // call selectElement() to find the person whose first name is "Dow"
    dow = (Person) personBag.selectElement ("firstName = 'Dow'");
    System.out.println ("selectElement() finds: " + dow);
    
    txact.commit ();
    System.out.println ("After commit transaction");
   }catch(QueryInvalidException qie) {}
  }
}
