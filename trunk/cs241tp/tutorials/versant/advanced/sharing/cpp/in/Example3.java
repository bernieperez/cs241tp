import jfv.*;
import com.versant.trans.*;
import com.versant.fund.*;
import com.versant.util.*;
import java.util.*;


public class Example3
{
  public static void main (String[] args) {
    if (args.length < 1) {
      System.err.println ("Usage: java Example3 <dbname>");
      System.exit (1);
    }
      
    Properties p = new Properties();
    p.setProperty("database", args[0]);
    p.setProperty("useJFV", "true");

    TransSession s = new TransSession (p);
      
    VQLQuery query = new VQLQuery (s, "select selfoid from FloorPlan");
    Enumeration e = query.execute ();

    if (!e.hasMoreElements()) {
      System.out.println ("No FloorPlan instances were found.");
    }
    else {
      while ( e.hasMoreElements() ) {
        jfv.FloorPlan fp = (jfv.FloorPlan) e.nextElement ();
        System.out.println ("FloorPlan: " + fp.name);
        CppHashMapIterator keys = fp.cubesAssigned.keys ();
        while (keys.hasNext ()) {
          Object key = keys.next ();
          jfv.Employee emp = (jfv.Employee) fp.cubesAssigned.get (key);
          System.out.println ("Cube: " + key +
			      "  Employee: " +
			      "Name: " + emp.name + "  Id: " + emp.id);
        }
        jfv.Employee newEmp = new Employee ();
        newEmp.name = new String ("Gary Stevenson");
        newEmp.id = 108;
        s.makePersistent (newEmp);
        fp.cubesAssigned.put (new Integer (20), newEmp);
        s.dirtyObject(fp);
      }
    }
      
    s.commit ();
  }
}
