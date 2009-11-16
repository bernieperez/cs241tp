// Copyright(c) 1997  Versant Object Technology.

import model.*;
import com.versant.odmg.Database;
import com.versant.odmg.Transaction;
import com.versant.odmg.ODMGException;
import com.versant.odmg3.ListOfObject;
import com.versant.trans.VQLQuery;
import java.util.Iterator;
import java.util.Enumeration;

/** 
 * This example demonstrates usage of the odmg.List object as a
 * stand-alone persistent objects.
 */
public class ListExample2
{
  public static void
  main (String[] args)
  {
    Transaction txact;
    Enumeration resultEnum;
    ListOfObject personList;
    ListOfObject resultList;
    Person bar;
    VQLQuery vql;
    int size;
    int i;
    Iterator all;

    if (args.length  < 1) {
      System.err.println ("Usage: java ListExample2 <dbname>");
      System.exit (1);
    }

    // create a database with the given argument as name
    Database db = null;
	try {
        db = Database.open (args[0], Database.openReadWrite);
	}
    catch (ODMGException ex1) {
        System.err.println ("Exception in Database open (): " + ex1);
        ex1.printStackTrace ();
        System.exit (1);
    }     

    // create a transaction
    txact = new Transaction ();

    // 1st transaction
    txact.begin ();
    System.out.println ("Transaction began");

    // create a List of two Person objects
    personList = new ListOfObject ();
    personList.add (bar = new Person ("Bar", "Graf", 28, (float)6.2));
    personList.add (new Person ("Smart", "Alex", 44, (float)5.9));
    System.out.println ("Created a list of two peron objects");

    // bind the List object with name "Person List"
    db.bind (personList, "Person List");
    System.out.println ("Bound the list with name \"Person List\"");

    // commit and end the 1st transaction ----
    txact.commit();
    System.out.println ("Ater commit of transaction");

    // 2nd transaction
    txact.begin ();
    System.out.println ("\nNext transaction began");

    // database root lookup by name "Person List"
    try {
      resultList = (ListOfObject) db.lookup ("Person List");
      if (resultList == personList)
         System.out.println ("Database.lookup method found personList");
    }
    catch (ODMGException ex3) {
      System.err.println ("Exception in Database lookup(): " + ex3);
      ex3.printStackTrace ();
      System.exit (1);
    }
      
    // find all instances of ListOfObject using VQLQuery
    vql = new VQLQuery (txact.session (),
			"select selfoid from com.versant.odmg3.ListOfObject");
    resultEnum = vql.execute ();
    while (resultEnum.hasMoreElements ()) {               
      resultList = (ListOfObject) resultEnum.nextElement();
      if (resultList == personList) {
         System.out.println ("VQLQuery found a person list");
         break;
      }
    }

    // print out contents
    System.out.println ("The person list contains " + personList.size () +
			" Person object(s)");
    all = personList.iterator();
    i = 0;
    while (all.hasNext()) {
      System.out.println (i + ": " + all.next());
      i++;
    }

    // add a Person object at position 2
    personList.add (2, new Person ("Bobby", "Lo", 7, (float)1.8));

    // add a Person object at the end, ie. position 3
    personList.add (bar);

    // replace the Person object at position 1
    personList.add (1, new Person ("Dow", "Jones", 24, (float)5.11));
    System.out.println ("Added two persons and replaced one");

    // commit changes
    txact.commit ();
    System.out.println ("Commit of Transaction");

    // 3rd transaction
    txact.begin ();
    System.out.println ("\nNext transaction began");

    // print out contents
    size = personList.size ();
    System.out.println ("Person list contains " + size + " Person object(s)");
    for (i = 0; i < size; i++) {
      System.out.println (i + ": " + personList.get (i));
    }
 
    // remove all occurrences of the person object referenced by bar
    int count = 0;
    if (personList.contains (bar)) {
       int pos = personList.indexOf (bar);
       Object p= personList.remove (pos);
       count++;
       System.out.println ("Removed " + count + " occurrence(s) of Bar Graf");
    }

    // remove object at position 0
    if (personList.isEmpty () == false) 
       personList.remove (0);
    System.out.println ("Removed object at position 0");

    // commit changes
    txact.commit ();
    System.out.println ("Commit of Transaction");

    // 4th transaction
    txact.begin ();
    System.out.println ("\nNext transaction began");

    // print out contents again
    size = personList.size ();
    System.out.println ("Person List contains " + size +
			" Person object(s)" ); 
    for (i = 0; i < size; i++) {
      System.out.println (i + ": " + personList.get (i));
    } 

    // commit changes
    txact.commit();
    System.out.println ("Commit of Transaction");
  }
}
