/* 
 * Copyright(c) 1997  Versant Object Technology.  
 */

import model.*;
import com.versant.fund.NewSessionCapability;
import com.versant.fund.VException;
import com.versant.trans.TransSession;
import com.versant.odmg3.DList;
import com.versant.odmg3.ListOfObject;
import com.versant.odmg.QueryInvalidException;
import java.util.Iterator;
import java.util.Properties;


public class VqlCollTrans
{
    public static void
    main (String[] args) {

      if (args.length  < 1) {
	System.err.println ("Usage: java VqlCollTrans <dbname>");
	System.exit (1);
      }

      // create a session object
      Properties prop = new Properties ();
      prop.put ("database", args[0]);
      NewSessionCapability cap= new NewSessionCapability ();
      TransSession s= new TransSession (prop, cap);
      System.out.println ("Started session");

      // populate database with a list of five Person objects
      DList personList = new ListOfObject ();
      Person dow;
      personList.add (0, new Person ("Bar", "Graf", 28, (float)6.2));
      personList.add (1, new Person ("Smart", "Alex", 44, (float)5.9));
      personList.add (2, new Person ("George", "Burdell", 110, (float)7.8));
      personList.add (3, dow = new Person ("Dow", "Jones", 24, (float)5.11));
      personList.add (4, new Person ("Udai", "Hussein", 40, (float)5.4));
      s.makePersistent (personList);
      System.out.println ("Made list persistent");
      s.commit ();

      System.out.println ("After commit of a list of Person objects," +
			  " size of list = " + personList.size () + "\n");

      // call query() to find persons whose age is between 20 and 25
      try{
      ListOfObject resultList = (ListOfObject)
	personList.query ("age > 20 and age < 25");
      if (resultList.contains (dow)) {
	System.out.println ("query() finds: " + dow);
      }

      // call select() to find persons whose last name starts with "I"
      // or "J" or "K"
      Iterator resultEnum = personList.select ("lastName like '[IJK]*'");
      while (resultEnum.hasNext ()) {
	if (dow == resultEnum.next()) {
	  System.out.println ("select () finds: " + dow);
	}
      }

      // call selectElement() to find the person whose first name is "Do?"
      dow = (Person) personList.selectElement ("firstName = 'Do?'");
      System.out.println ("selectElement() finds: " + dow);
     }catch(QueryInvalidException qie) {}

      s.endSession();
      System.out.println ("Ended session");
    }
}
