package tutorial;

import com.versant.trans.*;
import com.versant.util.*;
import tutorial.model.*;

public class FindPersonVector {
  
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println(
          "Usage: java FindPersonVector <database> <root>");
      System.exit(1);
    }
    String database = args[0];
    String root     = args[1];
    TransSession session  = new TransSession(database);

    VVector vector = (VVector) session.findRoot(root);
    for (int i = 0; i < vector.size(); i++) {
      Person person = (Person) vector.elementAt(i);
      System.out.println("Found " + person);
    }

    session.endSession ();        
  }
}
