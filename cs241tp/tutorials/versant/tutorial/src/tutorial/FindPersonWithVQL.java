package tutorial;

import java.util.*;
import com.versant.trans.*;
import tutorial.model.*;


public class FindPersonWithVQL {
  
  public static void main (String[] args) {
    if (args.length != 1) {
      System.out.println(
          "Usage: java FindPersonWithVQL <database>");
      System.exit(1);
    }
    String database = args[0];
    TransSession session  = new TransSession(database);

    VQLQuery query = new VQLQuery(session, 
        "select selfoid from tutorial.model.Person");
    Enumeration e = query.execute();

    while (e.hasMoreElements()) {
      Person person = (Person) e.nextElement();
      System.out.println ("Found " + person);
    }

    session.endSession();
  }
}
