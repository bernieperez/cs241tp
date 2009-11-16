package tutorial;

import com.versant.trans.*;
import tutorial.model.*;


public class CreatePersonWithRoot {

  public static void main(String[] args) {
    if (args.length != 4) {
      System.out.println ("Usage: java CreatePersonWithRoot" +
          "<database> <name> <age> <root>");
      System.exit(1);
    }
    
    String database = args[0];
    String name     = args[1];
    int    age      = Integer.parseInt(args[2]);
    String root     = args[3];
    TransSession session  = new TransSession(database);

    Person person = new Person(name, age);
    session.makeRoot(root, person);

    session.endSession();
  }
}
