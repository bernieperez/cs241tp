package tutorial;

import com.versant.trans.*;
import tutorial.model.*;

public class DeletePersonWithRoot {
  
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println(
          "Usage: java DeletePersonWithRoot <database> <root>");
      System.exit(1);
    }

    String database = args[0];
    String root     = args[1];

    TransSession session = new TransSession(database);

    Person person = (Person) session.findRoot(root);
    session.deleteObject(person);

    session.endSession();
  }
}
