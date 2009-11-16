import jfv.*;
import com.versant.trans.*;
import com.versant.fund.*;
import com.versant.util.*;
import java.util.*;


public class Example1
{
  public static void main (String[] args) {
    if (args.length < 1) {
      System.err.println ("Usage: java Example1 <dbname>");
      System.exit (1);
    }
      
    Properties p = new Properties();
    p.setProperty("database", args[0]);
    p.setProperty("useJFV", "true");

    TransSession s = new TransSession (p);
      
    VQLQuery query = new VQLQuery (s, "select selfoid from Vehicle");
    Enumeration e = query.execute ();

    if (!e.hasMoreElements()) {
      System.out.println ("No Vehicle instances were found.");
    }
    else {
      while ( e.hasMoreElements() ) {
        jfv.Vehicle v = (jfv.Vehicle) e.nextElement ();
        System.out.println ("Found Vehicle instance with\n" +
			    "\tManufacturer: " + v.manufacturer + "\n" +
			    "\tModel: " + v.model + "\n" +
			    "\tYear: " + v.year + "\n" +
			    "\tRetailCost: " + v.retailCost);

        // increment the Vehicle's retailCost by 1000.00
        v.retailCost += 1000.00;
      }
    }
      
    // create a new Vehicle instance and make it persistent
    jfv.Vehicle v = new jfv.Vehicle ();
    v.manufacturer = "Audi";
    v.model = "A300";
    v.year = 1998;
    v.retailCost = 32650.0;	

    s.makePersistent (v);
    s.commit ();
  }
}
