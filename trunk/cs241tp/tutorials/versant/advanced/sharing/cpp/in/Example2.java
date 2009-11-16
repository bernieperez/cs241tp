import jfv.*;
import com.versant.trans.*;
import com.versant.fund.*;
import com.versant.util.*;
import java.util.*;


public class Example2
{
  public static void main (String[] args) {
    if (args.length < 1) {
      System.err.println ("Usage: java Example2 <dbname>");
      System.exit (1);
    }
      
    Properties p = new Properties();
    p.setProperty("database", args[0]);
    p.setProperty("useJFV", "true");

    TransSession s = new TransSession (p);
      
    VQLQuery query = new VQLQuery (s, "select selfoid from Car");
    Enumeration e = query.execute ();

    if (!e.hasMoreElements()) {
      System.out.println ("No Car instances were found.");
    }
    else {
      while ( e.hasMoreElements() ) {
        jfv.Car c = (jfv.Car) e.nextElement ();
        System.out.println ("Found Car instance: " + c);

        // increment the Car's retailCost by 1000.00
        c.retailCost += 1000.00;
      }
    }
      
    // create a new Car instance and make it persistent
    jfv.Car c = new jfv.Car ("Honda", "Civic", 1997, 16770.50, 2, 4, 837365);

    s.makePersistent (c);
    s.commit ();
  }
}
