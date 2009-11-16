package tutorial;

import com.versant.trans.*;
import tutorial.model.*;


public class FindPersonWithRoot {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println(
          "Usage: java FindPersonWithRoot <database> <root>");
      System.exit (1);
    }
    String database = args[0];
    String root     = args[1];
    TransSession session  = new TransSession(database);

    Person person = (Person) session.findRoot(root);
    System.out.println("Found " + person);

    session.endSession();
  }
}
