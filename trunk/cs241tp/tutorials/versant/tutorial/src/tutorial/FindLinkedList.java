package tutorial;

import com.versant.trans.*;
import tutorial.model.*;

public class FindLinkedList {
  
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println(
          "Usage: java FindLinkedList <database> <root>");
      System.exit(1);
    }        
    
    String database = args[0];
    String root     = args[1];
    TransSession session  = new TransSession(database);

    LinkedList list = (LinkedList) session.findRoot(root);
    System.out.println("Found List: " + list);

    session.endSession ();
  }
}
