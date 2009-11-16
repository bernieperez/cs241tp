package tutorial;

import com.versant.trans.*;
import tutorial.model.*;


public class AddEmployees {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java AddEmployees <database>");
      System.exit(1);
    }

    String database = args[0];
    TransSession session  = new TransSession(database);

    Employee the_boss   = new Employee("The Boss",   42, 110000);
    Employee jane_jones = new Employee("Jane Jones", 24, 80000);
    Employee john_doe   = new Employee("John Doe",   25, 75000);
    Employee lois_line  = new Employee("Lois Line",  36, 70000);

    Department engineering = new Department("Engineering", the_boss);
    Department marketing = new Department("Marketing",   lois_line);

    the_boss.department   = engineering;
    jane_jones.department = engineering;
    john_doe.department   = marketing;
    lois_line.department  = marketing;

    session.makePersistent(the_boss);
    session.makePersistent(jane_jones);
    session.makePersistent(john_doe);
    session.makePersistent(lois_line);
    session.makePersistent(engineering);
    session.makePersistent(marketing);

    session.endSession();        
  }
}
