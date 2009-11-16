package tutorial;

import com.versant.trans.*;
import tutorial.model.*;


public class CreatePerson {
  
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println(
        "Usage: java CreatePerson <database> <name> <age>");
      System.exit(1);
    }
    
    String database = args [0];
    String name     = args [1];
    int    age      = Integer.parseInt (args[2]);
    TransSession session  = new TransSession(database);

    Person person = new Person(name, age);
    session.makePersistent(person);

    session.endSession ();
  }
}
