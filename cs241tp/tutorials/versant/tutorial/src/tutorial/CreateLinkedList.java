package tutorial;

import com.versant.trans.*;
import tutorial.model.*;

public class CreateLinkedList {
  
  public static void main(String[] args) {
    if (args.length != 1 && args.length != 2) {
      System.out.println(
          "Usage: java CreateLinkedList <database> [root]");
      System.exit(1);
    }
    
    String database = args[0];
    String root     = (args.length == 2) ? args[1] : null;
    TransSession session  = new TransSession(database);

    LinkedList list = null;
    for (int i = 0; i < 5; i++) 
      list = new LinkedList(i, list);
    
    if (root != null)
      session.makeRoot(root, list);

    session.endSession();
  }
}
