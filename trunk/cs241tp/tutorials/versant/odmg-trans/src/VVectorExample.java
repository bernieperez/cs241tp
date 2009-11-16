/* 
 * Copyright(c) 1997  Versant Object Technology.
 */

import model.*;
import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;
import com.versant.fund.NewSessionCapability;
import com.versant.util.VVector;
import java.util.Properties;
import java.util.Enumeration;

/** 
 * This example demonstrates usage of VVector object as a
 * stand-alone persistent object. Note that all elements of VVector
 * should be persistent objects, ie. Persistent Always or Persistent
 * Capable.
 */

public class VVectorExample 
{
  public static void
  main(String[] args)
  {
    if (args.length  < 1) {
      System.err.println ("Usage: java VVectorExample <dbname>");
      System.exit (1);
    }

    // start a database session
    NewSessionCapability capability = new NewSessionCapability ();
    Properties prop = new Properties ();
    prop.put ("database", args[0]);
    TransSession s = new TransSession (prop, capability);

    // create Person objects
    Person p1 = new Person ("Bar", "Graf", 28, (float)6.2);
    Person p2 = new Person ("Smart", "Alex", 44, (float)5.9);
    Person p3 = new Person ("George", "Burdell", 110, (float)7.8);

    // create a VVector and add Person p1 into the vector
    VVector vec = new VVector ();
    System.out.println ("Created a VVector: \n\t" + vec);
    vec.addElement (p1);
    System.out.println ("Added a Person into VVector");

    // make the vector persistent and commit the transaction
    s.makePersistent (vec);
    System.out.println ("Made the VVector persistent: \n\t" + vec);
    s.commit ();
    System.out.println ("After commit of the transaction\n");

    // select all VVector instances from database
    VQLQuery q = new VQLQuery (s,
		      "select selfoid from com.versant.util.VVector");
    Enumeration e = q.execute ();
    VVector pvec = null;
    while (e.hasMoreElements()) {
      pvec = ((VVector)(e.nextElement()));
      if (vec == pvec)
	System.out.println ("VQLQuery found the persistent VVector: \n\t" +
			    pvec);
    }

    // add p2 and commit
    pvec.addElement (p2);
    System.out.println ("Added persistent capable instance: \n\t" + p2 +
			"\ninto VVector");
    s.commit ();
    System.out.println ("After commit of the transaction\n");

    // contains Person p2 ?
    System.out.println ("Check - the VVector contains person: \n\t" + p2 +
			": " + pvec.contains (p2));

    // add p3 then rollback
    pvec.addElement (p3);
    System.out.println ("Added persistent capable instance: \n\t" + p3 +
			"\ninto VVector");
    s.rollback ();
    System.out.println ("Rollback the transaction\n");

    // contains p3 ?
    System.out.println ("Check - the VVector contains person: \n\t" + p3 +
			": " + pvec.contains (p3));

    // remove p1 and commit
    pvec.removeElement (p1);
    System.out.println ("Removed persistent capable instance: \n\t" + p1 +
			"\nfrom VVector");
    s.commit ();
    System.out.println ("After transaction commit\n");

    // vector contains p1 ?
    System.out.println ("Check - the VVector contains person: \n\t" + p1 +
			": " + pvec.contains (p1));

    // enumerate all elements in the vector
    Enumeration pe = pvec.elements ();
    while (pe.hasMoreElements ()) {
      System.out.println("Element in VVector: \n\t" + pe.nextElement());
    }

    s.endSession ();
  }
}
