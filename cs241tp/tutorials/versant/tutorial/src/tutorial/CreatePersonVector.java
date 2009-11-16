package tutorial;

import com.versant.fund.*;
import com.versant.trans.*;
import com.versant.util.*;
import tutorial.model.*;

public class CreatePersonVector {
  
  public static void main(String[] args)
  {
    if (args.length != 4) {
      System.out.println("Usage: java CreatePersonVector" +
          " <database> <name> <age> <root>");
      System.exit(1);
    }
    
    String database = args[0];
    String name     = args[1];
    int    age      = Integer.parseInt(args[2]);
    String root     = args[3];
    TransSession session = new TransSession(database);

    VVector vector;
    try {
      vector = (VVector) session.findRoot(root);
    } 
    catch(VException e) {
      if (e.getErrno() == Constants.EVJ_ROOTNAME_DOES_NOT_EXIST) {
        vector = new VVector();
        session.makeRoot(root, vector);
      } 
      else
        throw e;
    }

    Person person = new Person(name, age);
    vector.addElement(person);

    session.endSession();        
  }
}
