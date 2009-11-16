// Copyright(c) 1997  Versant Object Technology.

import model.*;
import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;
import com.versant.trans.VEnumeration;
import com.versant.fund.NewSessionCapability;
import java.util.Enumeration;
import java.util.Properties;

public class VqlTrans
{
  public static void
  main (String[] args)
  {
    Person[] persons;
    Person p, dow, bar;
    VQLQuery vql1, vql2;
    VEnumeration resultEnum;

    if (args.length  < 1) {
      System.err.println ("Usage: java VqlTrans <dbname>");
      System.exit (1);
    }

    // create a session object
    Properties prop= new Properties ();
    prop.put ("database", args[0]);
    NewSessionCapability cap = new NewSessionCapability ();
    TransSession s = new TransSession (prop, cap);
    System.out.println ("Started session");

    // populate database with five Person objects 
    persons = new Person[5];
    persons[0] = new Person ("Bar", "Graf", 28, (float)6.2);
    persons[1] = new Person ("Smart", "Alex", 44, (float)5.9, persons[0]);
    persons[2] = new Person ("George", "Burdell", 110, (float)7.8, persons[1]);
    persons[3] = new Person ("Dow", "Jones", 24, (float)5.11, persons[2]);
    persons[4] = new Person ("Udai", "Hussein", 40, (float)5.4, persons[3]);

    bar = persons[0];
    dow = persons[3];

    s.makePersistent (persons[4]);
    System.out.println ("Made persistent Person: " + persons[4]);

    s.commit();
    System.out.println ("After commit session\n");

    // create a VQLQuery object to find Person objects 
    // whose first name is 'Dow' 
    vql1 = new VQLQuery (s,
			 "select selfoid from model.Person where firstName = 'Dow'");

    // carry out the query, and enumerate through the query result  
    resultEnum = vql1.execute ();
    System.out.println ("Persons whose first name is 'Dow': ");
    while (resultEnum.hasMoreElements ()) {
      p = (Person) resultEnum.nextElement ();
      System.out.println (p);
    }	

    // create another VQLQuery object with a parameter 
    vql2 = new VQLQuery (s,
			 "select selfoid from model.Person where lastName = $1");

    // bind 'Jones' with the parameter 
    vql2.bind ("Jones");
    resultEnum = vql2.execute ();
    System.out.println ("Binding lastName to 'Jones' found: ");
    Object[] resultObjs = resultEnum.nextBatch(100);
    for (int i = 0; i < resultObjs.length; i ++)
    {
      p = (Person) resultObjs[i];
      System.out.println (p);
    }

    // bind 'Graf' with the parameter using the same VQLQuery instance
    vql2.bind ("Graf");
    resultEnum = vql2.execute ();
    System.out.println ("Binding lastName to 'Graf' found:");
    while (resultEnum.hasMoreElements()) {
        p = (Person) resultEnum.nextElement ();
        System.out.println (p);
    }
	
    // end the session
    s.endSession ();
  }
}
