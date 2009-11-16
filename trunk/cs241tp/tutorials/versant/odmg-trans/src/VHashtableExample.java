/* 
 * Copyright(c) 1997  Versant Object Technology.
 */

import model.*;
import com.versant.trans.*;
import com.versant.fund.*;
import com.versant.util.*;
import java.util.*;
import java.io.*;

/** 
 * This example class demonstrates usage of VHashtable object as a
 * stand-alone persistent object. Note that both the keay and value
 * of VHashtable should be persistent objects, ie. Persistent Always or
 * Persistent Capable.
 */
public class VHashtableExample 
{
  public static void
  main(String[] args) {

    if (args.length  < 1) {
      System.err.println ("Usage: java VHashtableExample <dbname>");
      System.exit (1);
    }

    // create a database session
    NewSessionCapability capability = new NewSessionCapability ();
    Properties prop = new Properties ();
    prop.put ("database",args[0]);
    TransSession s = new TransSession (prop,capability);

    // create Person objects
    Person p1 = new Person ("Bar", "Graf", 28, (float)6.2);
    Person p2 = new Person ("Smart", "Alex", 44, (float)5.9);
    Person p3 = new Person ("George", "Burdell", 110, (float)7.8);
    Person p4 = new Person ("Dow", "Jones", 24, (float)5.11);

    // create a VHashtable and put a key-value pair into the hashtable
    VHashtable ht = new VHashtable ();
    System.out.println ("Created a persistent VHashtable: \n\t" + ht);
    ht.put (p1, p2);
    System.out.println ("Added: \n\t" + p1 +
			" [KEY]\n\t" + p2 +
			" [VALUE]\n into VHashtable");

    // make the hashtable object persistent and commit the transaction
    s.makePersistent (ht);
    System.out.println ("Made hashtable persistent:\n\t" + ht);

    s.commit ();
    System.out.println ("After transaction commit\n");

    // find all VHashtable instances from database
    VQLQuery q = new VQLQuery (s,
		      "select selfoid from com.versant.util.VHashtable");
    Enumeration e = q.execute ();
    VHashtable pht = ((VHashtable)(e.nextElement()));
    System.out.println ("VQLQuery found a persistent VHashtable: \n\t" +
			pht);

    // hashtable look up
    System.out.println ("Check - the VHashtable contains person as value:" +
			"\n\t" + p2 + " :" + pht.contains (p2));
    System.out.println ("Check - the VHashtable contains person as key:" +
			"\n\t" + p1 + " :" + pht.containsKey (p1));

    // enumerate all elements in the hashtable
    Enumeration pe = pht.elements ();
    while (pe.hasMoreElements ()) {
      System.out.println("Person value in VHashtable: \n\t" +
			 pe.nextElement());
    }

    s.endSession ();
  }
}
